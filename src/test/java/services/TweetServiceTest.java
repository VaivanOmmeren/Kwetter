package services;

import dal.contexts.JPA.JPATweetDao;
import models.Tweet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TweetServiceTest {

    @Mock
    private static JPATweetDao mockDao;
    @InjectMocks
    private static TweetService tService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createTweet() {
        Tweet t = new Tweet();

        when(tService.createTweet(t)).thenReturn(t);

        assertEquals(t, tService.createTweet(t));
    }

    @Test
    void removeTweet() {

        Tweet t = new Tweet();
        t.setID("1");

        when(tService.RemoveTweet(t.getID())).thenReturn(true);

        assertTrue(tService.RemoveTweet(t.getID()));
    }

    @Test
    void getTweetById() {

        Tweet t = new Tweet();
        t.setID("2");

        when(tService.getTweetById(t.getID())).thenReturn(t);

        assertEquals(t, tService.getTweetById(t.getID()));

    }

    @Test
    void getAllTweetsByUser() {

        List<Tweet> tweets = new ArrayList<Tweet>();

        when(tService.getAllTweetsByUser("1")).thenReturn(tweets);

        assertEquals(tweets, tService.getTweetById("1"));
    }

    @Test
    void getAllTweetsByUsers() {
    }
}