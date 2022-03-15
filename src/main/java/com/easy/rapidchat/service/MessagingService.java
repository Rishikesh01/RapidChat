package com.easy.rapidchat.service;

import com.easy.rapidchat.dtos.MessageDTO;
import com.easy.rapidchat.mapper.MessageDTOMapper;
import com.easy.rapidchat.model.Message;
import com.easy.rapidchat.respository.GroupRespository;
import com.easy.rapidchat.respository.MessageRespository;
import com.easy.rapidchat.respository.UserDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

/**
 * @author Rishikesh
 * @project RapidChat
 */
@RequiredArgsConstructor
@Service
public class MessagingService {
    private static final String messageURLPrefix = "/topic";
    private final RabbitMessagingTemplate messagingTemplate;
    private final MessageDTOMapper messageDTOMapper;
    private final MessageRespository messageRespository;
    private final GroupRespository groupRespository;
    private final UserDetailsRepository userDetailsRepository;

    public void sendMessageToUser(MessageDTO messageDTO) {
        messagingTemplate.convertAndSend(
                messageURLPrefix + messageDTO.getGroupName(),
                messageDTO);
        persistMessage(messageDTO);
    }

    public void sendMessageToGroup(MessageDTO messageDTO) {
        messagingTemplate.convertAndSend(
                "/user" + messageDTO.getGroupName(),
                messageDTO);
        persistMessage(messageDTO);
    }

    private void persistMessage(MessageDTO messageDTO) {
        Message message = messageDTOMapper.toMessageModel(messageDTO);
        message.setGroup(groupRespository.findByName(messageDTO.getGroupName()));
        message.setUserDetail(userDetailsRepository.findByUsername(messageDTO.getSenderUserName()));
        message.setTimeOfMsg(LocalTime.now());

        messageRespository.save(message);
    }
}
