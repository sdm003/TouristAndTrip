package com.example.TouristTrip.model;

public class Message {
    Object object;
    private String message;

    public Message(String message,Object object) {
        this.message = message;
        this.object=object;
    }

    public Message() {
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
