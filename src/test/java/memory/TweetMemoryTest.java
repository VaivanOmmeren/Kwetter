package memory;

import dal.contexts.Memory.MemoryTweetDao;
import models.Tweet;
import models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TweetMemoryTest {

    MemoryTweetDao memDao;

    @BeforeEach
    void setUp(){
        memDao = new MemoryTweetDao();
        memDao.createTweets();
    }

    @Test
    void getTweetByID(){
        Tweet tweet = new Tweet("test", "5");
        tweet.setID("100");
        memDao.CreateTweet(tweet);

        assertEquals(tweet, memDao.getTweetById("100"));
    }

    @Test
    void createTweet(){
        Tweet t = new Tweet("Test", "400");
        t.setID("4500");

        assertEquals(t, memDao.CreateTweet(t));
    }

    @Test
    void RemoveTweet(){
        Tweet t = new Tweet("test", "4001");
        t.setID("40325");
        memDao.CreateTweet(t);

        assertEquals(t, memDao.getTweetById("40325"));
        assertTrue(memDao.RemoveTweet("40325"));
        assertFalse(memDao.RemoveTweet("40325"));
    }

    @Test
    void getAllTweetsByUser(){

        Tweet t = new Tweet("test", "1337");
        t.setID("403294");
        assertEquals(0, memDao.getAllTweetsByUser("1337").size());
        memDao.CreateTweet(t);
        assertEquals(1, memDao.getAllTweetsByUser("1337").size());
    }

    @Test
    void getAllTweetsByUsers(){

        Tweet t = new Tweet("test", "1337");
        Tweet t2 = new Tweet("test", "1338");

        List<User> users = new ArrayList<User>();
        User u = new User("Henk");
        u.setId("1337");
        User u2 = new User("Vai");
        u2.setId("1338");

        users.add(u);
        users.add(u2);

        assertEquals(0, memDao.getAllTweetsByUsers(users).size());
        memDao.CreateTweet(t);
        assertEquals(1, memDao.getAllTweetsByUsers(users).size());
        memDao.CreateTweet(t2);
        assertEquals(2, memDao.getAllTweetsByUsers(users).size());

    }
}
