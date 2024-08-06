package com.personal.model;

import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private MessageType type;
    private String message;
    private String room;
}
