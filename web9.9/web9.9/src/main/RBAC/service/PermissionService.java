package main.RBAC.service;

import java.util.List;
import java.util.Map;

public interface PermissionService {
    public List showPermission();
    public void addPermission(Integer rid, Integer pid);
    public void deletePermission(Integer rid, Integer pid);
}
