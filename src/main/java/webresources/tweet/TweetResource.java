package webresources.tweet;

import models.Tweet;
import services.TweetService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.management.Query;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Path("/tweets")
public class    TweetResource {

    @Inject
    TweetService tweetService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"user", "Administrator"})
    public Response createTweet(Tweet tweet) {
        try {
            return Response.ok(tweetService.createTweet(tweet)).header("Location", new URI("/api/tweets?id=" + tweet.getId())).build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return Response.status(500).build();
    }

    @DELETE
    @RolesAllowed("Administrator")
    public Response removeTweet(@QueryParam("id") String id) {
        if (tweetService.RemoveTweet(id)) {
            return Response.status(200).build();
        } else {
            return Response.status(400).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTweet(@QueryParam("id") String id, @Context UriInfo uriInfo){
        Tweet t = tweetService.getTweetById(id);

        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()
                                        .queryParam("id", t.getId()))
                                        .rel("self").build();

        Link author = Link.fromUriBuilder(uriInfo.getBaseUriBuilder()
                                        .path("/users")
                                        .queryParam("username", t.getAuthorname()))
                                        .rel("author").build();

        return Response.ok(t).links(self, author).build();
    }

    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"user", "Administrator"})
    public Response getTweetByUser(@PathParam("username") String userId){
        return Response.ok(tweetService.getAllTweetsByUser(userId)).build();
    }

    @GET
    @Path("users")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTweetByUsers(@QueryParam("users") List<String> userIDs){
        return Response.ok(tweetService.getAllTweetsByUsers(userIDs)).build();
    }

    @GET
    @Path("hashtag")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTweetByHashtag(@QueryParam("tag") String tag){
        System.out.println(tag);
        return Response.ok(tweetService.getAllTweetByTag(tag)).build();
    }
}
