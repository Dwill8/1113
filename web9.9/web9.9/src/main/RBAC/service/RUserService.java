package main.RBAC.service;

import main.RBAC.bean.RUser;

import java.util.List;

public interface RUserService {
    public List<RUser> showUser();
    public void addUser(String username);
    public void deleteUser(String username);


}
