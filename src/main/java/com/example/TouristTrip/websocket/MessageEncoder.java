package com.example.TouristTrip.websocket;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.example.TouristTrip.model.MessageChat;
import com.google.gson.Gson;

public class MessageEncoder implements Encoder.Text<MessageChat> {

    private static Gson gson = new Gson();

    @Override
    public String encode(MessageChat message) throws EncodeException {
        String json = gson.toJson(message);
        return json;
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
