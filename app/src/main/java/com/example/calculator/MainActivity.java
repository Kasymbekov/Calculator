package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Double d1, d2;
    private Boolean isOperationClicked;
    private String symbol;
    public static boolean isDestroyed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.result);
    }

    public void onNumberClick(View view) {
        switch (view.getId()) {
            case R.id.btn_zero:
                checkDigit(0);
                break;
            case R.id.btn_one:
                checkDigit(1);
                break;
            case R.id.btn_two:
                checkDigit(2);
                break;
            case R.id.btn_three:
                checkDigit(3);
                break;
            case R.id.btn_four:
                checkDigit(4);
                break;
            case R.id.btn_five:
                checkDigit(5);
                break;
            case R.id.btn_six:
                checkDigit(6);
                break;
            case R.id.btn_seven:
                checkDigit(7);
                break;
            case R.id.btn_eight:
                checkDigit(8);
                break;
            case R.id.btn_nine:
                checkDigit(9);
                break;
            case R.id.btn_clear:
                textView.setText("0");
                d1 = 0.0;
                d2 = 0.0;
                break;
        }
        setAlphaZero();
    }

    public void onOperationClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sum:
                symbol = "+";
                getFirstValue();
                break;
            case R.id.btn_minus:
                symbol = "-";
                getFirstValue();
                break;
            case R.id.btn_multiply:
                symbol = "x";
                getFirstValue();
                break;
            case R.id.btn_divide:
                symbol = "/";
                getFirstValue();
                break;
            case R.id.btn_percent:
                symbol = "%";
                getFirstValue();
                break;
            case R.id.btn_negative:
                if (!textView.getText().toString().equals("0")) {
                    String s = "";
                    if (!textView.getText().toString().contains("-")) {
                        s = "-" + textView.getText();
                    } else {
                        s += textView.getText().toString().substring(1);
                    }
                    textView.setText(s);
                }
                setAlphaZero();
                break;
            case R.id.btn_equal:
                performOperation(symbol);
                //Show button to move
                findViewById(R.id.btn_move).animate().alpha(1);
                break;
            case R.id.btn_dot:
                if (!textView.getText().toString().contains(".")) {
                    textView.append(".");
                }
                setAlphaZero();
                break;
        }
    }

    public void checkDigit(Integer integer) {
        if (textView.getText().toString().equals("0")) {
            textView.setText(String.valueOf(integer));
        } else if (isOperationClicked) {
            textView.setText(String.valueOf(integer));
        } else {
            textView.append(String.valueOf(integer));
        }
        isOperationClicked = false;
    }

    public void getFirstValue() {
        d1 = Double.parseDouble(textView.getText().toString());
        setVariable(d1);
        isOperationClicked = true;
        setAlphaZero();
    }

    public void performOperation(String s) {
        Double result = 0.0;
        d2 = Double.parseDouble(textView.getText().toString());

        switch (s) {
            case "+":
                result = d1 + d2;
                break;
            case "-":
                result = d1 - d2;
                break;
            case "/":
                result = d1 / d2;
                break;
            case "x":
                result = d1 * d2;
                break;
            case "%":
                result = d1 / 100;
                break;
        }

        setVariable(result);
        isOperationClicked = true;
    }

    public void setVariable(Double d) {
        if (d % 1 == 0) {
            textView.setText(String.valueOf(Math.round(d)));
        } else {
            textView.setText(d.toString());
        }

    }

    public void setAlphaZero() {
        findViewById(R.id.btn_move).animate().alpha(0);
    }

    public void OpenActivity(View view) {
        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
        String s = textView.getText().toString();
        intent.putExtra("value", s);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isDestroyed) {
            finish();
        }
    }
}