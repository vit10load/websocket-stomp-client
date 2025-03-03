package br.com.tech.websocket.app.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import br.com.tech.websocket.app.data.Message;

@Controller
public class MessageController {


    @MessageMapping("/socket")
    @SendTo("/topic/messages")
    public Message getMessage(Message message){

        return new Message(message.getContent());
        
    }
    
}
