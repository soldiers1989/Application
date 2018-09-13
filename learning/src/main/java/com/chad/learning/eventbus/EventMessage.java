package com.chad.learning.eventbus;

public class EventMessage {
    private String message;

    public EventMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
