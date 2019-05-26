package dal.contexts.JPA;

import dal.Dao.UserDao;
import models.User;
import models.UserDTO;

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
        TypedQuery<User> query = em.createQuery("SELECT NEW models.User(u.id, u.name, u.DateOfBirth, u.bio, u.website, u.userRole) " +
                "FROM User u " +
                "WHERE u.id = :id", User.class);
        query.setParameter("id", id);
        List<User> users = query.getResultList();
        return users.get(0);
    }

    public User getUserByName(String name){
        TypedQuery<User> query = em.createQuery("SELECT NEW models.User(u.id, u.name, u.DateOfBirth, u.bio, u.website, u.userRole) " +
                "FROM User u" +
                " WHERE u.name = :name", User.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    public User loginUser(String name, String password) {
        TypedQuery<User> query = em.createQuery("SELECT NEW models.User(u.id, u.name, u.DateOfBirth, u.bio, u.website, u.userRole)FROM User u " +
                "WHERE u.name = :name AND u.password = :password", User.class);
        query.setParameter("name", name);
        query.setParameter("password", password);
        return query.getSingleResult();
    }

    public List<User> getAllUsers() {
        TypedQuery<User> query = em.createQuery("SELECT NEW models.User(u.id, u.name, u.DateOfBirth, u.bio, u.website, u.userRole) FROM User u", User.class);
        return new ArrayList<User>(query.getResultList());
    }

    public List<User> getFollowers(String id) {
        TypedQuery<User> query = em.createQuery("SELECT NEW models.User(u.id, u.name, u.DateOfBirth, u.bio, u.website, u.userRole) " +
                "FROM User u " +
                "JOIN u.following AS f " +
                "WHERE f.id = :id", User.class);
        query.setParameter("id", id);
        return new ArrayList<User>(query.getResultList());

    }

    public List<User> getFollowing(String id) {
        TypedQuery<User> query  = em.createQuery("SELECT NEW models.User(f.id, f.name, f.DateOfBirth, f.bio, f.website, f.userRole) " +
                "FROM User u " +
                "JOIN u.following AS f " +
                "WHERE u.id = :id", User.class);
        query.setParameter("id", id);
        return new ArrayList<User>(query.getResultList());
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
