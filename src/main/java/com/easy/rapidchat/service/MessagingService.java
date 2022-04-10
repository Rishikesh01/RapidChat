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

/*    @PostConstruct
    public void init() {
        String[] roomUID = {"eefd2853-3534-430a-980c-a3fe98caea71",
                "08f03925-63c4-427a-89ba-55994e38003d",
                "f9a5850a-e003-4195-8640-41de28ad54ef",
                "3aecb7f9-3aa7-41d4-8fd8-91542eeca185"};
        String[] chatName = {"test_test1", "test1_tim", "tim_tom", "tom_test"};
        String[] users = {"test", "test1", "tom", "tim"};
        String[] content = {"hi this is tim",
                "tam bam",
                "boom kkbang",
                "titanic",
                "test message from test",
                "test1 message",
                "7ths msg",
                "8th msg",
                "9 msg",
                "10 msg"};
        MessageDTO messageDTO1 = new MessageDTO();
        MessageDTO messageDTO2 = new MessageDTO();
        MessageDTO messageDTO3 = new MessageDTO();
        MessageDTO messageDTO4 = new MessageDTO();
        MessageDTO messageDTO5 = new MessageDTO();
        MessageDTO messageDTO6 = new MessageDTO();
        MessageDTO messageDTO7 = new MessageDTO();
        MessageDTO messageDTO8 = new MessageDTO();
        MessageDTO messageDTO9 = new MessageDTO();
        MessageDTO messageDTO10 = new MessageDTO();

        //1 0chat
        messageDTO1.setChatName(chatName[0]);
        messageDTO1.setRoomUID(UUID.fromString(roomUID[0]));
        messageDTO1.setSenderID(users[0]);
        messageDTO1.setContent(content[0]);
//2 0chat
        messageDTO2.setChatName(chatName[0]);
        messageDTO2.setRoomUID(UUID.fromString(roomUID[0]));
        messageDTO2.setSenderID(users[1]);
        messageDTO2.setContent(content[1]);
//3
        messageDTO3.setChatName(chatName[1]);
        messageDTO3.setRoomUID(UUID.fromString(roomUID[1]));
        messageDTO3.setSenderID(users[1]);
        messageDTO3.setContent(content[2]);
//4
        messageDTO4.setChatName(chatName[1]);
        messageDTO4.setRoomUID(UUID.fromString(roomUID[0]));
        messageDTO4.setSenderID(users[3]);
        messageDTO4.setContent(content[3]);
//5
        messageDTO5.setChatName(chatName[1]);
        messageDTO5.setRoomUID(UUID.fromString(roomUID[1]));
        messageDTO5.setSenderID(users[3]);
        messageDTO5.setContent(content[4]);
//6
        messageDTO6.setChatName(chatName[1]);
        messageDTO6.setRoomUID(UUID.fromString(roomUID[1]));
        messageDTO6.setSenderID(users[1]);
        messageDTO6.setContent(content[5]);
//7  3rd chat
        messageDTO7.setChatName(chatName[3]);
        messageDTO7.setRoomUID(UUID.fromString(roomUID[3]));
        messageDTO7.setSenderID(users[2]);
        messageDTO7.setContent(content[6]);
//8 3rd chat
        messageDTO8.setChatName(chatName[3]);
        messageDTO8.setRoomUID(UUID.fromString(roomUID[3]));
        messageDTO8.setSenderID(users[0]);
        messageDTO8.setContent(content[7]);
//9
        messageDTO9.setChatName(chatName[2]);
        messageDTO9.setRoomUID(UUID.fromString(roomUID[2]));
        messageDTO9.setSenderID(users[3]);
        messageDTO9.setContent(content[8]);
        //10
        messageDTO10.setChatName(chatName[2]);
        messageDTO10.setRoomUID(UUID.fromString(roomUID[2]));
        messageDTO10.setSenderID(users[2]);
        messageDTO10.setContent(content[9]);

        List<MessageDTO> messageDTOS = new ArrayList<>();
        messageDTOS.add(messageDTO1);
        messageDTOS.add(messageDTO2);
        messageDTOS.add(messageDTO3);
        messageDTOS.add(messageDTO4);
        messageDTOS.add(messageDTO5);
        messageDTOS.add(messageDTO6);
        messageDTOS.add(messageDTO7);
        messageDTOS.add(messageDTO8);
        messageDTOS.add(messageDTO9);
        messageDTOS.add(messageDTO10);

        for (MessageDTO messageDTO : messageDTOS) {
            persistMessage(messageDTO, ROOM_TYPE_DIRECT_MESSAGE);
        }
    }*/
}
