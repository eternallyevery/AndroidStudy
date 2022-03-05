package com.example.lesson1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author lwj
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText editText = findViewById(R.id.username);
        final EditText editText1 = findViewById( R.id.password);
        final Button button = findViewById(R.id.enter_bt);
        final TextView textView = findViewById(R.id.registered_text);
        button.setOnClickListener(view -> {
            String username= editText.getText().toString();
            String password = editText1.getText().toString();
            if ("123".equals(username)){
                if ("123".equals(password)) {
                   Intent intent = new Intent(MainActivity.this,BirthdayActivity.class);
                   startActivity(intent);
                }else{
                    Toast.makeText(this,"登录失败",Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this,"登录失败",Toast.LENGTH_SHORT).show();
            }
        });
    }
}