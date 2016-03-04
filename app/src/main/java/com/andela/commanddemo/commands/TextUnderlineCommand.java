package com.andela.commanddemo.commands;

import com.andela.commanddemo.interfaces.TextFormatReceiver;

public class TextUnderlineCommand extends TextCommand {

    public TextUnderlineCommand(TextFormatReceiver textFormatReceiver) {
        super(textFormatReceiver);
    }

    @Override
    public void execute() {
        textFormatReceiver.underlineSelection();
    }

    @Override
    public void undo() {
        textFormatReceiver.undoSelectionFormatting();
    }
}
