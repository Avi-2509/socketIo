package com.personal.common.core.entity;

import com.personal.common.core.model.Message;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "message")
public class MessageEntity extends Message {
}
