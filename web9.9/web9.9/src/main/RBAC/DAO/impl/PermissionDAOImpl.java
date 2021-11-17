package main.RBAC.DAO.impl;

import main.RBAC.DAO.PermissionDAO;
import main.RBAC.bean.Permission;
import main.RBAC.bean.Role;
import main.RBAC.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PermissionDAOImpl implements PermissionDAO {

    Connection conn = JDBCUtils.getConn();
    // 数据库连接对象
    PreparedStatement pstmt;

    @Override
    public List showPermission() {
        try {
            pstmt = conn.prepareStatement("select * from permission");
            ResultSet rs = pstmt.executeQuery();
            // 执行查询，返回结果集
            List<Permission> list = new ArrayList<Permission>();
            //新建列表储存每页用户
            while (rs.next()) {
                // 通过JavaBean保存值
                Permission permission = new Permission();
                permission.setPid(rs.getInt(1));
                permission.setpName(rs.getString(2));
                list.add(permission); // 用户对象储存进列表
            }
            return list;
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean addPermission(Integer rid, Integer pid) {

        try {
            pstmt = conn.prepareStatement("insert into u_r ('rid', 'pid') values(?,?)");
            pstmt.setInt(1, rid);
            pstmt.setInt(2, pid);
            pstmt.executeUpdate(); // 返回数据库储存成功否

            return true;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deletePermission(Integer rid, Integer pid) {
        try {
            pstmt = conn.prepareStatement("delete from u_r where 'rid'=? and 'pid'=?)");
            pstmt.setInt(1, rid);
            pstmt.setInt(2, pid);
            pstmt.executeUpdate(); // 返回数据库储存成功否

            return true;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }
}
