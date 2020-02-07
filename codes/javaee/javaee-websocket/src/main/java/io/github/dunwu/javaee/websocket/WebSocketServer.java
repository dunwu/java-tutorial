package io.github.dunwu.javaee.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

/**
 * Websocket 消息处理中心
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @see https://github.com/jetty-project/embedded-jetty-websocket-examples
 */
@ServerEndpoint(value = "/auth/user/{id}", configurator = WebSocketServerConfigurator.class)
public class WebSocketServer {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

    static Map<String, Set<Session>> userSessionMap = new ConcurrentHashMap<>();

    // private static Map userSessionMap = new ConcurrentHashMap<Long, Set<Session>>();

    /**
     * 发送广播消息
     *
     * @param message
     */
    public static void sendAll(String message) {
        logger.info("SendAll: {}", message);

        for (Set<Session> groups : userSessionMap.values()) {
            for (Session session : groups) {
                try {
                    session.getBasicRemote().sendObject(message);
                } catch (IOException e) {
                    logger.error(e.toString());
                } catch (EncodeException e) {
                    logger.error(e.toString());
                }
            }
        }
    }

    public static void send(String userId, String message) {
        for (String id : userSessionMap.keySet()) {
            if (id.equals(userId)) {
                for (Session session : userSessionMap.get(userId)) {
                    try {
                        session.getBasicRemote().sendObject(message);
                        logger.info("SendAll: {}", message);
                    } catch (Exception e) {
                        logger.error(e.toString());
                    }
                }
            }
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException, InterruptedException {
        logger.debug("收到一条客户端消息。session:{}, msg:{}", session.getId(), message);
    }

    @OnOpen
    public void onOpen(Session session, EndpointConfig config, @PathParam("id") String id) {
        logger.info("Session {} connected.", session.getId());

        // 如果是新 Session，记录进 Map
        boolean isNewUser = true;
        Iterator i = userSessionMap.entrySet().iterator();
        while (i.hasNext()) {
            Map.Entry entry = (Map.Entry) i.next();
            String key = (String) entry.getKey();
            if (key.equals(id)) {
                userSessionMap.get(key).add(session);
                isNewUser = false;
                break;
            }
        }
        if (isNewUser) {
            Set<Session> sessions = new HashSet<>();
            sessions.add(session);
            userSessionMap.put(id, sessions);
        }
        logger.info("当前在线用户数: {}", userSessionMap.values().size());
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        logger.info("Session {} disconnected. Because of {}", session.getId(), closeReason);
        for (Set<Session> item : userSessionMap.values()) {
            if (item.contains(session)) {
                // 删除连接 session
                item.remove(session);
                // 如果 userId 对应的 session 数为 0 ，删除该 userId 对应的记录
                if (0 == item.size()) {
                    userSessionMap.values().remove(item);
                }
                break;
            }
        }
        logger.info("当前在线用户数: {}", userSessionMap.values().size());
    }

    @OnError
    public void onError(Throwable error) {
        logger.error("Websocket error: {}", error.getMessage());
    }

}
