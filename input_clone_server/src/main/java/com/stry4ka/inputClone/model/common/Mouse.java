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

    public int getMouseX() {
        return mouseX;
    }

    public void setMouseX(int mouseX) {
        this.mouseX = mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public void setMouseY(int mouseY) {
        this.mouseY = mouseY;
    }

    public int getWheelDelta() {
        return wheelDelta;
    }

    public void setWheelDelta(int wheelDelta) {
        this.wheelDelta = wheelDelta;
    }

    public boolean getButtonR() {
        return buttonR;
    }

    public void setButtonR(boolean buttonR) {
        this.buttonR = buttonR;
    }

    public boolean getButtonRDown() {
        return buttonRDown;
    }

    public void setButtonRDown(boolean buttonRDown) {
        this.buttonRDown = buttonRDown;
    }

    public boolean getButtonL() {
        return buttonL;
    }

    public void setButtonL(boolean buttonL) {
        this.buttonL = buttonL;
    }

    public boolean getButtonLDown() {
        return buttonLDown;
    }

    public void setButtonLDown(boolean buttonLDown) {
        this.buttonLDown = buttonLDown;
    }
}
