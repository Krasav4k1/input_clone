package com.stry4ka.inputClone.client;

import com.stry4ka.inputClone.model.common.Keyboard;
import com.stry4ka.inputClone.model.common.Message;
import com.stry4ka.inputClone.model.common.Mouse;
import com.stry4ka.inputClone.utils.KeyUtil;
import com.stry4ka.inputClone.utils.MessageUtil;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Client {

    private DatagramSocket ds;
    private Robot robot;

    public Client(int port) {
        try {
            ds = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public void listening() {
        while(true) {
            byte[] buffer = new byte[256];
            DatagramPacket pack = new DatagramPacket(buffer, buffer.length);
            try {
                ds.receive(pack);
                Message message = MessageUtil.fromByte(buffer);
                if (message != null) {
                    if (message.isKey())
                        keyEvent(message.getKey());
                    if (message.isMouse())
                        mouseEvent(message.getMouse());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void keyEvent(Keyboard keyboard) {
        int macKey = KeyUtil.getMacVkCode(keyboard.getKey());
        if (keyboard.isDown())
            robot.keyPress(macKey);
        else
            robot.keyRelease(macKey);
    }

    private void mouseEvent(Mouse mouse) {
        if (mouse.isMoveMouse())
            robot.mouseMove(mouse.getMouseX(), mouse.getMouseY());
        if (mouse.isWheel())
            robot.mouseWheel(mouse.getWheelDelta() > 0 ? 3 : -3);
        if (mouse.isButtonL())
            if (mouse.getButtonLDown())
                robot.mousePress(MouseEvent.BUTTON1_MASK);
            else
                robot.mouseRelease(MouseEvent.BUTTON1_MASK);
        if (mouse.isButtonR())
            if (mouse.getButtonRDown())
                robot.mousePress(MouseEvent.BUTTON3_MASK);
            else
                robot.mouseRelease(MouseEvent.BUTTON3_MASK);
    }
}
