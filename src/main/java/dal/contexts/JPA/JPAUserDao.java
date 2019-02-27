package dal.contexts.JPA;

import dal.Dao.UserDao;
import models.User;

import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Default
@Named
public class JPAUserDao implements UserDao {


    public JPAUserDao(){

    }

    @PersistenceContext
    private EntityManager em;


    public User EditUser(User u) {
        em.merge(u);

        return getUserByID(u.getId());
    }

    public User CreateUser(User user) {

        em.persist(user);
        return user;
    }

    public boolean RemoveUser(String id) {

        em.remove(getUserByID(id));
        return true;
    }

    public User getUserByID(String id) {
        TypedQuery query = em.createNamedQuery("users.getUserById", User.class);
        query.setParameter("id", id);
        List<User> users = query.getResultList();
        return users.get(0);
    }

    public User getUserByName(String name){
        TypedQuery query = em.createNamedQuery("users.getUserByName", User.class);
        query.setParameter("name", name);
        List<User> users = query.getResultList();
        return users.get(0);
    }

    public List<User> getAllUsers() {
        Query query = em.createQuery("SELECT s FROM User s");
        return new ArrayList<User>(query.getResultList());
    }

    public List<User> getFollowers(String id) {
        Query query = em.createNativeQuery("SELECT (u.id, u.name, u.bio, u.DateOfBirth, u.password, u.website, u.userRole) FROM  User u INNER JOIN users_users uu on u.id = uu.following_id WHERE uu.users_id = :id");
        query.setParameter("id", id);
        return new ArrayList<User>(query.getResultList());

    }

    public List<User> getFollowing(String id) {
        return null;
    }

    public boolean followUser(String id, String followerId) {
        User user = getUserByName(id);
        User followThis =  getUserByName(followerId);
        user.addFollowing(followThis);

        EditUser(user);
        return true;
    }

    public boolean unfollowUser(String id, String name) {
        User user = getUserByName(id);
        User unfollowThis = getUserByName(name);

        user.unFollow(unfollowThis);

        EditUser(user);

        return true;
    }

    public List<User> getUsersByID(List<String> id) {
        List<User> users = new ArrayList<User>();

        for(String x: id){
            users.add(getUserByID(x));
        }

        return users;
    }

}
