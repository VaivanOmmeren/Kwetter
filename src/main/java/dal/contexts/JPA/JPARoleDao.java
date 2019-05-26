package dal.contexts.JPA;

import dal.Dao.RoleDao;
import models.UserRole;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Default
public class JPARoleDao implements RoleDao {

    @PersistenceContext
    EntityManager em;

    public JPARoleDao(){

    }

    public UserRole getRole(String id) {
        TypedQuery query = em.createNamedQuery("userRole.getRoleById", UserRole.class);
        query.setParameter("id", id);
        List<UserRole> roles = query.getResultList();
        return roles.get(0);
    }

    public UserRole getRoleByName(String name){
        TypedQuery<UserRole> query = em.createQuery("SELECT r FROM UserRole r WHERE name = :name", UserRole.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    public UserRole addRole(UserRole r) {
        em.persist(r);
        return r;
    }

    public boolean removeRole(String id) {
        em.remove(getRole(id));
        return true;
    }

    public UserRole editRole(UserRole r) {
        em.merge(r);
        return r;
    }

    public List<UserRole> getAll() {
        Query query = em.createQuery("SELECT s from UserRole s");
        return new ArrayList<UserRole>(query.getResultList());
    }
}
