package com.easy.rapidchat.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


/**
 * @author Rishikesh
 * @project RapidChat
 */
@Data
@NoArgsConstructor
public class MessageDTO {
    private String senderID;
    private UUID roomUID;
    private UUID channelID;
    private String content;
}
