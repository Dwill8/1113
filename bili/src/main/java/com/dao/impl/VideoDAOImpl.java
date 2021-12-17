package com.dao.impl;

import com.bean.Video;
import com.dao.VideoDAO;
import com.utils.JDBCUtils;
import com.utils.PBKDF2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VideoDAOImpl implements VideoDAO {
    Connection conn = JDBCUtils.getConn();
    // 数据库连接对象
    PreparedStatement pstmt;
    @Override
    public Map checkReward(Integer uid, Integer vid) {
        Map result = new HashMap();
        try{
            pstmt = conn.prepareStatement("select cid from coin where coin_from = ? and coin_to = ?");
            pstmt.setInt(1, uid);
            pstmt.setInt(2, vid);
            ResultSet rs = pstmt.executeQuery();
            // 已投币,不能再投
            if(rs.next()) {
                result.put("uid", uid);
                result.put("vid", vid);
                result.put("status", 0);
            } else {
                result.put("uid", uid);
                result.put("vid", vid);
                result.put("status", 1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            result.put("uid", uid);
            result.put("vid", vid);
            result.put("status", 2);
        }
        System.out.println("uid:" + result.get("uid") + " vid:" + result.get("vid") + " check reward:" + result.get("status"));
        return result;
    }

//    @Override
//    public int checkCoinNum(Integer uid) {
//        int coinNum = -1;
//        try {
//            pstmt = conn.prepareStatement("select coin from user where uid=?");
//            pstmt.setInt(1, uid);
//            ResultSet rs = pstmt.executeQuery();
//
//            if(rs.next()) {
//                coinNum = rs.getInt(1);
//            }
//
//        } catch (SQLException e){
//            e.printStackTrace();
//        }
//        return coinNum;
//    }

    @Override
    public boolean rewardCoin(Integer uid, Integer vid) {
        try {
            // conn.setAutoCommit(false);
            // 更新关注人币数（Unsigned,更新币数不成功不执行返回0）
            pstmt = conn.prepareStatement("UPDATE `user` SET coin=coin-1 WHERE uid=?");
            pstmt.setInt(1, uid);
            int updateValue = pstmt.executeUpdate();

            if(updateValue == 1) {
                // 投币动作插入投币表
                pstmt = conn.prepareStatement("insert into coin (coin_from,coin_to) values (?,?)");
                pstmt.setInt(1, uid);
                pstmt.setInt(2, vid);
                pstmt.executeUpdate(); // 返回数据库储存成功否

                // 更新视频币数
                pstmt = conn.prepareStatement("UPDATE video SET coin=coin+1 WHERE vid=?");
                pstmt.setInt(1, vid);
                pstmt.executeUpdate();

                // 更新视频账号币数
                pstmt = conn.prepareStatement("UPDATE user SET coin=coin+1 WHERE uid=(select uid from video as a where vid=?);");
                pstmt.setInt(1, vid);
                pstmt.executeUpdate();
                //conn.commit();
                //用不用else rollback? 我觉得不用因为没有更新
            }
            return updateValue == 1;
        } catch (Exception e) {
//            try {
//                conn.rollback();
//            } catch (SQLException es) {
//                es.printStackTrace();
//            }
            return false;
        }
    }

    @Override
    public boolean saveVideo(Video video) {
        try {
            //新增一列存入到数据库里
            pstmt = conn.prepareStatement("insert into video(vname, vname_origin, vtitle, vportrait, vportrait_small, uid, vurl) values(?,?,?,?,?,?,?)");
            pstmt.setString(1, video.getVideoName());
            pstmt.setString(2, video.getVideoNameOrigin());
            pstmt.setString(3, video.getVideoTitle());
            pstmt.setString(4, video.getVideoPortrait());
            pstmt.setString(5, video.getVideoPortraitSmall());
            pstmt.setInt(6, video.getUserId());
            pstmt.setString(7, video.getVideoURL());
            pstmt.executeUpdate(); // 返回数据库储存成功否
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Map checkLike(Integer uid, Integer vid) {
        return null;
    }

    @Override
    public boolean like(Integer uid, Integer vid) {
        return false;
    }

    @Override
    public List<String> bulletScreenList(Integer vid) {
        return null;
    }
}
