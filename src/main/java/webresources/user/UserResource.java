package webresources.user;

import jwt.TokenProvider;
import models.User;
import services.UserService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Path("/users")
public class UserResource {


    @Inject
    private UserService userServiceImpl;
    @Inject
    private TokenProvider tokenProvider;
    @Context
    SecurityContext context;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@Context HttpHeaders headers, @QueryParam("username") String username, @Context UriInfo uriInfo) {
        if(username != null){
            User u = userServiceImpl.getUserByName(username);
//            initLinks(u, uriInfo);

            Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder().queryParam("username", u.getName()))
                                            .rel("self").build();

            Link following = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder().path("/" + u.getName() + "/following"))
                                            .rel("following").build();

            Link followers = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder().path("/" + u.getName() + "/followers"))
                                            .rel("followers").build();

            Link kweets = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("/tweets/" + u.getId()))
                                            .rel("tweets").build();

            return Response.ok(u).links(self, following, followers, kweets).build();

        }else{
            List<User> users = userServiceImpl.getAllUsers();
            users.forEach( u -> initLinks(u, uriInfo));

            Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder())
                                            .rel("self").build();
            return Response.ok(users).links(self).build();

        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/login")
    public Response loginUser(User u){
        try{
            User user = userServiceImpl.loginUser(u.getName(), u.getPassword());
            Set<String> roles = new HashSet<>();
            roles.add(user.getUserRole().getName());
            String token = tokenProvider.createToken(u.getName(), roles, true);
            user.setToken(token);

            return Response.ok().entity(user).build();

        } catch (NoResultException e){
            return Response.serverError().build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(User user) {
        try {
            return Response.ok(userServiceImpl.CreateUser(user)).header("Location", new URI("/api/users?username="+user.getName())).build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return Response.status(500).build();
    }


    @DELETE
    @RolesAllowed("Administrator")
    public Response deleteUser(@QueryParam("username") String userId) {
        if (userServiceImpl.RemoveUser(userId)) {
            return Response.status(200).build();
        } else {
            return Response.status(400).build();
        }

    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{username}")
    @RolesAllowed({"user", "Administrator"})
    public Response editUser(User u){
        try{
            return Response.ok(userServiceImpl.editUser(u)).header("Location", new URI("/api/users?username="+u.getName())).build();
        }catch(URISyntaxException e){
            e.printStackTrace();
        }
        return Response.status(500).build();
    }

    @PUT
    @Path("{username}/follow")
    @RolesAllowed({"user", "Administrator"})
    public Response followUser(@PathParam("username") String userName, @QueryParam("followID") String userToFollow){
        try {
            if(userServiceImpl.followUser(userName, userToFollow)){
                return Response.status(204).header("Location", new URI("/api/users?username=" + userName)).build();
            }else{
                return Response.status(400).build();
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return Response.status(500).build();
    }

    @PUT
    @Path("{username}/unfollow")
    @RolesAllowed({"user", "Administrator"})
    public Response unfollowUser(@PathParam("username") String username, @QueryParam("unfollowId") String userToUnfollow){
        try{
            if(userServiceImpl.unfollowUser(username, userToUnfollow)){
                return Response.status(204).header("Location", new URI("/api/users?username="+username)).build();
            }else {
                return Response.status(400).build();
            }
        } catch (URISyntaxException e){
            e.printStackTrace();
        }
        return Response.status(500).build();
    }

    @Path("{username}/followers")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllFollowers(@PathParam("username") String userId){
        return Response.ok(userServiceImpl.getFollowers(userServiceImpl.getUserByName(userId))).build();
    }

    @Path("{username}/following")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllFollowing(@PathParam("username") String userId){
        return Response.ok(userServiceImpl.getFollowing(userServiceImpl.getUserByName(userId))).build();
    }

    private void initLinks(User u, UriInfo uriInfo){
        //create self link
        UriBuilder uriBuilder = uriInfo.getRequestUriBuilder();
        uriBuilder = uriBuilder.path(u.getName());
        Link.Builder linkBuilder = Link.fromUriBuilder(uriBuilder);
        Link selfLink = linkBuilder.rel("self").build();

        u.setLinks(Arrays.asList(selfLink));
    }
}
