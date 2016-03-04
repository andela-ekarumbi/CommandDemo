package com.andela.commanddemo.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

import com.andela.commanddemo.interfaces.Command;

public class CommandButton extends Button {

    private Command command;

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    private boolean isButtonPressed = false;

    public boolean isButtonPressed() {
        return isButtonPressed;
    }

    public void setIsButtonPressed(boolean isButtonPressed) {
        this.isButtonPressed = isButtonPressed;
    }

    public CommandButton(Context context) {
        super(context);
    }

    public CommandButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CommandButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
