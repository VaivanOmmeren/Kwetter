package dal.Dao;

import models.Role;

import java.util.List;

public interface RoleDao {

    Role getRole(String id);
    Role addRole(Role r);
    boolean removeRole(String id);
    Role editRole(Role r);
    List<Role> getAll();
}
