package com.web.socket.websocket.client;

import com.web.socket.websocket.bean.MessageBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.stomp.*;

import java.lang.reflect.Type;

/**
 * This class is an implementation for <code>StompSessionHandlerAdapter</code>.
 * Once a connection is established, We subscribe to /topic/messages and
 * send a sample message to server.
 *
 * @author Kalyan
 *
 */
public class MyStompSessionHandler extends StompSessionHandlerAdapter {

    private Logger logger = LogManager.getLogger(MyStompSessionHandler.class);


    public static StompSession getSessionG() {
        return sessionG;
    }

    public static void setSessionG(StompSession sessionG) {
        MyStompSessionHandler.sessionG = sessionG;
    }

    public static StompSession sessionG;

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        sessionG = session;
        logger.info("New session established : " + session.getSessionId());
        session.subscribe("/topic/user", this);
        logger.info("Subscribed to /topic/user");
        int i = 0;
        while(i < 4){
            session.send("/app/user-all", getSampleMessage());
            i++;
        }

        logger.info("Message sent to websocket server");
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        logger.error("Got an exception", exception);
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return MessageBean.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        MessageBean msg = (MessageBean) payload;
        logger.info("Received : " + msg.getMessage() + " from : " + msg.getName());
    }

    /**
     * A sample message instance.
     * @return instance of <code>Message</code>
     */
    private MessageBean getSampleMessage() {
        MessageBean msg = new MessageBean();
        msg.setName("çağrı");
        msg.setMessage("naber!!");
        return msg;
    }

}