package dal.Dao;

import models.Tweet;
import models.User;

import java.util.List;

public interface TweetDao {

    Tweet getTweetById(String id);
    Tweet CreateTweet(Tweet tweet);
    boolean RemoveTweet(String id);
    List<Tweet> getAllTweetsByUser(String id);
    List<Tweet> getAllTweetsByUsers(List<String> userIDs);
    List<Tweet> getAllTweetWithTag(String tag);
}
