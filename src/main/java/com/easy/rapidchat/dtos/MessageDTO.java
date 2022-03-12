package com.easy.rapidchat.dtos;

import lombok.Data;


/**
 * @author Rishikesh
 * @project RapidChat
 */
@Data
public class MessageDTO {
    private String senderUserName;
    private String receiverUserName;
    private String content;
}