package main.RBAC.DAO.impl;

import main.RBAC.DAO.RUserDAO;
import main.RBAC.bean.RUser;
import main.RBAC.utils.JDBCUtils;
import main.loginweb.bean.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
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
    public void addUser(String username) {

    }

    @Override
    public void deleteUser(String username) {

    }
}
