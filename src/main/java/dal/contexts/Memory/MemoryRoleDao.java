package dal.contexts.Memory;

import dal.Dao.RoleDao;
import models.Role;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Default;
import java.util.ArrayList;
import java.util.List;

@Stateful
@Default
public class MemoryRoleDao implements RoleDao {

    List<Role> roles = new ArrayList<Role>();

    public MemoryRoleDao() {

    }

    @PostConstruct
    private void setRoles() {
        for (int x = 0; x < 5; x++) {
            Role r = new Role("test", ""+x);
            roles.add(r);
        }
    }

    public Role getRole(String id) {
        for (Role r : roles) {
            if (r.getID().equals(id)) {
                return r;
            }
        }

        return null;
    }

    public Role addRole(Role r) {

        int index = -1;
        for (Role role : roles) {
            if (role.getID().equals(r.getID())) {
                index = roles.indexOf(role);
                break;
            }
        }

        if (index == -1) {
            roles.add(r);
            System.out.println(roles);
            return r;
        } else {
            return null;
        }
    }

    public boolean removeRole(String id) {

        int index = -1;
        for (Role r : roles) {
            if (r.getID().equals(id)) {
                index = roles.indexOf(r);
                break;
            }
        }

        if (index != -1) {
            roles.remove(index);
            return true;
        } else {
            return false;
        }
    }

    public Role editRole(Role r) {

        for(Role role : roles){
            if(role.getID().equals(r.getID())){
                role.setName(r.getName());
                return role;
            }
        }
        return null;
    }

    public List<Role> getAll() {
        return roles;
    }
}
