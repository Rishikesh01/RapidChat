package com.easy.rapidchat.dtos;

import lombok.Data;

/**
 * @author Rishikesh
 * @project RapidChat
 */
@Data
public class UserDetailsDTO {
    private String username;
    private String password;
    private String confirmPassword;
}
