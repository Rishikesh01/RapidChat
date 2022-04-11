package com.easy.rapidchat.mapper;

import com.easy.rapidchat.dtos.RoomDTO;
import com.easy.rapidchat.model.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Rishikesh
 * @project RapidChat
 */
@Mapper(componentModel = "spring")
public interface RoomDTOMapper {
    @Mapping(target = "roomID", source = "topicID")
    RoomDTO toRoomDTO(Room room);
}
