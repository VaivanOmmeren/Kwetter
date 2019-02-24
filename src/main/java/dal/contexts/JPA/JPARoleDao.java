package dal.contexts.JPA;

import dal.Dao.RoleDao;
import models.Role;

import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Default;
import java.util.List;

@Stateless
@Alternative
public class JPARoleDao implements RoleDao {
    public Role getRole(String id) {
        return null;
    }

    public Role addRole(Role r) {
        return null;
    }

    public boolean removeRole(String id) {
        return false;
    }

    public Role editRole(Role r) {
        return null;
    }

    public List<Role> getAll() {
        return null;
    }
}
