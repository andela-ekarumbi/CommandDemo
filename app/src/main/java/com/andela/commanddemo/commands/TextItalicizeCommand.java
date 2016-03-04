package com.andela.commanddemo.commands;

import com.andela.commanddemo.interfaces.TextFormatReceiver;

public class TextItalicizeCommand extends TextCommand {

    public TextItalicizeCommand(TextFormatReceiver textFormatReceiver) {
        super(textFormatReceiver);
    }

    @Override
    public void execute() {
        textFormatReceiver.italicizeSelection();
    }

    @Override
    public void undo() {
        textFormatReceiver.undoSelectionFormatting();
    }
}
