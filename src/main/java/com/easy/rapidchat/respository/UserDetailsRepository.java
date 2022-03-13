package com.easy.rapidchat.respository;

import com.easy.rapidchat.model.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author Rishikesh
 * @project RapidChat
 */
public interface UserDetailsRepository extends JpaRepository<UserDetail, UUID> {
    UserDetail findByUsername(String username);
}
