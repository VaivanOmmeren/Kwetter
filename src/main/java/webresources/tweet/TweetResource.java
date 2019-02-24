package webresources.tweet;

import dal.Dao.TweetDao;
import models.Tweet;
import services.TweetService;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Path("/tweets")
public class TweetResource {

    @Inject
    TweetService tweetService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createTweet(Tweet tweet) {
        try {
            return Response.ok(tweetService.createTweet(tweet)).header("Location", new URI("/api/tweets?id=" + tweet.getID())).build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return Response.status(500).build();
    }

    @DELETE
    public Response removeTweet(@QueryParam("id") String id) {
        if (tweetService.RemoveTweet(id)) {
            return Response.status(200).build();
        } else {
            return Response.status(400).build();
        }
    }

    @GET
    public Response getTweet(@QueryParam("id") String id){
        return Response.ok(tweetService.getTweetById(id)).build();
    }

    @GET
    @Path("/{username}")
    public Response getTweetByUser(@PathParam("username") String userId){
        return Response.ok(tweetService.getAllTweetsByUser(userId)).build();
    }

    @GET
    @Path("users")
    public Response getTweetByUsers(@QueryParam("users") List<String> userIDs){
        return Response.ok(tweetService.getAllTweetsByUsers(userIDs)).build();
    }
}
