package com.easy.rapidchat.mapper;

import com.easy.rapidchat.dtos.RoomDTO;
import com.easy.rapidchat.model.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author Rishikesh
 * @project RapidChat
 */
@Mapper(componentModel = "spring")
public interface RoomDTOMapper {
    @Mapping(target = "channelID", source = "topicID")
    RoomDTO toRoomDTO(Room room);

    @Mapping(target = "channelID", source = "topicID")
    List<RoomDTO> toRoomDTOList(List<Room> room);
}
