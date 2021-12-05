package com.dao.impl;

import com.bean.User;
import com.dao.UserDAO;
import com.utils.JDBCUtils;
import com.utils.PBKDF2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDAOImpl implements UserDAO {
    Connection conn = JDBCUtils.getConn();

    // 数据库连接对象
    PreparedStatement pstmt;

    @Override
    public Map queryUserByUsername(String username) {
        return checkUsername(username);
    }

    @Override
    public Map queryUserByUsernameAndPassword(String username, String password) {
        return checkLogin(username, password);
    }

    @Override
    public boolean saveUser(User user) {
        return update(user.getUsername(), user.getPassword());

    }

    public boolean update(String username, String password) {
        try {
            String salt = PBKDF2.getSalt(); //新增一列存入到数据库里
            String pbkdf2Password = PBKDF2.getPBKDF2(password, salt);
            pstmt = conn.prepareStatement("insert into user(username, password, salt, PBKDF2) values(?,?,?,?)"); //注册其他信息
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, salt);
            pstmt.setString(4, pbkdf2Password);
            pstmt.executeUpdate(); // 返回数据库储存成功否
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    @Override
    public User findUserByName(String username) {
        User user = new User();
        try{
            pstmt = conn.prepareStatement("select uid, gender, email, portrait, permission, status from user where username = ?");
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user.setId(rs.getInt(1));
                user.setUsername(username);
                user.setGender(rs.getString(2));
                user.setEmail(rs.getString(3));
                user.setPortrait(rs.getString(4));
                user.setPermission(rs.getString(5));
                user.setStatus(rs.getString(6));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    // 查询粉丝数量
    @Override
    public Integer queryFollowerAmount(Integer id) {
        Integer followerAmount = 0;
        try {
            pstmt = conn.prepareStatement("select count(*) from follow where follower_id = ?");
                pstmt.setInt(1, id);
                ResultSet rs = pstmt.executeQuery();
                while(rs.next()) {
                followerAmount = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return followerAmount;
    }

    // 查询关注数量
    @Override
    public Integer queryFollowedAmount(Integer id) {
        Integer followedAmount = null;
        try {
            pstmt = conn.prepareStatement("select count(*) from follow where followed_id = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                followedAmount = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return followedAmount;
    }

    // 我的粉丝
    @Override
    public List<User> queryFollowerList(Integer id) {
        List<User> list = new ArrayList<User>();
        try {
            pstmt = conn.prepareStatement("SELECT uid, username, gender, portrait, status from `user` WHERE uid in (SELECT follower_id FROM follow WHERE followed_id = ?)");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setGender(rs.getString(3));
                user.setPortrait(rs.getString(4));
                user.setStatus(rs.getString(5));
                list.add(user);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return list;
        }
    }

    // 我关注的用户
    @Override
    public List<User> queryFollowedList(Integer id) {
        List<User> list = new ArrayList<User>();
        try {
            pstmt = conn.prepareStatement("SELECT uid, username, gender, portrait, status from `user` WHERE uid in (SELECT followed_id FROM follow WHERE follower_id = ?)");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setGender(rs.getString(3));
                user.setPortrait(rs.getString(4));
                user.setStatus(rs.getString(5));
                list.add(user);
            }
                return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return list;
        }
    }

    @Override
    public boolean follow(Integer id, Integer followId) {
        try {
            conn.setAutoCommit(false);
            // 关注插入关注表
            pstmt = conn.prepareStatement("insert into follow (follower_id, followed_id) values (?,?)");
            pstmt.setInt(1, id);
            pstmt.setInt(2, followId);
            pstmt.executeUpdate(); // 返回数据库储存成功否
            // 更新被关注人粉丝数
            pstmt = conn.prepareStatement("UPDATE `user` SET follower_amount = follower_amount+1 WHERE uid=?");
            pstmt.setInt(1,followId);
            pstmt.executeUpdate();
            // 更新关注人关注数
            pstmt = conn.prepareStatement("UPDATE `user` SET followed_amount = follower_amount+1 WHERE uid=?");
            pstmt.setInt(1,id);
            pstmt.executeUpdate();
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
    public boolean unfollow(Integer id, Integer followId) {
        // trigger
        try {
            conn.setAutoCommit(false);
            // 从关注表删除
            pstmt = conn.prepareStatement("delete from follow where follower_id = ? and  followed_id = ?");
            pstmt.setInt(1, id);
            pstmt.setInt(2, followId);
            pstmt.executeUpdate(); // 返回数据库储存成功否
            // 更新被关注人粉丝数
            pstmt = conn.prepareStatement("UPDATE `user` SET follower_amount = follower_amount-1 WHERE uid=?");
            pstmt.setInt(1,followId);
            pstmt.executeUpdate();
            // 更新关注人关注数
            pstmt = conn.prepareStatement("UPDATE `user` SET followed_amount = follower_amount-1 WHERE uid=?");
            pstmt.setInt(1,id);
            pstmt.executeUpdate();
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
    public Map checkFollow(Integer id, Integer followId) {
        Map result = new HashMap();
        try{
            pstmt = conn.prepareStatement("select * from follow where follower_id = ? and followed_id = ?");
            pstmt.setInt(1, id);
            pstmt.setInt(2, followId);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                result.put("follower_id", id);
                result.put("followed_id", followId);
                result.put("status", 1);
            } else {
                result.put("follower_id", id);
                result.put("followed_id", followId);
                result.put("status", 0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            result.put("follower_id", id);
            result.put("followed_id", followId);
            result.put("status", 2);
        }
        return result;
    }

    public Map checkLogin(String username, String password) {
        Map result = new HashMap();
        // 验证用户名密码
        try {
            pstmt = conn.prepareStatement("select username, password, salt, PBKDF2 from user where username = ?");
            pstmt.setString(1, username);
            // 设置SQL语句参数

            // 设置SQL语句参数
            ResultSet rs = pstmt.executeQuery();
            // 执行查询，返回结果集
            if (rs.next()) { // 执行一行（查）
                // 通过JavaBean保存值
                if(PBKDF2.verify(password, rs.getString(3), rs.getString(4))){
                    User user = new User();
                    user.setUsername(rs.getString(1));
                    result.put("username", username);
                    result.put("status", 10200);
                }else{
                    result.put("username", null);
                    result.put("status", 10400);
                }
                //session加到LoginServlet中了
                // 返回JavaBean对象
            }
            // 验证失败返回null
        } catch (Exception e) {
            e.printStackTrace();
            result.put("username", null);
            result.put("status", 10500);

        }
        return result;
    }

    public Map checkUsername(String name) {
        Map result = new HashMap();
        // 验证用户名密码
        try {
            pstmt = conn.prepareStatement("select uid from user where 'username'= ?");
            // 设置SQL语句参数
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            // 执行查询，返回结果集
            if (rs.next()) {

                result.put("username", name);
                result.put("status", 20200);
            }else {
                result.put("username", null);
                result.put("status", 20400);
            }

        } catch (Exception e) { // 加异常类型
            e.printStackTrace();
            result.put("username", null);
            result.put("status", 20500);
        }
        return result;
    }
}
