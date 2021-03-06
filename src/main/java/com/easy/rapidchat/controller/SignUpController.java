package com.easy.rapidchat.controller;

import com.easy.rapidchat.dtos.UserDetailsDTO;
import com.easy.rapidchat.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Rishikesh
 * @project RapidChat
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/signup")
public class SignUpController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<HttpStatus> createUser(@RequestBody UserDetailsDTO userDetailsDTO) {
        userService.save(userDetailsDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
