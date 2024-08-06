package com.personal.component;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.personal.model.Message;
import com.personal.model.MessageType;
import com.personal.service.SocketService;
import com.personal.util.AppUtil;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SocketModule {

    @Autowired
    private SocketIOServer server;

    @Autowired
    SocketService socketService;

    @PostConstruct
    public void initializeSocket() {
        log.info("initializeSocket");
        server.addConnectListener(onConnected());
        server.addDisconnectListener(onDisconnected());
        server.addEventListener("sendMessage", Message.class, onChatReceived());
        server.start();
    }

    private DataListener<Message> onChatReceived() {
        return (senderClient, data, ackSender) -> {
            log.info("sending data {}", AppUtil.toJson(data));
            socketService.sendMessage(data.getRoom(),"getMessage", senderClient, data.getMessage());
        };
    }


    private ConnectListener onConnected() {
        return (client) -> {
            String room = client.getHandshakeData().getSingleUrlParam("room");
            client.joinRoom(room);
            log.info("Socket ID[{}]  Connected to socket", client.getSessionId().toString());
        };

    }

    private DisconnectListener onDisconnected() {
        return client -> {
            log.info("Client[{}] - Disconnected from socket", client.getSessionId().toString());
        };
    }

}