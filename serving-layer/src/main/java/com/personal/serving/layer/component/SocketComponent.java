package com.personal.serving.layer.component;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.personal.common.core.bo.MessageBo;
import com.personal.common.core.util.AppUtil;
import com.personal.common.service.SocketService;
import com.personal.serving.layer.dto.MessageDto;
import com.personal.serving.layer.mapper.MessageBoDtoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class SocketComponent {

    @Autowired
    private SocketIOServer server;

    @Autowired
    SocketService socketService;

    @PostConstruct
    public void initializeSocket() {
        log.info("initializeSocket");
        server.addConnectListener(onConnected());
        server.addDisconnectListener(onDisconnected());
        server.addEventListener("sendMessage", MessageDto.class, onMessageReceived());
        server.start();
    }

    private DataListener<MessageDto> onMessageReceived() {
        return (senderClient, data, ackSender) -> {
            MessageBo messageBo = MessageBoDtoMapper.INSTANCE.dtoToBo(data);
            log.info("headers {}", senderClient.getHandshakeData().getHttpHeaders());
            log.info("sending data {}", AppUtil.toJson(messageBo));
            socketService.send(senderClient, data.getRoom(), "getMessage", messageBo);
        };
    }


    private ConnectListener onConnected() {
        return (client) -> {
            String room = client.getHandshakeData().getSingleUrlParam("room");
            client.getHandshakeData().getHttpHeaders().set("room", "123, room, room1");
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