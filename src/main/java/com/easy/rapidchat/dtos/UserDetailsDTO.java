package com.easy.rapidchat.dtos;

import lombok.Data;

/**
 * @author Rishikesh
 * @project RapidChat
 */
@Data
public class UserDetailsDTO {
    private String userName;
    private String password;
    private String confirmPassword;
}
