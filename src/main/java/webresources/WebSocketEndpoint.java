package webresources;

import javax.faces.event.WebsocketEvent;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint("/socket")
public class WebSocketEndpoint {
    Set<Session> sessions = new HashSet<>();

    @OnOpen
    public void onOpened(Session session){
        sessions.add(session);
        System.out.println(session);
    }

    @OnClose
    public void onClosed(Session session){
        sessions.remove(session);
    }

    @OnMessage
    public String handleTextMessage(String message){
        System.out.println("New Text Message Received");
        return message;
    }

    @OnMessage(maxMessageSize = 1024000)
    public byte[] handleBinaryMessage(byte[] buffer){
        System.out.println("New binary message received");
        return buffer;
    }
}
