package com.example.kirillantonov.calculator;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView view;
    private String curText;
    private Parser parser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = (TextView) findViewById(R.id.textView);
        if (view == null) {
            view = (TextView) findViewById(R.id.textView2);
        }
        parser = new Parser();
        curText = "";
        updateText();
    }

    private void updateText() {
        view.setText(curText);
    }

    private Button getButton(View pressed) {
        return (Button) pressed;
    }

    public void onSimpleClick(View pressed) {
        Button button = getButton(pressed);
        curText = curText.concat(button.getText().toString());
        updateText();
    }

    public void onOperationClick(View pressed) {
        Button button = getButton(pressed);
        String t = " ";
        curText = curText.concat(t.concat(button.getText().toString().concat(t)));
        updateText();
    }

    public void onClickEqual(View pressed) {
        Button button = getButton(pressed);
        curText = parser.parse(curText).evaluate().toString();
        updateText();
    }

    public void onClickC(View pressed) {
        Button button = getButton(pressed);
        curText = "";
        updateText();
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("curText", curText);
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        curText = savedInstanceState.getString("curText");
        updateText();
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
}
