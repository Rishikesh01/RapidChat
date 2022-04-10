package com.easy.rapidchat.service;

import com.easy.rapidchat.dtos.RoomDTO;
import com.easy.rapidchat.mapper.RoomDTOMapper;
import com.easy.rapidchat.model.Room;
import com.easy.rapidchat.model.RoomUser;
import com.easy.rapidchat.model.UserDetail;
import com.easy.rapidchat.respository.RoomRepository;
import com.easy.rapidchat.respository.UserDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Rishikesh
 * @project RapidChat
 */
@Service
@RequiredArgsConstructor
public class RoomCreationService {
    private final RoomRepository roomRepository;
    private final UserDetailsRepository userDetailsRepository;
    private final RoomDTOMapper roomDTOMapper;

    private void addRoomUsers(List<UserDetail> userDetailList, Room room) {
        List<RoomUser> roomUsers = new ArrayList<>();
        for (UserDetail userDetail : userDetailList) {
            RoomUser roomUser = new RoomUser(null, room, userDetail);
            roomUsers.add(roomUser);
        }
        roomUsers.forEach(roomRepository::save);
    }

    public RoomDTO makeDirectMessageRoom(String username, String toUsername) {
        String topicID = UUID.randomUUID().toString();
        Room room = new Room(
                null,
                username + "_" + toUsername,
                topicID,
                null
        );
        List<UserDetail> userDetails = new ArrayList<>();
        userDetails.add(userDetailsRepository.findByUsername(username));
        userDetails.add(userDetailsRepository.findByUsername(toUsername));
        roomRepository.save(room);
        addRoomUsers(userDetails, room);
        return roomDTOMapper.toRoomDTO(room);
    }

    public RoomDTO makeGroupRoom(String groupName, List<String> usernames) {
        String topicID = UUID.randomUUID().toString();
        Room room = new Room(
                null,
                groupName,
                topicID,
                null
        );
        List<UserDetail> userDetails = new ArrayList<>();
        for (String user : usernames) {
            UserDetail userDetail = userDetailsRepository.findByUsername(user);
            userDetails.add(userDetail);
        }
        roomRepository.save(room);
        addRoomUsers(userDetails, room);
        return roomDTOMapper.toRoomDTO(room);
    }

}
