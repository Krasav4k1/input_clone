package com.stry4ka.inputClone.model.common;

import java.io.Serializable;

public class Keyboard implements Serializable {
    private static final long serialVersionUID = -3788419762057237556L;
    private int key;
    private boolean down;

    public Keyboard() {
    }

    public Keyboard(int key, boolean down) {
        this.key = key;
        this.down = down;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }
}
