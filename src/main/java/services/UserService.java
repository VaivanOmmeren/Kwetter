package services;

import dal.Dao.UserDao;
import models.User;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class UserService {

    @Inject
    private UserDao userDaoImpl;

    public UserService(){

    }

    public User editUser(User u ){
        return userDaoImpl.EditUser(u);
    }

    public boolean followUser(String userId, String userToFollow){
        return userDaoImpl.followUser(userId, userToFollow);
    }

    public boolean unfollowUser(String username, String userToUnfollow){
        return userDaoImpl.unfollowUser(username, userToUnfollow);
    }

    public User CreateUser(User user) {
        return userDaoImpl.CreateUser(user);
    }

    public boolean RemoveUser(String userId) {
        return userDaoImpl.RemoveUser(userId);
    }

    public User getUserByID(String id) {
        return userDaoImpl.getUserByID(id);
    }

    public User getUserByName(String name) { return userDaoImpl.getUserByName(name);}

    public List<User> getAllUsers() {
        return userDaoImpl.getAllUsers();
    }

    public List<User> getFollowers(User u) {
        return userDaoImpl.getFollowers(u.getId());
    }

    public List<User> getFollowing(User u) {
        return userDaoImpl.getFollowing(u.getId());
    }

    public User loginUser(String name, String password){
        return userDaoImpl.loginUser(name, password);
    }
}
