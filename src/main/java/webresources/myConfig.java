package webresources;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import webresources.role.RoleResource;
import webresources.tweet.TweetResource;
import webresources.user.UserResource;

import javax.annotation.security.DeclareRoles;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
@DeclareRoles({"Administrator", "user"})
public class myConfig extends ResourceConfig {

    public myConfig(){
        register(UserResource.class);
        register(TweetResource.class);
        register(RoleResource.class);
        register(CORSResponseFilter.class);
        register(RolesAllowedDynamicFeature.class);
    }
}
