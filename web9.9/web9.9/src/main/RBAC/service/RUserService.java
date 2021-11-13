package main.RBAC.service;

import java.util.List;

public interface RUserService {
    public List showUser();
    public void addUser(String username);
    public void deleteUser(String username);

}
