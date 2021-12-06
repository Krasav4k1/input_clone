package com.stry4ka.inputClone.nativecpp;

import com.stry4ka.inputClone.common.GlobalVariable;
import com.stry4ka.inputClone.model.common.Keyboard;
import com.stry4ka.inputClone.model.common.Message;
import com.stry4ka.inputClone.model.common.Mouse;
import com.stry4ka.inputClone.server.SocketMessageSend;
import com.stry4ka.inputClone.utils.KeyUtil;

public class NativeListenerCPP {
    private SocketMessageSend sms;
    private Message message = new Message();
    private int hotKey;

    static {
        System.loadLibrary("nativelistener");
    }

    public NativeListenerCPP(SocketMessageSend sms, int hotKey) {
        this.sms = sms;
        this.hotKey = hotKey;
        this.setupListener();
    }

    private native void setupListener();

    private native void blockInput(boolean block);

    public void eventKey(int key, boolean down) {
        handleHotKey(key, down);
    }

    public void eventMoveMouse(int x, int y) {
        mouseSend(new Mouse().moveMouse(x, y));
    }

    public void eventMouseLButton(boolean down) {
        mouseSend(new Mouse().buttonL(down));
    }

    public void eventMouseRButton(boolean down) {
        mouseSend(new Mouse().buttonR(down));
    }

    public void eventMouseWheel(int delta) {
        mouseSend(new Mouse().wheel(delta));
    }

    private void handleHotKey(int key, boolean down) {
        key = KeyUtil.getJavaKeyCode(key);
        if (key == this.hotKey) {
            if (down)
                eventSwitchInputDevice();
        } else {
            keySend(key, down);
        }
    }

    private void keySend(int key, boolean down) {
        if (!GlobalVariable.thisDevice) {
            message.setKey(new Keyboard(key, down));
            sms.send(message);
        }
    }

    private void mouseSend(Mouse mouse) {
        if (!GlobalVariable.thisDevice) {
            message.setMouse(mouse);
            sms.send(message);
        }
    }

    private void eventSwitchInputDevice() {
        sms.switchInputDevice(GlobalVariable.changeDevice());
        blockInput(!GlobalVariable.isThisDevice());
    }
}