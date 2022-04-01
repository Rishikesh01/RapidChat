package com.easy.rapidchat.service;

import com.easy.rapidchat.dtos.UserDetailsDTO;
import com.easy.rapidchat.mapper.UserDetailsDTOMapper;
import com.easy.rapidchat.model.UserDetail;
import com.easy.rapidchat.respository.UserDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Rishikesh
 * @project RapidChat
 */
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserDetailsDTOMapper userDetailsDTOMapper;
    private final UserDetailsRepository userDetailsRepository;
    private final BCryptPasswordEncoder encoder;
    public void save(UserDetailsDTO userDetailsDTO) {
        UserDetail userDetail = userDetailsDTOMapper.toUserDetails(userDetailsDTO);
        userDetail.setPassword(encoder.encode(userDetailsDTO.getPassword()));
        userDetailsRepository.save(userDetail);
    }
}
