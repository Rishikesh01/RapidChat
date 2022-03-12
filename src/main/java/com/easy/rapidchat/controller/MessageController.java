package com.easy.rapidchat.controller;

import com.easy.rapidchat.model.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Rishikesh
 * @project RapidChat
 */
@RequiredArgsConstructor
@RestController
public class MessageController {
    private final SimpMessagingTemplate simpMessagingTemplate;

    private final static String messageUrlPrefix="/topic";
    //URL /app/chat
    @MessageMapping("/group")
    public ResponseEntity<HttpStatus> sendMsgInGroup(@Payload Message message){
        simpMessagingTemplate.convertAndSend(
                messageUrlPrefix+message.getReceiverId().toString(),
                message);
    return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @MessageMapping("/user")
    public ResponseEntity<HttpStatus> sendMsgToUser(@Payload Message message){
        simpMessagingTemplate.convertAndSendToUser(
                message.getReceiverId().toString(),
                messageUrlPrefix+message.getReceiverId().toString(),
                message);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }
}
