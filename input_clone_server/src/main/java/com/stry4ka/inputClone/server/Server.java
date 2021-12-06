package com.stry4ka.inputClone.server;

import com.stry4ka.inputClone.common.GlobalVariable;
import com.stry4ka.inputClone.model.common.Message;
import com.stry4ka.inputClone.utils.MessageUtil;

import java.io.IOException;
import java.net.*;

public class Server implements SocketMessageSend {
    private DatagramSocket ds;
    private SocketAddress address;

    public Server(int port) {
        GlobalVariable.port = port;
        setupHostAddress();
    }

    public void send(Message message) {
        byte[] data = MessageUtil.toByte(message);
        DatagramPacket pack = new DatagramPacket(data, data.length, address);
        try {
            ds.send(pack);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void switchInputDevice(boolean thisDevice) {
        try {
            if (thisDevice)
                ds.close();
            else
                ds = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    private void setupHostAddress() {
        address = new InetSocketAddress(GlobalVariable.getHost(), GlobalVariable.port);
    }
}
