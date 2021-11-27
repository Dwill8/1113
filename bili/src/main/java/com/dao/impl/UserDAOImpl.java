package com.dao.impl;

import com.bean.User;
import com.dao.UserDAO;
import com.utils.JDBCUtils;
import com.utils.PBKDF2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
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
        int loginCheckCount = 0;
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
    public Integer queryFollowerAmount(int id) {
        Integer followerAmount = null;
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

    @Override
    public Integer queryFollowedAmount(int id) {
        Integer followedAmount = null;
        try {
            pstmt = conn.prepareStatement("select count(*) from follow where followed_id = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                followedAmount = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return followedAmount;
    }

    @Override
    public Integer follow(int id, int followId) {
        return 0;
    }

    @Override
    public Integer unfollow(int id, int followId) {
        return 0;
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
                    result.put("name", username);
                    result.put("status", 10200);
                }else{
                    result.put("name", null);
                    result.put("status", 10400);
                }
                //session加到LoginServlet中了
                // 返回JavaBean对象
            }
            // 验证失败返回null（记录失败次数）在queryUserByUsernameAndPassword记录
        } catch (Exception e) {
            e.printStackTrace();
            result.put("name", null);
            result.put("status", 10500);

        }
        return result;
    }

    public Map checkUsername(String name) {
        Map result = new HashMap();
        // 验证用户名密码
        try {
            pstmt = conn.prepareStatement("select * from user where 'username'= ?");
            // 设置SQL语句参数
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            // 执行查询，返回结果集
            if (rs.next()) {

                result.put("name", name);
                result.put("status", 20200);
            }else {
                result.put("name", null);
                result.put("status", 20400);
            }

        } catch (Exception e) { // 加异常类型
            e.printStackTrace();
            result.put("name", null);
            result.put("status", 20500);
        }
        return result;
    }
}
