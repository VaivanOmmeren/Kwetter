package services;

import dal.contexts.JPA.JPAUserDao;
import models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private static JPAUserDao mockDao;
    @InjectMocks
    private static UserService uService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void editUser() {

        User u = new User();
        u.setId("20");

        when(uService.editUser(u)).thenReturn(u);

        assertEquals(u, uService.editUser(u));
    }


    @Test
    void followUser() {

        String id1 = "200";
        String id2 = "300";

        when(uService.followUser(id1,id2)).thenReturn(true);

        assertTrue(uService.followUser("200", "300"));
    }

    @Test
    void createUser() {
        User u = new User();
        when(uService.CreateUser(u)).thenReturn(u);

        assertEquals(u, uService.CreateUser(u));
    }

    @Test
    void removeUser() {
        User u = new User();

        when(uService.RemoveUser(u.getId())).thenReturn(true);

        assertTrue(uService.RemoveUser(u.getId()));

    }

    @Test
    void getUserByID() {
        User u = new User();
        u.setId("432");

        when(uService.getUserByID(u.getId())).thenReturn(u);

        assertEquals(u, uService.getUserByID(u.getId()));
    }

    @Test
    void getUserByName() {
        User u = new User("Vai");

        when(uService.getUserByName(u.getName())).thenReturn(u);

        assertEquals(u, uService.getUserByName(u.getName()));
    }

    @Test
    void getAllUsers() {

        List<User> users = new ArrayList<User>();
        users.add(new User());
        users.add(new User());

        when(uService.getAllUsers()).thenReturn(users);

        assertEquals(users, uService.getAllUsers());
    }

    @Test
    void getFollowers() {
    }

    @Test
    void getFollowing() {
    }
}