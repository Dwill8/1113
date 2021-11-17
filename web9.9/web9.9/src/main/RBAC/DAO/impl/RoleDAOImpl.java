package main.RBAC.DAO.impl;

import main.RBAC.DAO.RoleDAO;
import main.RBAC.bean.RUser;
import main.RBAC.bean.Role;
import main.RBAC.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoleDAOImpl implements RoleDAO {
    Connection conn = JDBCUtils.getConn();
    // 数据库连接对象
    PreparedStatement pstmt;
    @Override
    public List showRole() {
        try {
            pstmt = conn.prepareStatement("select * from role");
            ResultSet rs = pstmt.executeQuery();
            // 执行查询，返回结果集
            List<Role> list = new ArrayList<Role>();
            //新建列表储存每页用户
            while (rs.next()) {
                // 通过JavaBean保存值
                Role role = new Role();
                role.setRid(rs.getInt(1));
                role.setName(rs.getString(2));
                list.add(role); // 用户对象储存进列表
            }
            return list;
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean addRole(Integer uid, Integer rid) {
        try {
            pstmt = conn.prepareStatement("insert into u_r ('uid', 'rid') values(?,?)");
            pstmt.setInt(1, uid);
            pstmt.setInt(2, rid);
            pstmt.executeUpdate(); // 返回数据库储存成功否

            return true;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteRole(Integer uid, Integer rid) {
        try {
            pstmt = conn.prepareStatement("delete from u_r where 'uid'=? and 'rid'=?)");
            pstmt.setInt(1, uid);
            pstmt.setInt(2, rid);
            pstmt.executeUpdate(); // 返回数据库储存成功否

            return true;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }
}
