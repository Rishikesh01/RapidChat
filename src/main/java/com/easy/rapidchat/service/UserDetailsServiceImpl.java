package com.easy.rapidchat.service;

import com.easy.rapidchat.model.UserDetail;
import com.easy.rapidchat.respository.UserDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Rishikesh
 * @project RapidChat
 */
@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserDetailsRepository userDetailsRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDetail result = userDetailsRepository.findByUsername(username);
        if (result != null)
            return result;
        throw new UsernameNotFoundException("user with username" + username + "not found");
    }
}
