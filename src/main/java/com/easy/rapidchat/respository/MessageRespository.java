package com.easy.rapidchat.respository;

import com.easy.rapidchat.model.Message;
import com.easy.rapidchat.model.UserDetail;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

/**
 * @author Rishikesh
 * @project RapidChat
 */
public interface MessageRespository extends JpaRepository<Message, UUID> {
    @Query(
            nativeQuery = true,
            value = "select * from message  inner join" +
                    " room_user on " +
                    " message.room_id=room_user.room_id" +
                    " where room_user.user_detail_id=?1 order by time_of_msg desc ")
    List<Message> findNewMessagesByUserDetail(UserDetail userDetail, Pageable pageable);

    @Query(
            nativeQuery = true,
            value = "select * from message as msg inner  join" +
                    " room on msg.room_id=room.id " +
                    " where room.id=?1 " +
                    " order by time_of_msg desc ",
            countQuery = "select count (id) from message inner  join" +
                    " room on messsage.room_id=room.id " +
                    " where room.id=?1 "
    )
    List<Message> findByUserDetailAndUserDetailOrderByTimeOfMsgDesc(UUID roomID, Pageable pageable);

    @Query(
            nativeQuery = true,
            value = "select * from message as msg inner  join" +
                    " room on msg.room_id=room.id " +
                    " where room.id=?1 " +
                    " order by time_of_msg desc ",
            countQuery = "select count (id) from message inner  join" +
                    " room on messsage.room_id=room.id " +
                    " where room.id=?1 "
    )
    List<Message> findByRoomOrderByTimeOfMsgDesc(UUID roomID, Pageable pageable);
}
