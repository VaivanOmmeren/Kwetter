package dal.contexts.Memory;

import dal.Dao.TweetDao;
import models.Tweet;
import models.User;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Stateful;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Default;
import java.util.ArrayList;
import java.util.List;

@Stateful
@Alternative
public class MemoryTweetDao implements TweetDao {

    public MemoryTweetDao(){
    }

    private List<Tweet> tweets = new ArrayList<Tweet>();

    @PostConstruct
    public void createTweets(){
        for(int x = 0; x < 10; x++){
            Tweet tweet = new Tweet("Dit is een test", ""+(x*x), "henk");
            tweet.setId(""+x);
            tweets.add(tweet);
        }
    }

    public Tweet getTweetById(String id) {
        for(Tweet t : tweets){
            if(t.getId().equals(id)){
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
            if(t.getId().equals(id)){
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

    public List<Tweet> getAllTweetsByUsers(List<String> Users) {
        List<Tweet> userTweets = new ArrayList<Tweet>();

        for(String u : Users){
            userTweets.addAll(getAllTweetsByUser(u));
        }

        return userTweets;
    }

    @Override
    public List<Tweet> getAllTweetWithTag(String tag) {

        List<Tweet> tagTweets = new ArrayList<>();

        for(Tweet t: tweets){
            if(t.getText().contains(tag)){
                tagTweets.add(t);
            }
        }

        return tagTweets;
    }
}
