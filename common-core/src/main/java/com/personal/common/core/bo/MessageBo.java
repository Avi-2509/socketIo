package com.personal.common.core.bo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.personal.common.core.model.Message;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@MappedSuperclass
public class MessageBo extends Message {
}
