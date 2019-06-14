package webresources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dal.Dao.UserDao;
import models.Tweet;
import models.User;
import services.SessionService;

import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/socket/{username}")
public class WebSocketEndpoint {
    
    private static final ObjectMapper mapper = new ObjectMapper();

    @Inject
    private SessionService sService;

    @Inject
    private UserDao dao;

    @OnOpen
    public void onOpened(Session session, @PathParam("username") String username) {
        sService.addSession(username, session);
    }

    @OnClose
    public void onClosed(Session session, @PathParam("username") String username) {
        sService.getSessionHashMap().remove(username);
    }

    @OnMessage
    public String handleTextMessage(String message) {
        sendToAllFollowing(message);
        return message;
    }

    public void sendToAllFollowing(String message) {
        try {
            Tweet t = mapper.readValue(message, Tweet.class);
            String placedBy = t.getAuthorID();

            List<User> followers = dao.getFollowers(placedBy);

            followers.forEach( k -> {
                if(sService.getSessionHashMap().containsKey(k.getId())){
                    try {
                        sService.getSessionHashMap().get(k.getId()).getAsyncRemote().sendText(mapper.writeValueAsString(t));
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                }
            });

        } catch (IOException e) {

        }
    }
}
