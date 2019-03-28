package services;

import dal.Dao.RoleDao;
import dal.Dao.TweetDao;
import dal.Dao.UserDao;
import models.Tweet;
import models.UserRole;
import models.User;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.Date;

@Singleton
@Startup
public class startup {

    @Inject
    private UserDao dao;
    @Inject
    private RoleDao roleDao;
    @Inject
    private TweetDao tweetDao;

    public startup(){

    }

    @PostConstruct
    private void initData(){

        UserRole adminUserRole = new UserRole("Administrator");
        UserRole userRole = new UserRole("user");
        roleDao.addRole(adminUserRole);
        roleDao.addRole(userRole);


        User admin = new User();
        admin.setName("Henk");
        admin.setPassword("test");
        admin.setDateOfBirth(new Date());
        admin.setBio("Dit is wat informatie over mezelf");
        admin.setUserRole(adminUserRole);
        admin.setWebsite("www.google.com");

        User user = new User();
        user.setName("Vai");
        user.setDateOfBirth(new Date());
        user.setBio("Ik hou van dit vak");
        user.setUserRole(userRole);
        user.setWebsite("www.vaifreecams.com");

        User frontend = new User();
        frontend.setName("admin");
        frontend.setDateOfBirth(new Date());
        frontend.setBio("Front end is best wel lame");
        frontend.setUserRole(adminUserRole);
        frontend.setWebsite("www.vaifi.dev");


        dao.CreateUser(frontend);
        dao.CreateUser(user);
        dao.CreateUser(admin);


    }
}
