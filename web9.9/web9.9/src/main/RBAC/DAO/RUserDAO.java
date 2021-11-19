package main.RBAC.DAO;

import main.RBAC.bean.RUser;

import java.util.List;
import java.util.Map;

public interface RUserDAO {
    public List pageQueryData(int pageNum, int pageSize);
    public boolean addUser(RUser rUser);
    public boolean deleteUser(String username);
    public Map checkUsername(String name);
}
