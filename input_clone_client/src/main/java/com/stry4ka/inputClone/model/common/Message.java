package com.stry4ka.inputClone.model.common;

import java.io.Serializable;

public class Message implements Serializable {
    private static final long serialVersionUID = 8820245574564782551L;
    private Keyboard key;
    private Mouse mouse;

    public Message() {
    }

    public Message(Keyboard key, Mouse mouse) {
        this.key = key;
        this.mouse = mouse;
    }

    public Keyboard getKey() {
        return key;
    }

    public boolean isKey() {
        return this.key != null;
    }

    public boolean isMouse() {
        return this.mouse != null;
    }

    public void setKey(Keyboard key) {
        this.mouse = null;
        this.key = key;
    }

    public Mouse getMouse() {
        return mouse;
    }

    public void setMouse(Mouse mouse) {
        this.key = null;
        this.mouse = mouse;
    }
}
