package main.RBAC.service;

import main.RBAC.bean.RUser;

import java.util.List;
import java.util.Map;

public interface RUserService {
    public List<RUser> showUser(Integer pageNum, Integer pagesize);
    public Map addUser(RUser rUser);
    public boolean deleteUser(String username);


}
