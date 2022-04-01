package com.easy.rapidchat.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Rishikesh
 * @project RapidChat
 */
@Data
@NoArgsConstructor
public class UserDetailsDTO {
    private String username;
    private String password;
    private String confirmPassword;
}
