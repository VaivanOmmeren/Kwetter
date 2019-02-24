package services;

import dal.Dao.RoleDao;
import models.Role;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class RoleService {

    public RoleService(){

    }

    @Inject
    RoleDao roleDao;

    public Role createRole(Role r){
        return roleDao.addRole(r);
    }

    public boolean removeRole(String id) {
        return roleDao.removeRole(id);
    }

    public Role getRole(String id) {
        return roleDao.getRole(id);
    }

    public Role updateRole(Role r) {
        return roleDao.editRole(r);
    }

    public List<Role> getAll() {
        return roleDao.getAll();
    }
}
