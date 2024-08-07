package com.personal.common.core.model;

import com.personal.common.core.enums.MessageTypeEnum;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Message extends SqlBase {

    @Column(name = "messageType")
    @Enumerated(EnumType.STRING)
    private MessageTypeEnum messageType;

    @Column(name = "message")
    private String message;

    @Column(name = "room")
    private String room;
}
