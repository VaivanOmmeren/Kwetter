package dal.contexts.JPA;

import dal.Dao.TweetDao;
import models.Tweet;
import models.User;

import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Default;
import java.util.List;

@Stateless
@Alternative
public class JPATweetDao implements TweetDao {
    public Tweet getTweetById(String id) {
        return null;
    }

    public Tweet CreateTweet(Tweet tweet) {
        return null;
    }

    public boolean RemoveTweet(String id) {
        return false;
    }

    public List<Tweet> getAllTweetsByUser(String id) {
        return null;
    }

    public List<Tweet> getAllTweetsByUsers(List<User> userIDs) {
        return null;
    }
}
