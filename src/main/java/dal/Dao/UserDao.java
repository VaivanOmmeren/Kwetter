package dal.Dao;

import models.User;

import java.util.List;


public interface UserDao {

    User EditUser(User u);
    User CreateUser(User user);
    boolean RemoveUser(String id);
    User getUserByID(String id);
    List<User> getAllUsers();
    List<User> getFollowers(String id);
    List<User> getFollowing(String id);
    boolean followUser(String id, String followerId);
    boolean unfollowUser(String id, String unfollowID);
    List<User> getUsersByID(List<String> id);
    User getUserByName(String name);
}
