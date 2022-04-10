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
                    " where room_user.user_detail_id=?1")
    List<Message> findAllByUserDetail(UserDetail userDetail);

    List<Message> findByUserDetailAndDirectMessageIsFalse(UserDetail userDetail, Pageable pageable);

    List<Message> findByUserDetailAndDirectMessageIsTrue(UserDetail userDetail, Pageable pageable);
}
