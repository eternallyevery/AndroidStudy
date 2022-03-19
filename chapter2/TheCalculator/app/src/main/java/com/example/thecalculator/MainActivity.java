package com.example.thecalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.thecalculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
private ActivityMainBinding activityMainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        activityMainBinding.first.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Toast.makeText(MainActivity.this,activityMainBinding.first.getText(),Toast.LENGTH_SHORT).show();
    }
}