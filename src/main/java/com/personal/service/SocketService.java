package com.personal.service;

import com.corundumstudio.socketio.SocketIOClient;
import com.personal.model.Message;
import com.personal.model.MessageType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SocketService {

    public void sendMessage(String room, String eventName, SocketIOClient senderClient, String msg) {
        for (SocketIOClient client : senderClient.getNamespace().getRoomOperations(room).getClients()) {
            if (!client.getSessionId().equals(senderClient.getSessionId())) {
                Message message = new Message();
                message.setMessage(msg);
                message.setType(MessageType.SERVER);
                client.sendEvent(eventName, message);
            }
        }
    }

}