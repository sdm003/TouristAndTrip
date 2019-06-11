package com.example.TouristTrip.websocket;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;


import com.example.TouristTrip.model.MessageChat;
import com.google.gson.Gson;

public class MessageDecoder implements Decoder.Text<MessageChat> {

    private static Gson gson = new Gson();

    @Override
    public MessageChat decode(String s) throws DecodeException {
        MessageChat message = gson.fromJson(s, MessageChat.class);
        return message;
    }

    @Override
    public boolean willDecode(String s) {
        return (s != null);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
        // Custom initialization logic
    }

    @Override
    public void destroy() {
        // Close resources
    }
}