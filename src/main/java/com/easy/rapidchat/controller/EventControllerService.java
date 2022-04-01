package com.easy.rapidchat.controller;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.messaging.SessionConnectEvent;

/**
 * @author Rishikesh
 * @project RapidChat
 */
@Component
public class EventControllerService {
@EventListener
    public void isConnected(SessionConnectEvent event){
    System.out.println("connected");

}

}
