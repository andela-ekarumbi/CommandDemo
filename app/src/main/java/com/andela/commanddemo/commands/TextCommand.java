package com.andela.commanddemo.commands;

import com.andela.commanddemo.interfaces.Command;
import com.andela.commanddemo.interfaces.TextFormatReceiver;

public abstract class TextCommand implements Command {
    protected TextFormatReceiver textFormatReceiver;

    public TextCommand(TextFormatReceiver textFormatReceiver) {
        this.textFormatReceiver = textFormatReceiver;
    }
}
