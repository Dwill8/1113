package main.RBAC.service.impl;

import main.RBAC.DAO.RUserDAO;
import main.RBAC.bean.RUser;
import main.RBAC.service.RUserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RUserServiceImpl implements RUserService {

    private RUserService rUserService = new RUserServiceImpl();
    private RUserDAO rUserDAO;

    @Override
    public List showUser(Integer pageNum, Integer pagesize) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", (pageNum - 1) * pagesize);
        List<RUser> list = rUserService.pageQueryData(map);
        //TODO
        return list;
    }

    @Override
    public void addUser(String username) {

    }

    @Override
    public void deleteUser(String username) {

    }

    public List<RUser> pageQueryData(Map<String, Object> map) {
        return rUserDAO.pageQueryData(map);
    }
}
