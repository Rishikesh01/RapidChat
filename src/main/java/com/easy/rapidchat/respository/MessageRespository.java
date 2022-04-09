package com.easy.rapidchat.respository;

import com.easy.rapidchat.model.Message;
import com.easy.rapidchat.model.Room;
import com.easy.rapidchat.model.UserDetail;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * @author Rishikesh
 * @project RapidChat
 */
public interface MessageRespository extends JpaRepository<Message, UUID> {
    List<Message> findByUserDetailAndRoom(UserDetail userDetail, Room groupName, Pageable pageable);

    List<Message> findByUserDetailAndDirectMessageIsFalse(UserDetail userDetail, Pageable pageable);

    List<Message> findByUserDetailAndDirectMessageIsTrue(UserDetail userDetail, Pageable pageable);
}
