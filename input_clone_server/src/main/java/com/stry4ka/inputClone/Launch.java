package com.stry4ka.inputClone;

import com.stry4ka.inputClone.nativecpp.NativeListenerCPP;
import com.stry4ka.inputClone.server.Server;

import java.awt.event.KeyEvent;

public class Launch {
    public static void main(String[] args)  {
        Server server = new Server(4565);
        new NativeListenerCPP(server, KeyEvent.VK_SCROLL_LOCK);
    }
}
