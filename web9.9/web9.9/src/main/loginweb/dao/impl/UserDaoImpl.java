package main.loginweb.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.loginweb.utils.PBKDF2;
import main.loginweb.bean.User;
import main.loginweb.dao.UserDAO;
import main.loginweb.utils.JDBCUtils;


public class UserDaoImpl implements UserDAO {

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
            pstmt = conn.prepareStatement("insert into student(username, password, salt, PBKDF2) values(?,?,?,?)"); //注册其他信息
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

    public Map checkLogin(String username, String password) {
        Map result = new HashMap();
        // 验证用户名密码
        try {
            pstmt = conn.prepareStatement("select username, password, salt, PBKDF2 from student where username = ?");
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
            pstmt = conn.prepareStatement("select * from student where 'name'= ?");
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

    @Override
    public List<User> findByPage(int pageNum, int pageSize) {
        try {
            pstmt = conn.prepareStatement("select * from student order by student_id limit ? offset ?");
            pstmt.setInt(1, pageSize);
            // 设置SQL语句参数
            pstmt.setInt(2, (pageNum - 1) * pageSize);
            // 设置SQL语句参数
            ResultSet rs = pstmt.executeQuery();
            // 执行查询，返回结果集
            List<User> list = new ArrayList<User>();
            //新建列表储存每页用户
            while (rs.next()) {
                // 通过JavaBean保存值
                User user = new User();
                user.setUsername(rs.getString(1));
                user.setName(rs.getString(3));
                user.setGender(rs.getString(4));
                list.add(user); // 用户对象储存进列表
            }
            return list;
            // 返回每页用户列表
        } catch (Exception e) {
            e.printStackTrace();
            return null; //
        }
    }
}
