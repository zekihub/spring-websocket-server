package com.web.socket.websocket.server.controller;

import com.google.gson.Gson;
import com.web.socket.websocket.bean.MessageBean;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Controller
public class SocketController {

    private Gson gson = new Gson();


    @MessageMapping("/user-all")
    @SendTo("/topic/all")
    public Map<String, String> sendToAll(@Payload MessageBean message) {
        System.out.println("name: " + message.getName() + "message: " + message.getMessage());
        Map<String, String> map = new HashMap<>();
        map.put("from", "topic/all");
        map.put("message", message.toString());
        return map;
    }

    @MessageMapping("/user-specific")
    @SendToUser("/queue/user")
    public Map<String, String> processMessageFromClient(@Payload String message) throws Exception {
        System.out.println("sendnamedeyiz name: " + message);
        Map<String, String> map = new HashMap<>();
        map.put("from", "queue");
        map.put("message", gson.fromJson(message, Map.class).get("name").toString());
        return map;
        //return gson.fromJson(message, Map.class).get("name").toString();
    }
}
