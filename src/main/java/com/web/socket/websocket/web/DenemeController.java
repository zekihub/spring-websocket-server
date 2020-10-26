package com.web.socket.websocket.web;

import com.web.socket.websocket.bean.MessageBean;
import com.web.socket.websocket.client.MyStompSessionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/api")
public class DenemeController {



    @GetMapping(value="/deneme")
    public String deneme(){
        MessageBean mb = new MessageBean();
        mb.setName("kamur");
        mb.setMessage("slm, ben kamur :)");
        MyStompSessionHandler.sessionG.send("/app/user-all", mb);

        return "ok";
    }
}
