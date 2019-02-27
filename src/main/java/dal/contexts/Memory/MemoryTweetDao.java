package dal.contexts.Memory;

import dal.Dao.TweetDao;
import models.Tweet;
import models.User;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Stateful;
import javax.enterprise.inject.Default;
import java.util.ArrayList;
import java.util.List;

@Stateful
@Default
public class MemoryTweetDao implements TweetDao {

    public MemoryTweetDao(){
        System.out.println(" constructed");
    }

    private List<Tweet> tweets = new ArrayList<Tweet>();

    @PostConstruct
    public void createTweets(){
        for(int x = 0; x < 10; x++){
            Tweet tweet = new Tweet("Dit is een test", ""+(x*x));
            tweet.setID(""+x);
            tweets.add(tweet);
        }
    }

    public Tweet getTweetById(String id) {
        System.out.println(tweets.size());
        for(Tweet t : tweets){
            if(t.getID().equals(id)){
                return t;
            }
        }
        return null;
    }

    public Tweet CreateTweet(Tweet tweet) {
        tweets.add(tweet);
        return tweet;
    }

    public boolean RemoveTweet(String id) {

        int index = -1;

        for(Tweet t : tweets){
            if(t.getID().equals(id)){
                index = tweets.indexOf(t);
                break;
            }
        }

        if(index != -1){
            tweets.remove(index);
            return true;
        }else{
            return false;
        }
    }

    public List<Tweet> getAllTweetsByUser(String id) {

        List<Tweet> userTweets = new ArrayList<Tweet>();

        for (Tweet t : tweets) {
            if (t.getAuthorID().equals(id)) {
                userTweets.add(t);
            }
        }

        return userTweets;
    }

    public List<Tweet> getAllTweetsByUsers(List<User> Users) {
        List<Tweet> userTweets = new ArrayList<Tweet>();

        for(User u : Users){
            userTweets.addAll(getAllTweetsByUser(u.getName()));
        }

        return userTweets;
    }
}
