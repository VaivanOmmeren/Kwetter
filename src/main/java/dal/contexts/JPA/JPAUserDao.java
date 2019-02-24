package dal.contexts.JPA;

import dal.Dao.UserDao;
import models.User;

import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import java.util.List;

@Stateless
@Alternative
public class JPAUserDao implements UserDao {
    public User EditUser(User u) {
        return null;
    }

    public User CreateUser(User user) {
        return null;
    }

    public boolean RemoveUser(String id) {
        return false;
    }

    public User getUserByID(String id) {
        return null;
    }

    public List<User> getAllUsers() {
        return null;
    }

    public List<User> getFollowers(String id) {
        return null;
    }

    public List<User> getFollowing(String id) {
        return null;
    }

    public boolean followUser(String id, String followerId) {
        return false;
    }

    public boolean unfollowUser(String id, String unfollowID) {
        return false;
    }

    public List<User> getUsersByID(List<String> id) {
        return null;
    }
}
