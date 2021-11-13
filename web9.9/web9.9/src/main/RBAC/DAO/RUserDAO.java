package main.RBAC.DAO;

import java.util.List;

public interface RUserDAO {
    public List showUser();
    public void addUser(String username);
    public void deleteUser(String username);
}
