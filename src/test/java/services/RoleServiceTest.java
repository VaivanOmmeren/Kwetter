package services;

import dal.contexts.JPA.JPARoleDao;
import models.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class RoleServiceTest {

    @Mock
    private static JPARoleDao mockDao;
    @InjectMocks
    private static RoleService rService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createRole() {

        UserRole r = new UserRole("test");

        when(rService.createRole(r)).thenReturn(r);

        assertEquals(r, rService.createRole(r));
    }

    @Test
    void removeRole() {


        UserRole r = new UserRole("test");

        when(rService.removeRole(r.getId())).thenReturn(true);
        assertTrue(rService.removeRole(r.getId()));
    }

    @Test
    void getRole() {

        UserRole r = new UserRole("test");
        when(rService.getRole(r.getId())).thenReturn(r);

        assertEquals(r, rService.getRole(r.getId()));
    }

    @Test
    void updateRole() {

        UserRole r = new UserRole("test");
        r.setName("geentest");

        when(rService.updateRole(r)).thenReturn(r);

        assertEquals(r, rService.updateRole(r));
    }

    @Test
    void getAll() {

        List<UserRole> roles = new ArrayList<UserRole>();
        roles.add(new UserRole());
        roles.add(new UserRole());

        when(rService.getAll()).thenReturn(roles);

        assertEquals(roles, rService.getAll());
    }
}