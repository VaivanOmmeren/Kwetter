package webresources.user;

import models.User;
import services.UserService;

import javax.inject.Inject;
import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

@Path("/users")
public class UserResource {


    @Inject
    private UserService userServiceImpl;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@QueryParam("username") String userId) {
        if(userId != null){
            return Response.status(200).entity(userServiceImpl.getUserByID(userId)).build();
        }else{
            return Response.status(200).entity(userServiceImpl.getAllUsers()).build();
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
    public Response followUser(@PathParam("username") String userId, @QueryParam("followID") String userToFollow){
        try {
            if(userServiceImpl.followUser(userId, userToFollow)){
                return Response.status(204).header("Location", new URI("/api/users?username=" + userId)).build();
            }else{
                return Response.status(400).build();
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return Response.status(500).build();
    }

    @Path("{username}/followers")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllFollowers(@PathParam("username") String userId){
        return Response.ok(userServiceImpl.getFollowers(userServiceImpl.getUserByID(userId))).build();
    }

    @Path("{username}/following")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllFollowing(@PathParam("username") String userId){
        return Response.ok(userServiceImpl.getFollowers(userServiceImpl.getUserByID(userId))).build();
    }
}
