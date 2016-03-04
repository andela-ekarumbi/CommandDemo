# CommandDemo
A simple Android application used to demonstrate the Command design pattern.

##Command Pattern Brief
The Command design pattern enables us to encapsulate a request as an object, thereby letting us parameterize clients with different requests, queue or log requests, and support undoable operations.
  
When implementing the command pattern, three things need to exist:
* **The client**: instantiates the command object and provides the information required to call the method at a later time.
* **The invoker**: decides when the method should be called.
* **The receiver**: is the object on which the instructions packaged in the command will be executed.

##Example Implementation

###The Command Interface
```java
package com.andela.commanddemo.interfaces;


public interface Command {
   void execute();

   void undo();
}

```
This interface defines the **execute** and **undo** operations, which encapsulate the instructions we want executed by a command object.

###The Receiver Interface

```java
package com.andela.commanddemo.interfaces;

public interface TextFormatReceiver {
    void makeSelectionBold();

    void underlineSelection();

    void italicizeSelection();

    void undoSelectionFormatting();
}
```
This interface defined the set of operations that may be encapsulated as commands. Every command object will be composed with (i.e. have a private variable of) an object implementing this interface.

###The Invoker Class
```java
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

```
The CommandButton class is our invoker. Whenever an action (click) is performed on the button, the command associated with that button is invoked.

###The Client Class

```java
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        CommandButton commandButton = (CommandButton)v; 
        Command command = commandButton.getCommand(); 
        if (commandButton.isButtonPressed()) {
            command.undo();
            commandButton.setIsButtonPressed(false);
        } else {
            command.execute(); //execute
            commandButton.setIsButtonPressed(true);
        }
    }
}

```
The MainActivity class is involved with setting up command objects and associating them with the respective invokers.
