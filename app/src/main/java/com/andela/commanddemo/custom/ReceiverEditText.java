package com.andela.commanddemo.custom;


import android.content.Context;
import android.text.Html;
import android.util.AttributeSet;
import android.widget.EditText;

import com.andela.commanddemo.interfaces.TextFormatReceiver;

public class ReceiverEditText extends EditText implements  TextFormatReceiver{

    private int selectionStart;

    private int selectionStop;

    @Override
    public void makeSelectionBold() {
        applyFormatting("<b>", "</b>");
    }

    @Override
    public void underlineSelection() {
        applyFormatting("<u>", "</u>");
    }

    @Override
    public void italicizeSelection() {
        applyFormatting("<i>", "</i>");
    }

    @Override
    public void undoSelectionFormatting() {
        if (isSelectionRangeValid()) {
            getSelectionRanges();
            String currentText = this.getText().toString();
            String formatSection = currentText.substring(selectionStart, selectionStop);
            String replacementSection = formatSection.replace("<[a-z/]{1,}>", "");
            this.setText(currentText.replace(formatSection, replacementSection));
        }
    }

    private void applyFormatting(String startTag, String endTag) {
        if (isSelectionRangeValid()) {
            getSelectionRanges();
            String currentText = this.getText().toString();
            String formatSection = currentText.substring(selectionStart, selectionStop);
            String htmlString = startTag + formatSection + endTag;
            currentText = currentText.replace(formatSection, htmlString);
            this.setText(Html.fromHtml(currentText));
        }
    }

    private void getSelectionRanges() {
        selectionStart = this.getSelectionStart();
        selectionStop = this.getSelectionEnd();
    }

    private boolean isValidSelection() {
        return !isTextEmpty() && isSelectionRangeValid();
    }

    private boolean isTextEmpty() {
        return this.getText().toString().length() > 0;
    }

    private boolean isSelectionRangeValid() {
        getSelectionRanges();
        return (Math.abs(selectionStart - selectionStop)) > 0;
    }

    public ReceiverEditText(Context context) {
        super(context);
    }

    public ReceiverEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ReceiverEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
