package webresources.role;

import models.UserRole;
import services.RoleService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

@Path("/role")
public class RoleResource {

    @Inject
    RoleService roleService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createRole(UserRole r){
        try {
            return Response.ok(roleService.createRole(r)).header("Location", new URI("/api/role?id=" +r.getId())).build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return Response.status(500).build();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRole(@QueryParam("id") String id){
        if(id != null){
            return Response.ok().entity(roleService.getRole(id)).build();
        }
        else{
            return Response.ok().entity(roleService.getAll()).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateRole(UserRole r){
        try {
            return Response.ok(roleService.updateRole(r)).header("Location", new URI("/api/role?id="+ r.getId())).build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return Response.status(500).build();
    }

    @DELETE
    public Response deleteRole(@QueryParam("id") String id){
        if(roleService.removeRole(id)){
            return Response.ok().build();
        }else{
            return Response.status(400).build();
        }
    }


}
