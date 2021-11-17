package main.RBAC.DAO;

import java.util.List;
import java.util.Map;

public interface RUserDAO {
    public List pageQueryData(int pageNum, int pageSize);
    public void addUser(String username);
    public void deleteUser(String username);
}
