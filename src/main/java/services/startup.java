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
        user.setPassword("test");
        user.setDateOfBirth(new Date());
        user.setBio("Ik hou van dit vak");
        user.setUserRole(userRole);
        user.setWebsite("www.vaifreecams.com");


        dao.CreateUser(user);
        dao.CreateUser(admin);

        Tweet tweet = new Tweet("dit is een test tweet", "20");
        tweetDao.CreateTweet(tweet);


    }
}