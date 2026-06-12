package com.ltc.companymanagementsystem.controller;

import com.ltc.companymanagementsystem.entity.ChatMessage;
import com.ltc.companymanagementsystem.service.UserTracker;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class ChatController {

    private final UserTracker  userTracker;

    public ChatController(UserTracker userTracker) {
        this.userTracker = userTracker;
    }

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public ChatMessage sendMessage(ChatMessage chatMessage) {
        return chatMessage;
    }


    @MessageMapping("/join")
    @SendTo("/topic/messages")
    public ChatMessage join(ChatMessage message) {

        userTracker.addUser(message.getSender());

//        return new ChatMessage(
//                "Bildirish",
//                message.getSender() + " Qoshuldu. Online: " +
//                        userTracker.getOnlineCount(),
//                "Qoshuldu"
//        );


        message.setTime(LocalDateTime.now());
        message.setType("JOIN");

        message.setContent(message.getSender() + " joined room: " + message.getRoom());

        return message;
    }

//    @MessageMapping("/leave")
//    @SendTo("/topic/messages")
//    public ChatMessage leave(ChatMessage message) {
//
//        userTracker.removeUser(message.getSender());
//
//        return new ChatMessage(
//                "Bildirish ! ",
//                message.getSender() + " Chixdi. Online: " +
//                        userTracker.getOnlineCount(),
//                "Chixdi"
//        );
//    }
}
