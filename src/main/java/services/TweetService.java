package services;

import dal.Dao.TweetDao;
import dal.Dao.UserDao;
import models.Tweet;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class TweetService {

    @Inject
    TweetDao tweetDao;

    @Inject
    UserDao userDao;

    public TweetService(){

    }

    public Tweet createTweet(Tweet t){
        return tweetDao.CreateTweet(t);
    }

    public boolean RemoveTweet(String id) {
        return tweetDao.RemoveTweet(id);
    }

    public Tweet getTweetById(String id) {
        return tweetDao.getTweetById(id);
    }

    public List<Tweet> getAllTweetsByUser(String userId) {
        return tweetDao.getAllTweetsByUser(userId);
    }

    public List<Tweet> getAllTweetsByUsers(List<String> userIDs) {
        return tweetDao.getAllTweetsByUsers(userIDs);
    }
}
