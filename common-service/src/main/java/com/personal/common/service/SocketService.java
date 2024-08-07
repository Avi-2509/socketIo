package com.personal.common.service;

import com.corundumstudio.socketio.SocketIOClient;
import com.personal.common.core.bo.MessageBo;

public interface SocketService {
    void send(final SocketIOClient senderClient, final String room, final String eventName, final MessageBo messageBo);
}
