package webresources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dal.Dao.UserDao;
import models.Tweet;
import models.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Singleton;
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

@ApplicationScoped
@ServerEndpoint("/socket/{username}")
public class WebSocketEndpoint {

    private final Map<String, Session> sessionHashMap = new ConcurrentHashMap<>();
    private final ObjectMapper mapper = new ObjectMapper();

    int count = 0;

    @Inject
    private UserDao dao;

    @OnOpen
    public void onOpened(Session session, @PathParam("username") String username) {
        System.out.println(this.getClass().hashCode());
        System.out.println("New connection inbound, previously had " + sessionHashMap.size());
        System.out.println(username + " now joined");
        sessionHashMap.put(username, session);
        System.out.println("Count is now " + sessionHashMap.size());
        count++;
        System.out.println("Session count is now " + count);
    }

    @OnClose
    public void onClosed(Session session, @PathParam("username") String username) {
        sessionHashMap.remove(username);
        System.out.println("Somebody left, count is now " + sessionHashMap.size());
    }

    @OnMessage
    public String handleTextMessage(String message) {
        System.out.println("New Text Message Received");
        sendToAllFollowing(message);
        return message;
    }

    public void sendToAllFollowing(String message) {
        try {
            Tweet t = mapper.readValue(message, Tweet.class);
            String placedBy = t.getAuthorID();

            List<User> followers = dao.getFollowers(placedBy);

            followers.forEach( k -> {
                System.out.println("I GET INTO THE FOR LOOP");
                System.out.println(k.getId());
                System.out.println(sessionHashMap.size());
                if(sessionHashMap.containsKey(k.getId())){
                    try {
                        System.out.println("I ALSO GET INTO THE IF STATEMENT BUT THE MESSAGE JUST DOESNT SEND LOL");
                        sessionHashMap.get(k.getId()).getAsyncRemote().sendText(mapper.writeValueAsString(t));
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                }
            });

        } catch (IOException e) {

        }
    }
}
