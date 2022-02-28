package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button trueButton = findViewById(R.id.ture_button);
        final Button falseButton = findViewById(R.id.false_button);
        final Button nextButton = findViewById(R.id.next_button);
        trueButton.setOnClickListener(view -> {
            Toast.makeText(this,R.string.correct_text, Toast.LENGTH_SHORT).show();
        });
        falseButton.setOnClickListener(view -> {
            Toast.makeText(this,R.string.incorrect_text,Toast.LENGTH_SHORT).show();
        });
    }
}