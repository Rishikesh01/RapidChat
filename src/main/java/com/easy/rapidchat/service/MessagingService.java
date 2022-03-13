package com.easy.rapidchat.service;

import com.easy.rapidchat.dtos.MessageDTO;
import com.easy.rapidchat.model.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Rishikesh
 * @project RapidChat
 */
@RequiredArgsConstructor
@Service
public class MessagingService {
    private static final String messageURLPrefix = "/topic";
    private final RabbitMessagingTemplate messagingTemplate;

    public void sendMessageToUser(MessageDTO messageDTO) {


    }

    public void sendMessageToGroup(MessageDTO messageDTO) {

    }

    public void persistMessage(Message message) {

    }
}
