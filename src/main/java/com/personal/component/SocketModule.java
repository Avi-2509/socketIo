package com.personal.component;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.personal.model.Message;
import com.personal.model.MessageType;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SocketModule {

    @Autowired
    private SocketIOServer server;

    @PostConstruct
    public void initializeSocket() {
        log.info("initializeSocket");
        server.addConnectListener(onConnected());
        server.addDisconnectListener(onDisconnected());
        server.addEventListener("sendMessage", String.class, onChatReceived());
        server.start();
    }

    private DataListener<String> onChatReceived() {
        return (senderClient, data, ackSender) -> {
            log.info("sending message {}", data);
            senderClient.getNamespace().getBroadcastOperations().sendEvent("getMessage", data);
        };
    }

    private ConnectListener onConnected() {
        return (client) -> {
            log.info("Socket ID[{}]  Connected to socket", client.getSessionId().toString());
        };
    }

    private DisconnectListener onDisconnected() {
        return client -> {
            log.info("Client[{}] - Disconnected from socket", client.getSessionId().toString());
        };
    }

}