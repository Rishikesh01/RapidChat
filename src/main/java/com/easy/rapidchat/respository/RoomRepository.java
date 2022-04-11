package com.easy.rapidchat.respository;

import com.easy.rapidchat.model.Room;
import com.easy.rapidchat.model.RoomUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author Rishikesh
 * @project RapidChat
 */
public interface RoomRepository extends JpaRepository<Room, UUID> {
    Room findByTopicID(String topicId);

    Room findByChatName(String name);

    void save(RoomUser roomUser);
}
