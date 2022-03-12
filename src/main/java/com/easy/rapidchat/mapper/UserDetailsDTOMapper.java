package com.easy.rapidchat.mapper;

import com.easy.rapidchat.dtos.UserDetailsDTO;
import com.easy.rapidchat.model.UserDetail;
import org.mapstruct.Mapper;

/**
 * @author Rishikesh
 * @project RapidChat
 */
@Mapper(componentModel = "spring")
public interface UserDetailsDTOMapper {
    UserDetail toUserDetails(UserDetailsDTO userDetailsDTO);
}
