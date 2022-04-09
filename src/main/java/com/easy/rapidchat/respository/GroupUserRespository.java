package com.easy.rapidchat.respository;

import com.easy.rapidchat.model.RoomUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author Rishikesh
 * @project RapidChat
 */
public interface GroupUserRespository extends JpaRepository<RoomUser, UUID> {
}
