package com.easy.rapidchat.respository;

import com.easy.rapidchat.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author Rishikesh
 * @project RapidChat
 */
public interface MessageRespository extends JpaRepository<Message, UUID> {
}
