package com.web.socket.websocket.bean;


import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageBean {
    @JsonProperty("name")
    private String name;

    @JsonProperty("message")
    private String message;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
