package com.easy.rapidchat.controller;

import com.easy.rapidchat.dtos.RoomDTO;
import com.easy.rapidchat.model.UserDetail;
import com.easy.rapidchat.service.RoomCreationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Rishikesh
 * @project RapidChat
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/create/room/user")
public class RoomCreationController {
    private final RoomCreationService roomCreationService;

    @GetMapping("/dm/{user}")
    public ResponseEntity<RoomDTO> createDirectMessageRoom(
            @AuthenticationPrincipal UserDetail userDetail,
            @PathVariable("user") String toUser
    ) {
        RoomDTO room = roomCreationService.makeDirectMessageRoom(userDetail.getUsername(), toUser);
        room.setRoomType("inbox");
        return ResponseEntity.ok(room);
    }

    @GetMapping("/group/{name}")
    public ResponseEntity<RoomDTO> createGroupRoom(
            @PathVariable("name") String groupName,
            List<String> usernames
    ) {
        RoomDTO room = roomCreationService.makeGroupRoom(groupName, usernames);
        room.setRoomType("group");
        return ResponseEntity.ok(room);
    }
}
