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

/**
 * @author Rishikesh
 * @project RapidChat
 */
@RequiredArgsConstructor
@Service
public class MessagingService {
    private static final String GROUP_MESSAGE_URL_PREFIX = "/topic/group.";
    private static final String USER_MESSAGE_URL_PREFIX = "/topic/user.";
    private static final String ROOM_TYPE_GROUP = "group";
    private static final String ROOM_TYPE_DIRECT_MESSAGE = "directMessage";
    private static final int PAGE_SIZE = 50;

    private final SimpMessagingTemplate messagingTemplate;
    private final MessageDTOMapper messageDTOMapper;
    private final MessageRespository messageRespository;
    private final RoomRepository roomRepository;
    private final UserDetailsRepository userDetailsRepository;

    public void sendMessageToUser(MessageDTO messageDTO) {
        messagingTemplate.convertAndSend(
                USER_MESSAGE_URL_PREFIX + messageDTO.getChannelID(),
                messageDTO
        );
        persistMessage(messageDTO, ROOM_TYPE_DIRECT_MESSAGE);
    }

    public void sendMessageToGroup(MessageDTO messageDTO) {
        messagingTemplate.convertAndSend(
                GROUP_MESSAGE_URL_PREFIX + messageDTO.getChannelID(),
                messageDTO
        );
        persistMessage(messageDTO, ROOM_TYPE_GROUP);
    }

    public List<MessageDTO> getAllTheLatestMessages(UserDetail userDetail) {
        return new ArrayList<>(messageDTOMapper.
                toListMessageDTO
                        (messageRespository.
                                findAllByUserDetail(userDetail)));
    }

    public List<MessageDTO> getUserDMS(UserDetail userDetail, int offset) {
        List<Message> userDMSPage = messageRespository
                .findByUserDetailAndDirectMessageIsFalse(userDetail, PageRequest.of(offset, PAGE_SIZE));
        return new ArrayList<>(messageDTOMapper.toListMessageDTO(userDMSPage));
    }

    public List<MessageDTO> getUserGroupMessages(UserDetail user, int offset) {
        List<Message> userGroupMessages = messageRespository
                .findByUserDetailAndDirectMessageIsTrue(user, PageRequest.of(offset, PAGE_SIZE));
        return new ArrayList<>(messageDTOMapper.toListMessageDTO(userGroupMessages));
    }

    private void persistMessage(MessageDTO messageDTO, String typeOfRoom) {
        Message message = messageDTOMapper.toMessageModel(messageDTO);
        Room room = roomRepository.findByTopicID(messageDTO.getChannelID());
        System.out.println(room.toString());
        UserDetail userDetail = userDetailsRepository.findByUsername(messageDTO.getSenderID());
        message.setDirectMessage(!typeOfRoom.equals(ROOM_TYPE_GROUP));
        message.setRoom(room);
        message.setUserDetail(userDetail);
        message.setTimeOfMsg(LocalTime.now());
        messageRespository.save(message);
    }
}
