package com.chad.hlife.eventbus;

public class EventMessage {

    private int type;
    private Object object;

    public EventMessage() {

    }

    public EventMessage(int type, Object object) {
        this.type = type;
        this.object = object;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Object getObject() {
        return object;
    }
}
