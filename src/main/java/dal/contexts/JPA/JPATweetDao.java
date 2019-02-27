package dal.contexts.JPA;

import dal.Dao.TweetDao;
import models.Tweet;
import models.User;

import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
@Alternative
public class JPATweetDao implements TweetDao {

    public JPATweetDao(){

    }

    @PersistenceContext
    EntityManager em;

    public Tweet getTweetById(String id) {
        TypedQuery query = em.createNamedQuery("tweet.getTweetById", Tweet.class);
        query.setParameter("id", id);
        List<Tweet> tweets = query.getResultList();
        return tweets.get(0);
    }

    public Tweet CreateTweet(Tweet tweet) {
        em.persist(tweet);
        return tweet;
    }

    public boolean RemoveTweet(String id) {
        em.remove(getTweetById(id));
        return true;
    }

    public List<Tweet> getAllTweetsByUser(String id) {
        TypedQuery query = em.createNamedQuery("tweet.getTweetByUser", Tweet.class);
        query.setParameter("id", id);
        List<Tweet> tweets = query.getResultList();
        return tweets;
    }

    public List<Tweet> getAllTweetsByUsers(List<User> userIDs) {
        return null;
    }
}
