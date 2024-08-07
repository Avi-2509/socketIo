package com.personal.common.core.entity;

import com.personal.common.core.model.UserProfileChat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "userProfileChat")
public class UserProfileChatEntity extends UserProfileChat {
}
