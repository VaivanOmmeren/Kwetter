package services;

import dal.Dao.RoleDao;
import models.UserRole;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class RoleService {

    public RoleService(){

    }

    @Inject
    RoleDao roleDao;

    public UserRole createRole(UserRole r){
        return roleDao.addRole(r);
    }

    public boolean removeRole(String id) {
        return roleDao.removeRole(id);
    }

    public UserRole getRole(String id) {
        return roleDao.getRole(id);
    }

    public UserRole updateRole(UserRole r) {
        return roleDao.editRole(r);
    }

    public List<UserRole> getAll() {
        return roleDao.getAll();
    }
}
