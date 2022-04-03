package com.easy.rapidchat.controller;

import com.easy.rapidchat.dtos.MessageDTO;
import com.easy.rapidchat.service.MessagingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Rishikesh
 * @project RapidChat
 */
@RequiredArgsConstructor
@Controller
public class MessageController {
    private final MessagingService messagingService;

    //URL /app/chat
    @MessageMapping("/groups")
    public ResponseEntity<MessageDTO> sendMsgInGroup(@Payload MessageDTO messageDTO) {
        messagingService.sendMessageToGroup(messageDTO);
        return ResponseEntity.accepted().body(messageDTO);
    }

    @MessageMapping("/users")
    public ResponseEntity<MessageDTO> sendMsgToUser(@Payload MessageDTO messageDTO) {
        messagingService.sendMessageToUser(messageDTO);
        return ResponseEntity.accepted().body(messageDTO);
    }


}
