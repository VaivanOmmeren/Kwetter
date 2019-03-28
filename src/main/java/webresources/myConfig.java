package webresources;
import org.glassfish.jersey.server.ResourceConfig;
import webresources.role.RoleResource;
import webresources.tweet.TweetResource;
import webresources.user.UserResource;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class myConfig extends ResourceConfig {

    public myConfig(){
        register(UserResource.class);
        register(TweetResource.class);
        register(RoleResource.class);
        register(CORSResponseFilter.class);
    }
}
