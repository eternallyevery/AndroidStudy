package com.example.lesson4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.lesson4.databinding.ActivitySecondBinding;

import java.io.Serializable;
import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
private ActivitySecondBinding secondBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        secondBinding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(secondBinding.getRoot());

        Intent intent = getIntent();
        String data = intent.getStringExtra("data");
        if (data != null){
            secondBinding.tvData.setText(data);
        }
        ArrayList<Integer> list = intent.getIntegerArrayListExtra("list");
        if (list != null){
            secondBinding.tvData.setText("获取列表 "+list.toString());
        }
        User user = (User) intent.getSerializableExtra("object");
        if (user != null){
            secondBinding.tvData.setText(user.toString());
        }
        Bundle bundle = intent.getExtras();
        if (bundle!=null){
            String name = bundle.getString("name");
            int id = bundle.getInt("id");
            if (name!= null){
                secondBinding.tvData.setText(name+","+id+","+bundle.getSerializable("user").toString());
            }
        }
        secondBinding.btnBack.setOnClickListener(view -> {
            final Intent intentRe = new Intent();
            intentRe.putExtra("data","返回给调用的activity");
            setResult(RESULT_OK,intentRe);
            finish();
        });




    }
}