package com.personal.common.core.mapper;

import com.personal.common.core.bo.MessageBo;
import com.personal.common.core.entity.MessageEntity;
import com.personal.common.core.model.Message;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MessageBoEntityMapper {
    MessageBoEntityMapper INSTANCE = Mappers.getMapper(MessageBoEntityMapper.class);

    MessageBo entityToBo(final MessageEntity messageEntity);
    List<MessageBo> entityToBoList(final List<MessageEntity> messageEntityList);

    MessageEntity boToEntity(final MessageBo messageBo);
    List<MessageEntity> boToEntityList(final List<MessageBo> messageBoList);
}
