package com.easy.rapidchat.respository;

import com.easy.rapidchat.model.Room;
import com.easy.rapidchat.model.RoomUser;
import com.easy.rapidchat.model.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

/**
 * @author Rishikesh
 * @project RapidChat
 */
public interface RoomRepository extends JpaRepository<Room, UUID> {
    @Query(
            nativeQuery = true,
            value = "select * from room  inner join" +
                    " room_user on " +
                    " room.id=room_user.room_id" +
                    " where room_user.user_detail_id=?1")
    List<Room> joinRoomUserAndRoomOnUserId(UserDetail userdetail);

    Room findByTopicID(UUID topicID);

    void save(RoomUser roomUser);
}
