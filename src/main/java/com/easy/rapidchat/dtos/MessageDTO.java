package com.easy.rapidchat.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author Rishikesh
 * @project RapidChat
 */
@Data
@NoArgsConstructor
public class MessageDTO {
    private String senderUserName;
    private String groupName;
    private String content;
}
