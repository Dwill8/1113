package com.dao.impl;

import com.dao.VideoDAO;
import com.utils.JDBCUtils;

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
            pstmt = conn.prepareStatement("select coin from reward_coin where uid = ? and vid = ?");
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
        return result;
    }

    @Override
    public int checkCoinNum(Integer uid) {
        int coinNum = -1;
        try {
            pstmt = conn.prepareStatement("select coin from user where uid=?");
            pstmt.setInt(1, uid);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) {
                coinNum = rs.getInt(1);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return coinNum;
    }

    @Override
    public boolean rewardCoin(Integer uid, Integer vid) {
        try {
            conn.setAutoCommit(false);
            // 投币动作插入投币表
            pstmt = conn.prepareStatement("insert into follow (uid, vid) values (?,?)");
            pstmt.setInt(1, uid);
            pstmt.setInt(2, vid);
            pstmt.executeUpdate(); // 返回数据库储存成功否

            // 更新视频币数
            pstmt = conn.prepareStatement("UPDATE video SET coin=coin+1 WHERE vid=?");
            pstmt.setInt(1, vid);
            pstmt.executeUpdate();

            // 更新关注人币数
            pstmt = conn.prepareStatement("UPDATE `user` SET coin=coin-1 WHERE uid=?");
            pstmt.setInt(1, uid);
            pstmt.executeUpdate();

            // 更新视频账号币数
            pstmt = conn.prepareStatement("select uid from video where vid=?");
            pstmt.setInt(1, vid);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                // 通过vid获取其账号的uid
                int vOwnerId = rs.getInt(1);
                pstmt = conn.prepareStatement("UPDATE user SET coin=coin+1 WHERE uid=?");
                pstmt.setInt(1, vOwnerId);
                pstmt.executeUpdate();
            }
            conn.commit();
            return true;
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException es) {
                es.printStackTrace();
            }
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
