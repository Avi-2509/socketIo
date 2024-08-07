package com.personal.common.service.impl;

import com.corundumstudio.socketio.SocketIOClient;
import com.personal.common.core.bo.MessageBo;
import com.personal.common.service.SocketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SocketServiceImpl implements SocketService {

    @Override
    public void send(final SocketIOClient senderClient, final String room, final String eventName, final MessageBo messageBo) {
        for (SocketIOClient client : senderClient.getNamespace().getRoomOperations(room).getClients()) {
            if (!client.getSessionId().equals(senderClient.getSessionId())) {
                client.sendEvent(eventName, messageBo);
            }
        }
    }

}