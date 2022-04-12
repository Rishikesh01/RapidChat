package com.easy.rapidchat.service;

import com.easy.rapidchat.dtos.MessageDTO;
import com.easy.rapidchat.mapper.MessageDTOMapper;
import com.easy.rapidchat.model.Message;
import com.easy.rapidchat.model.Room;
import com.easy.rapidchat.model.UserDetail;
import com.easy.rapidchat.respository.MessageRespository;
import com.easy.rapidchat.respository.RoomRepository;
import com.easy.rapidchat.respository.UserDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Rishikesh
 * @project RapidChat
 */
@RequiredArgsConstructor
@Service
public class MessagingService {
    private static final String GROUP_MESSAGE_URL_PREFIX = "/topic/group.";
    private static final String USER_MESSAGE_URL_PREFIX = "/topic/user.";
    private static final int PAGE_SIZE = 50;

    private final SimpMessagingTemplate messagingTemplate;
    private final MessageDTOMapper messageDTOMapper;
    private final MessageRespository messageRespository;
    private final RoomRepository roomRepository;
    private final UserDetailsRepository userDetailsRepository;

    public void sendMessageToUser(MessageDTO messageDTO) {
        messagingTemplate.convertAndSend(
                USER_MESSAGE_URL_PREFIX + messageDTO.getChannelID().toString(),
                messageDTO
        );
        persistMessage(messageDTO);
    }

    public void sendMessageToGroup(MessageDTO messageDTO) {
        messagingTemplate.convertAndSend(
                GROUP_MESSAGE_URL_PREFIX + messageDTO.getChannelID().toString(),
                messageDTO
        );
        persistMessage(messageDTO);
    }

    public List<MessageDTO> getAllTheLatestMessages(UserDetail userDetail) {
        return new ArrayList<>(messageDTOMapper.
                toListMessageDTO
                        (messageRespository.
                                findNewMessagesByUserDetail(userDetail, PageRequest.of(0, PAGE_SIZE))));
    }

    public List<MessageDTO> getUserDMS(String dm, int offset) {
        List<Message> userDMSPage = messageRespository
                .findByUserDetailAndUserDetailOrderByTimeOfMsgDesc(UUID.fromString(dm), PageRequest.of(offset, PAGE_SIZE));
        return new ArrayList<>(messageDTOMapper.toListMessageDTO(userDMSPage));
    }

    public List<MessageDTO> getUserGroupMessages(UUID group, int offset) {
        List<Message> userGroupMessages = messageRespository
                .findByRoomOrderByTimeOfMsgDesc(group, PageRequest.of(offset, PAGE_SIZE));
        return new ArrayList<>(messageDTOMapper.toListMessageDTO(userGroupMessages));
    }

    private void persistMessage(MessageDTO messageDTO) {
        Message message = messageDTOMapper.toMessageModel(messageDTO);
        Room room = roomRepository.findById(messageDTO.getRoomUID()).orElse(null);
        System.out.println(room);
        UserDetail userDetail = userDetailsRepository.findById(messageDTO.getSenderID()).orElse(null);
        message.setRoom(room);
        message.setUserDetail(userDetail);
        message.setTimeOfMsg(LocalTime.now());
        messageRespository.save(message);
    }

}
