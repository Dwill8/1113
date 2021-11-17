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
    public void addUser(String username) {

    }

    @Override
    public void deleteUser(String username) {


    }

}
