package models;

import models.UserRole;
import models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    List<User> Users = new ArrayList<User>();

    @BeforeEach
    void setUp(){
        for(int i = 0; i < 10; i++){
           User user = new User("Vai" +i , "test", new UserRole(), new Date(), "Hoi ik ben een test", "www.google.com");
           Users.add(user);
        }
    }

    @Test
    void editUser(){
        User user = Users.get(0);
        user.setName("Henk");
        user.setPassword("haha");
        user.setWebsite("www.reddit.com");

        assertEquals("Henk", user.getName());
        assertEquals("haha", user.getPassword());
        assertEquals("www.reddit.com", user.getWebsite());
    }

    @Test
    void followUser(){
    }

    @Test
    void removeUser(){
    }
}
