package com.easy.rapidchat.controller;

import com.easy.rapidchat.dtos.MessageDTO;
import com.easy.rapidchat.dtos.RoomDTO;
import com.easy.rapidchat.model.UserDetail;
import com.easy.rapidchat.service.MessagingService;
import com.easy.rapidchat.service.RoomService;
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
@RequestMapping("/api/v1/user")
public class MessagingController {
    private final MessagingService messagingService;
    private final RoomService roomService;

    @GetMapping("/all/rooms")
    public ResponseEntity<List<RoomDTO>> getAllTheRooms(
            @AuthenticationPrincipal UserDetail userDetail
    ) {
        List<RoomDTO> rooms = roomService.getAllTheRooms(userDetail);
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/new/message")
    public ResponseEntity<List<MessageDTO>> getAllTheLatestMessages(
            @AuthenticationPrincipal UserDetail userDetail
    ) {
        List<MessageDTO> messages = messagingService.getAllTheLatestMessages(userDetail);

        return ResponseEntity.ok(messages);
    }

    @GetMapping("/old/messages/{dm}/{offset}")
    public ResponseEntity<List<MessageDTO>> getUserDMS(
            @AuthenticationPrincipal UserDetail userDetail,
            @PathVariable("offset") int offset,
            @PathVariable("dm") String dm
    ) {
        List<MessageDTO> messages = messagingService.getUserDMS(userDetail, offset);
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/old/messages/{group}/{offset}")
    public ResponseEntity<List<MessageDTO>> getGroupMessages(
            @AuthenticationPrincipal UserDetail userDetail,
            @PathVariable("offset") int offset,
            @PathVariable("group") String group
    ) {
        List<MessageDTO> groupMessages = messagingService.getUserGroupMessages(userDetail, offset);
        return ResponseEntity.ok(groupMessages);
    }
}
