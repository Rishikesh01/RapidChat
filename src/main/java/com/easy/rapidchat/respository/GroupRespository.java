package com.easy.rapidchat.respository;

import com.easy.rapidchat.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author Rishikesh
 * @project RapidChat
 */
public interface GroupRespository extends JpaRepository<Group, UUID> {
}
