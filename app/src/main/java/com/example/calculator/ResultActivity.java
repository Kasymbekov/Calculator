package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        String text = getIntent().getStringExtra("value");
        TextView textView = findViewById(R.id.answer);
        textView.setText(text);

        findViewById(R.id.destroy_all).setOnClickListener(view -> {
            MainActivity.isDestroyed = true;
            finish();
        });
    }
}