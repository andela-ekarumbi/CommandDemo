package com.andela.commanddemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.andela.commanddemo.commands.TextBoldCommand;
import com.andela.commanddemo.commands.TextItalicizeCommand;
import com.andela.commanddemo.commands.TextUnderlineCommand;
import com.andela.commanddemo.custom.CommandButton;
import com.andela.commanddemo.custom.ReceiverEditText;
import com.andela.commanddemo.interfaces.Command;

public class MainActivity extends AppCompatActivity implements Button.OnClickListener {

    private CommandButton boldButton;

    private CommandButton italicButton;

    private CommandButton underlineButton;

    private ReceiverEditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initializeComponents();
    }

    private void initializeComponents() {
        boldButton = (CommandButton)findViewById(R.id.bold_button);
        italicButton = (CommandButton)findViewById(R.id.italic_button);
        underlineButton = (CommandButton)findViewById(R.id.underline_button);
        editText = (ReceiverEditText)findViewById(R.id.edit_text);

        TextBoldCommand textBoldCommand = new TextBoldCommand(editText);
        boldButton.setCommand(textBoldCommand);

        TextItalicizeCommand textItalicizeCommand = new TextItalicizeCommand(editText);
        italicButton.setCommand(textItalicizeCommand);

        TextUnderlineCommand textUnderlineCommand = new TextUnderlineCommand(editText);
        underlineButton.setCommand(textUnderlineCommand);

        boldButton.setOnClickListener(this);
        italicButton.setOnClickListener(this);
        underlineButton.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        CommandButton commandButton = (CommandButton)v; //bold_button
        Command command = commandButton.getCommand(); //textBoldCommand
        if (commandButton.isButtonPressed()) {
            command.undo();
            commandButton.setIsButtonPressed(false);
        } else {
            command.execute(); //execute
            commandButton.setIsButtonPressed(true);
        }
    }
}
