package com.easy.rapidchat.controller;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

/**
 * @author Rishikesh
 * @project RapidChat
 */
@Component
public class EventControllerService {
    @EventListener
    public void isConnected(SessionConnectEvent event) {
    }

    @EventListener
    public void disconnected(SessionDisconnectEvent event) {

    }

}
