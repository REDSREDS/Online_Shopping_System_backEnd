package com.maxChen.onlineStore.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
@ServerEndpoint("/websocket")
@Slf4j
public class WebSocket {
    private Session session;
    private static CopyOnWriteArrayList<WebSocket> webSocketSet = new CopyOnWriteArrayList<>();

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);
        log.info("new connection, amount:{}", webSocketSet.size());
    }

    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        log.info("connect finish, amount:{}", webSocketSet.size());
    }

    @OnMessage
    public void onMessage(String message) {
        log.info("new message:{}", message);
    }
    public void sendMessage(String message) {
        for(WebSocket webSocket : webSocketSet) {
            log.info("dispatch message, message={}", message);
            try {
                webSocket.session.getBasicRemote().sendText(message);
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}
