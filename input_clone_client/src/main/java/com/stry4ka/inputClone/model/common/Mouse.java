package com.stry4ka.inputClone.model.common;

import java.io.Serializable;

public class Mouse implements Serializable {
    private static final long serialVersionUID = 8485066551806179177L;
    private int mouseX = 0;
    private int mouseY = 0;
    private int wheelDelta = 0;
    private boolean buttonR = false;
    private boolean buttonRDown = false;
    private boolean buttonL = false;
    private boolean buttonLDown = false;

    public Mouse() {
    }

    public Mouse moveMouse(int x, int y) {
        this.mouseX = x;
        this.mouseY = y;
        return this;
    }

    public Mouse buttonL(boolean down) {
        this.buttonL = true;
        this.buttonLDown = down;
        return this;
    }

    public Mouse buttonR(boolean down) {
        this.buttonR = true;
        this.buttonRDown = down;
        return this;
    }

    public Mouse wheel(int wheelDelta) {
        this.wheelDelta = wheelDelta;
        return this;
    }

    public boolean isMoveMouse() {
        return this.mouseX != 0 && this.mouseY != 0;
    }

    public boolean isWheel() {
        return this.wheelDelta != 0;
    }

    public boolean isButtonR() {
        return this.buttonR;
    }

    public boolean isButtonL() {
        return this.buttonL;
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public int getWheelDelta() {
        return wheelDelta;
    }

    public boolean getButtonRDown() {
        return buttonRDown;
    }

    public boolean getButtonLDown() {
        return buttonLDown;
    }
}
