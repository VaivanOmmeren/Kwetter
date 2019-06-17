package dal.contexts.JPA;

import dal.Dao.TweetDao;
import models.Tweet;
import models.User;

import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Default
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

    public List<Tweet> getAllTweetsByUsers(List<String> userIDs) {
        TypedQuery<Tweet> query = em.createQuery("SELECT t FROM Tweet t WHERE t.authorID IN :id", Tweet.class);
        query.setParameter("id", userIDs);
        return query.getResultList();

    }

    @Override
    public List<Tweet> getAllTweetWithTag(String tag) {
        tag = "%#" + tag + "%";
        Query query = em.createNativeQuery("SELECT t.* FROM Tweet t WHERE t.text LIKE :tag", Tweet.class);
        query.setParameter("tag", tag);
        return query.getResultList();
    }
}
