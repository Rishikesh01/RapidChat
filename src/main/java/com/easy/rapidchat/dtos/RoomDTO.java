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
public class RoomDTO {
    private UUID id;
    private String roomType;
    private String chatName;
    private String roomID;
}
