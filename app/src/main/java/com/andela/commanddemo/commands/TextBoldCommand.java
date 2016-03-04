package com.andela.commanddemo.commands;

import com.andela.commanddemo.interfaces.TextFormatReceiver;

public class TextBoldCommand extends TextCommand {

    public TextBoldCommand(TextFormatReceiver textFormatReceiver) {
        super(textFormatReceiver);
    }

    @Override
    public void execute() {
        textFormatReceiver.makeSelectionBold();
    }

    @Override
    public void undo() {
        textFormatReceiver.undoSelectionFormatting();
    }
}
