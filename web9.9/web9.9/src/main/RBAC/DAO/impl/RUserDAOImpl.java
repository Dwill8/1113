package main.RBAC.DAO.impl;

import main.RBAC.DAO.RUserDAO;
import main.RBAC.bean.RUser;
import main.RBAC.utils.JDBCUtils;
import main.RBAC.utils.PBKDF2;
import main.loginweb.bean.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.sql.*;

public class RUserDAOImpl implements RUserDAO {

    Connection conn = JDBCUtils.getConn();
    // 数据库连接对象
    PreparedStatement pstmt;

    @Override
    public List pageQueryData(int pageNum, int pageSize) {
        try {
            pstmt = conn.prepareStatement("select * from user limit ? offset ?");
            pstmt.setInt(1, pageSize);
            pstmt.setInt(2, (pageNum - 1) * pageSize);
            ResultSet rs = pstmt.executeQuery();
            // 执行查询，返回结果集
            List<RUser> list = new ArrayList<RUser>();
            //新建列表储存每页用户
            while (rs.next()) {
                // 通过JavaBean保存值
                RUser rUser = new RUser();
                rUser.setId(rs.getInt(1));
                rUser.setUsername(rs.getString(2));
                rUser.setPassword(rs.getString(3));
                list.add(rUser); // 用户对象储存进列表
            }
            return list;
        } catch (Exception e) {

            e.printStackTrace();
            return null;

        }
    }

    @Override
    public boolean addUser(RUser rUser) {

        try {
            String username = rUser.getUsername();
            String password = rUser.getPassword();
            String salt = PBKDF2.getSalt();
            String pbkdf2Password = PBKDF2.getPBKDF2(password, salt);
            pstmt = conn.prepareStatement("insert into user (username, password, salt, PBKDF2) values(?,?,?,?)"); //注册其他信息
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
    public boolean deleteUser(String username) {
        return true;
    }

    @Override
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
