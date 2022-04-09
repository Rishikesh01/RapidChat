package com.easy.rapidchat.mapper;

import com.easy.rapidchat.dtos.MessageDTO;
import com.easy.rapidchat.model.Message;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author Rishikesh
 * @project RapidChat
 */
@Mapper(componentModel = "spring")
public interface MessageDTOMapper {
    Message toMessageModel(MessageDTO messageDTO);
    MessageDTO toMessageDTO(Message message);
    List<MessageDTO> toListMessageDTO(List<Message> message);
}
