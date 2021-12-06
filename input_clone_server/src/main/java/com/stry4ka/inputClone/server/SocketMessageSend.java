package com.stry4ka.inputClone.server;

import com.stry4ka.inputClone.common.SwitchInputDevice;
import com.stry4ka.inputClone.model.common.Message;

public interface SocketMessageSend extends SwitchInputDevice {
    void send(Message message);
}
