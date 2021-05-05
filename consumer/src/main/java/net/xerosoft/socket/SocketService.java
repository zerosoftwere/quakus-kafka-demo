package net.xerosoft.socket;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.xerosoft.emails.Email;

@ApplicationScoped
@ServerEndpoint("/emails")
public class SocketService {
    Map<String, Session> sessions = new HashMap<>();
    ObjectMapper mapper = new ObjectMapper();

    @OnOpen
    public void onOpen(Session session) {
        sessions.put(session.getId(), session);
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session.getId());
    }

    public void broadcast(Email email) {
        try {
            String message = mapper.writeValueAsString(email);
            sessions.values().forEach(s -> s.getAsyncRemote().sendObject(message));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}