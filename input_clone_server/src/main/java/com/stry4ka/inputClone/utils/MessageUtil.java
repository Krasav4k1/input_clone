package com.stry4ka.inputClone.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stry4ka.inputClone.model.common.Message;

import java.io.IOException;

public class MessageUtil {

    public static byte[] toByte(Message message) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        byte[] data = new byte[0];
        try {
            data = mapper.writeValueAsBytes(message);
        } catch (IOException e) {
            System.err.println("Parser error");
            e.printStackTrace();
        }
        return data;
    }
}
