package dal.contexts.Memory;

import dal.Dao.RoleDao;
import models.UserRole;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.inject.Alternative;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Stateful
@Alternative
public class MemoryRoleDao implements RoleDao {

    List<UserRole> userRoles = new ArrayList<UserRole>();

    public MemoryRoleDao() {

    }

    @PostConstruct
    private void setRoles() {
        for (int x = 0; x < 5; x++) {
            UserRole r = new UserRole("test");
            r.setId(UUID.randomUUID().toString());
            userRoles.add(r);
        }
    }

    public UserRole getRole(String id) {
        for (UserRole r : userRoles) {
            if (r.getId().equals(id)) {
                return r;
            }
        }

        return null;
    }

    public UserRole addRole(UserRole r) {

        int index = -1;
        for (UserRole userRole : userRoles) {
            if (userRole.getId().equals(r.getId())) {
                index = userRoles.indexOf(userRole);
                break;
            }
        }

        if (index == -1) {
            userRoles.add(r);
            System.out.println(userRoles);
            return r;
        } else {
            return null;
        }
    }

    public boolean removeRole(String id) {

        int index = -1;
        for (UserRole r : userRoles) {
            if (r.getId().equals(id)) {
                index = userRoles.indexOf(r);
                break;
            }
        }

        if (index != -1) {
            userRoles.remove(index);
            return true;
        } else {
            return false;
        }
    }

    public UserRole editRole(UserRole r) {

        for(UserRole userRole : userRoles){
            if(userRole.getId().equals(r.getId())){
                userRole.setName(r.getName());
                return userRole;
            }
        }
        return null;
    }

    public List<UserRole> getAll() {
        return userRoles;
    }
}
