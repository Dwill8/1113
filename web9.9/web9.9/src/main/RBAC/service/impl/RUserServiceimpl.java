package main.RBAC.service.impl;

import main.RBAC.DAO.RUserDAO;
import main.RBAC.bean.RUser;
import main.RBAC.service.RUserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RUserServiceImpl implements RUserService {

    private RUserDAO rUserDAO;

    @Override
    public List<RUser> showUser(Integer pageNum, Integer pagesize) {
        //此处pageNum默认为1
        List<RUser> list = rUserDAO.pageQueryData(pageNum, pagesize);
        return list;
    }

    @Override
    public Map addUser(RUser rUser) {
        Map result = new HashMap();
        // 验证设置的账号密码是否为空
        if(rUser.getUsername() == null || rUser.getPassword() == null) {

            result.put("name", rUser.getUsername());
            result.put("status", 20200);
        } else{
            result.put("name", null);
            result.put("status", 20401);
        }
        if(rUserDAO.checkUsername(rUser.getUsername()).get("staus") == (Integer)20200) {
            result.put("name", rUserDAO.checkUsername(rUser.getUsername()).get("name"));
            result.put("status", 20200)
        } else {
            result.put("name", null);
            result.put("status", 20402);
        }
        return result;
    }

    @Override
    public boolean deleteUser(String username) {
        return true;

    }

}
