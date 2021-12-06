package com.stry4ka.inputClone.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stry4ka.inputClone.model.common.Message;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

public class MessageUtil {

    public static Message fromByte(byte[] data) {
        ObjectMapper mapper = new ObjectMapper();
        Message message = null;
        try {
            message = mapper.readValue(data, Message.class);
        } catch (IOException e) {
            System.err.println("Parser error");
            e.printStackTrace();
        }
        return message;
    }
}
