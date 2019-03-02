package memory;

import dal.contexts.Memory.MemoryUserDao;
import models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class UserMemoryTest {

    MemoryUserDao memDao;

    @BeforeEach
    void setUp(){
        memDao = new MemoryUserDao();
        memDao.fillUser();
    }

    @Test
    void editUserTest() {
        User u = new User();

        memDao.CreateUser(u);

        u.setName("test");

        assertEquals(u, memDao.EditUser(u));
    }

    @Test
    void createUserTest() {
        User u = new User();
        assertEquals(u, memDao.CreateUser(u));
    }

    @Test
    void removeUserTest(){

        User u = new User("Vai");
        memDao.CreateUser(u);

        assertTrue(memDao.RemoveUser("Vai"));
        assertFalse(memDao.RemoveUser("Henk"));
    }

    @Test
    void getUserByName(){
        User u = new User("Piet");
        memDao.CreateUser(u);

        assertEquals(u, memDao.getUserByName("Piet"));
    }

    @Test
    void getUserByIdTest(){

        User u = new User("Vai");
        u.setId("22");
        memDao.CreateUser(u);

        assertEquals(u, memDao.getUserByID("22"));
    }

    @Test
    void getAllUsersTest(){
        assertEquals(10, memDao.getAllUsers().size());
    }

    @Test
    void getFollowersTest(){
        memDao.getAllUsers().get(0).addFollowing(memDao.getAllUsers().get(1));

        assertEquals(1, memDao.getFollowers(memDao.getAllUsers().get(1).getName()).size());
    }

    @Test
    void getFollowingTest(){
        memDao.getAllUsers().get(0).addFollowing(memDao.getAllUsers().get(2));

        assertEquals(1, memDao.getAllUsers().get(0).getFollowing().size());
    }

    @Test
    void followUser(){

        User u = new User("Karel");
        u.setId("44");
        User u2 = new User("Piet");
        u.setId("45");

        memDao.CreateUser(u);
        memDao.CreateUser(u2);

        assertTrue(memDao.followUser(u.getName(), u2.getName()));
        assertFalse(memDao.followUser(u.getName(), u2.getName()));
    }

    @Test
    void unFollowUser(){

        User u = new User("test1");
        u.setId("33");
        User u2 = new User("test2");
        u2.setId("44");

        memDao.CreateUser(u);
        memDao.CreateUser(u2);

        memDao.followUser(u.getName(), u2.getName());

        assertTrue(memDao.unfollowUser(u.getName(), u2.getName()));
        assertFalse(memDao.unfollowUser(u.getName(), u2.getName()));
    }

    @Test
    void getUsersByIds(){

        ArrayList<String> ids = new ArrayList<String>();
        for(int x = 0; x < 10; x ++){
            ids.add("user"+x);
        }

        assertEquals(10, memDao.getUsersByID(ids).size());
    }

}
