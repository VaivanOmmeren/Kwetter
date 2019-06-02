package services;

import dal.Dao.RoleDao;
import dal.Dao.TweetDao;
import dal.Dao.UserDao;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import models.Tweet;
import models.User;
import models.UserRole;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.security.Key;
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
        admin.setUserRole(roleDao.getRoleByName("Administrator"));
        admin.setWebsite("www.google.com");

        User user = new User();
        user.setName("Vai");
        user.setPassword("test");
        user.setDateOfBirth(new Date());
        user.setBio("Ik hou van dit vak");
        user.setUserRole(roleDao.getRoleByName("user"));
        user.setWebsite("www.vaifreecams.com");

        User frontend = new User();
        user.setPassword("test");
        frontend.setName("admin");
        frontend.setDateOfBirth(new Date());
        frontend.setBio("Front end is best wel lame");
        frontend.setUserRole(adminUserRole);
        frontend.setWebsite("www.vaifi.dev");


        dao.CreateUser(frontend);
        dao.CreateUser(user);
        dao.CreateUser(admin);

        User henk = dao.getUserByName("Henk");
        User vai = dao.getUserByName("Vai");
        User adminU = dao.getUserByName("admin");

        for (int i = 0; i < 10; i++) {
            Tweet tweet = new Tweet("Dit is een test"  + i, henk.getId());
            tweetDao.CreateTweet(tweet);

            Tweet tweet2 = new Tweet("Dit is een test voor vai" + i, vai.getId());
            tweetDao.CreateTweet(tweet2);

            Tweet tweet3 = new Tweet("dit is tweet voor admin" + i, adminU.getId());
            tweetDao.CreateTweet(tweet3);
        }

        dao.followUser(henk.getName(), vai.getName());
        dao.followUser(henk.getName(), adminU.getName());


    }
}
