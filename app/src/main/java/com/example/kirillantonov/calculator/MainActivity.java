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
    private String curText = "";
    private Long lastResalt = 0L;
    private Long curResalt = 0L;
    private boolean allCleare = true;
    private enum Operation {Plus, Minus, Div, Mult};
    private String lastOperation = "+";
    private boolean exceptionState = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = (TextView) findViewById(R.id.textView);
        goToDefaultState();
        updateText();
    }

    private void updateText() {
        view.setText(curText);
    }

    private Button getButton(View pressed) {
        return (Button) pressed;
    }

    public void onClickNumber(View pressed) {
        Button button = getButton(pressed);
        String s = button.getText().toString();
        curText = curText.concat(s);
        if (s.equals("00")) curResalt *= 10;
        curResalt = curResalt * 10 + Long.parseLong(s);
        allCleare = false;
        checkOverflow (curResalt);
        updateText();
    }

    private boolean checkOverflow(Long k) {
        if (k > (Integer.MAX_VALUE) || k < (Integer.MIN_VALUE)) {
            curText = "Overflow";
            return true;
        }
        return false;
    }

    private void goToDefaultState() {
        curText = "";
        lastResalt = 0L;
        curResalt = 0L;
        allCleare = true;
        lastOperation = "+";
    }

    public void onClickC(View pressed) {
        Button button = getButton(pressed);
        goToDefaultState();
        updateText();
    }

    public void onCLickBinaryOperation(View pressed) {
        Button button = getButton(pressed);
        if (!evaluate()) return;
        lastOperation = button.getText().toString();
        curText = curText.concat(" ").concat(button.getText().toString()).concat(" ");
        updateText();
    }

    private boolean evaluate() {
        if (allCleare) return false;
        Long ans;
        switch (lastOperation) {
            case "+":
                ans = lastResalt + curResalt;
                break;
            case "-":
                ans = lastResalt - curResalt;
                break;
            case "x":
                ans = lastResalt * curResalt;
                break;
            case "/":
                if (curResalt == 0) {
                    curText = "Devising by zero";
                    return false;
                }
                ans = lastResalt / curResalt;
                break;
            default:
                ans = 0L;
                break;
        }
        if (checkOverflow(ans)) return false;
        lastResalt = ans;
        curResalt = 0L;
        curText = lastResalt.toString();
        return true;
    }

    public void onCLickEqual(View pressed) {
        Button button = getButton(pressed);
        evaluate();
        lastOperation = "+";
        updateText();
    }

    public void onCLickDel(View pressed) {
        if (!curText.isEmpty()) {
            curText = curText.substring(0, curText.length() - 1);
        }
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
