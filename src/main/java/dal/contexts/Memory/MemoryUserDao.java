package dal.contexts.Memory;

import dal.Dao.UserDao;
import models.User;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.inject.Alternative;
import java.util.ArrayList;
import java.util.List;

@Stateful
@Alternative
public class MemoryUserDao implements UserDao {

    private ArrayList<User> Users = new ArrayList<User>();

    public MemoryUserDao() {

    }

    @PostConstruct
    public void fillUser() {

        for (int x = 0; x < 10; x++) {
            User user = new User("user" + x);
            Users.add(user);

        }
    }


    public User EditUser(User u) {
        for(User user: Users){
            if(user.getName().equals(u.getName())){
                user.setUserRole(u.getUserRole());
                user.setWebsite(u.getWebsite());
                user.setBio(u.getBio());
                if(u.getDateOfBirth() != null) user.setDateOfBirth(u.getDateOfBirth());
                user.setPassword(u.getPassword());

                return user;
            }
        }
        return null;
    }

    public User CreateUser(User user) {

        int index = -1;

        for (User u : Users) {
            if (u.getName().equals(user.getName())) {
                index = Users.indexOf(u);
                break;
            }
        }

        if (index == -1) {
            Users.add(user);
            return user;
        } else {
            return null;
        }

    }

    public boolean RemoveUser(String id) {
        int index = -1;
        for (User u : Users) {
            if (u.getName().equals(id)) {
                index = Users.indexOf(u);
                break;
            }
        }

        if (index != -1) {
            Users.remove(index);
            return true;
        } else {
            return false;
        }
    }

    public User getUserByID(String id) {
        for (User u : Users) {
            if (u.getName().equals(id)) {
                return u;
            }
        }
        return null;
    }

    public List<User> getAllUsers() {
        return Users;
    }

    public List<User> getFollowers(String id) {
        List<User> followers = new ArrayList<User>();

        for (User u : Users) {
            for (User following : u.getFollowing()) {
                if (following.getName().equals(id)) {
                    followers.add(u);
                }
            }
        }

        return followers;
    }

    public List<User> getFollowing(String id) {
        for (User u : Users) {
            if (u.getName().equals(id)) {
                return u.getFollowing();
            }
        }
        return null;
    }

    public boolean followUser(String id, String followerId) {

        User u = getUserByID(id);
        boolean alreadyFollowing = false;

        for (User following : u.getFollowing()) {
            if (following.getName().equals(followerId)) {
                alreadyFollowing = true;
                break;
            }
        }

        if (alreadyFollowing) {
            return false;
        } else {
            u.addFollowing(getUserByID(followerId));
            return true;
        }
    }

    public boolean unfollowUser(String id, String unfollowID) {
        return false;
    }

    public List<User> getUsersByID(List<String> id) {
        List<User> requestedUsers = new ArrayList<User>();

        for(String s : id){
            for(User u: Users){
                if(s.equals(u.getName())){
                    requestedUsers.add(u);
                }
            }
        }

        return requestedUsers;
    }

    public User getUserByName(String name) {
        return null;
    }

}
