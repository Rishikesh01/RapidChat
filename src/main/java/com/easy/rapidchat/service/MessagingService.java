package com.easy.rapidchat.service;

import com.easy.rapidchat.dtos.MessageDTO;
import com.easy.rapidchat.mapper.MessageDTOMapper;
import com.easy.rapidchat.model.Message;
import com.easy.rapidchat.respository.GroupRespository;
import com.easy.rapidchat.respository.MessageRespository;
import com.easy.rapidchat.respository.UserDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

/**
 * @author Rishikesh
 * @project RapidChat
 */
@RequiredArgsConstructor
@Service
public class MessagingService {
    private static final String GROUP_MESSAGE_URL_PREFIX = "/topic/group.";
    private static final String USER_MESSAGE_URL_PREFIX = "/topic/user.";

    private final SimpMessagingTemplate messagingTemplate;
    private final MessageDTOMapper messageDTOMapper;
    private final MessageRespository messageRespository;
    private final GroupRespository groupRespository;
    private final UserDetailsRepository userDetailsRepository;

    public void sendMessageToUser(MessageDTO messageDTO) {
        messagingTemplate.convertAndSend(
                USER_MESSAGE_URL_PREFIX + messageDTO.getGroupName(),
                messageDTO
        );
        persistMessage(messageDTO);
    }

    public void sendMessageToGroup(MessageDTO messageDTO) {
        messagingTemplate.convertAndSend(
                GROUP_MESSAGE_URL_PREFIX + messageDTO.getGroupName(),
                messageDTO
        );
        persistMessage(messageDTO);
    }

    @RabbitListener

    private void persistMessage(MessageDTO messageDTO) {
        Message message = messageDTOMapper.toMessageModel(messageDTO);
        message.setGroup(groupRespository.findByName(messageDTO.getGroupName()));
        message.setUserDetail(userDetailsRepository.findByUsername(messageDTO.getSenderUserName()));
        message.setTimeOfMsg(LocalTime.now());
        messageRespository.save(message);
    }
}
