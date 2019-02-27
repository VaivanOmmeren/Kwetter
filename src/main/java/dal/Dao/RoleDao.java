package dal.Dao;

import models.UserRole;

import java.util.List;


public interface RoleDao {

    UserRole getRole(String id);
    UserRole addRole(UserRole r);
    boolean removeRole(String id);
    UserRole editRole(UserRole r);
    List<UserRole> getAll();
}
