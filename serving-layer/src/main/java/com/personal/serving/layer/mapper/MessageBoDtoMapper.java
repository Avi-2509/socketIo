package com.personal.serving.layer.mapper;

import com.personal.common.core.bo.MessageBo;
import com.personal.serving.layer.dto.MessageDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MessageBoDtoMapper {
    MessageBoDtoMapper INSTANCE = Mappers.getMapper(MessageBoDtoMapper.class);

    MessageDto boTodo(final MessageBo messageBo);
    MessageBo dtoToBo(final MessageDto messageDto);

    List<MessageDto> boTodoList(final List<MessageBo> messageBoList);
    List<MessageBo> dtoToBoList(final List<MessageDto> messageDtoList);

}
