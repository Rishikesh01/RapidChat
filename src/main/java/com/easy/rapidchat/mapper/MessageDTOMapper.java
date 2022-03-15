package com.easy.rapidchat.mapper;

import com.easy.rapidchat.dtos.MessageDTO;
import com.easy.rapidchat.model.Message;
import org.mapstruct.Mapper;

/**
 * @author Rishikesh
 * @project RapidChat
 */
@Mapper(componentModel = "spring")
public interface MessageDTOMapper {
    Message toMessageModel(MessageDTO messageDTO);
}
