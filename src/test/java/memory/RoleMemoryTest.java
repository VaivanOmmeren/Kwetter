package memory;

import dal.contexts.Memory.MemoryRoleDao;
import models.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RoleMemoryTest {

    MemoryRoleDao memDao;

    @BeforeEach
    void setUp(){
        memDao = new MemoryRoleDao();
        memDao.setRoles();
    }

    @Test
    void createRole(){
        UserRole role = new UserRole("Test");
        role.setId("44");

        assertEquals(role, memDao.addRole(role));
        assertNull(memDao.addRole(role));
    }

    @Test
    void editRole(){
        UserRole role = new UserRole("Test");
        role.setId("22");
        memDao.addRole(role);

        assertEquals(role, memDao.editRole(role));
    }

    @Test
    void getRole(){
        UserRole role = new UserRole("test");
        role.setId("44");
        memDao.addRole(role);

        assertEquals(role, memDao.getRole("44"));
        assertNull(memDao.getRole("33"));
    }

    @Test
    void removeRole(){
        UserRole role = new UserRole("test");
        role.setId("44");
        memDao.addRole(role);

        assertEquals(role, memDao.getRole("44"));
        assertTrue(memDao.removeRole("44"));
        assertFalse(memDao.removeRole("44"));
        assertNull(memDao.getRole("44"));
    }

    @Test
    void getAllRoles(){

        UserRole role = new UserRole("test");

        assertEquals(5, memDao.getAll().size());
        memDao.addRole(role);
        assertEquals(6, memDao.getAll().size());
    }
}
