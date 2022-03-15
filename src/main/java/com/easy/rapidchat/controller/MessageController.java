package com.easy.rapidchat.controller;

import com.easy.rapidchat.dtos.MessageDTO;
import com.easy.rapidchat.service.MessagingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Rishikesh
 * @project RapidChat
 */
@RequiredArgsConstructor
@RestController
public class MessageController {
    private final MessagingService messagingService;

    //URL /app/chat
    @MessageMapping("/group")
    public ResponseEntity<HttpStatus> sendMsgInGroup(@Payload MessageDTO messageDTO) {
        messagingService.sendMessageToGroup(messageDTO);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @MessageMapping("/user")
    public ResponseEntity<HttpStatus> sendMsgToUser(@Payload MessageDTO messageDTO) {
        messagingService.sendMessageToUser(messageDTO);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }
}
