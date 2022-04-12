package com.easy.rapidchat.mapper;

import com.easy.rapidchat.dtos.MessageDTO;
import com.easy.rapidchat.model.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @author Rishikesh
 * @project RapidChat
 */
@Mapper(componentModel = "spring")
public interface MessageDTOMapper {

    Message toMessageModel(MessageDTO messageDTO);

    @Mappings({
            @Mapping(target = "senderID", expression = "java(message.getUserDetail().getId())"),
            @Mapping(target = "channelID", expression = "java(message.getRoom().getTopicID())"),
            @Mapping(target = "roomUID", expression = "java(message.getRoom().getId())")

    })
    MessageDTO toMessageDTO(Message message);

    @Mappings({
            @Mapping(target = "senderID", expression = "java(message.getUserDetail().getId())"),
            @Mapping(target = "channelID", expression = "java(message.getRoom().getTopicID())"),
            @Mapping(target = "roomUID", expression = "java(message.getRoom().getId())")

    })
    List<MessageDTO> toListMessageDTO(List<Message> message);
}
