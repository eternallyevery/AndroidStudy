package com.example.lesson1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lwj
 */
public class LoginActivity extends AppCompatActivity {
    private Button loginBt;
    private TextView username;
    private TextView phoneEt;
    private RadioButton maleRb;
    private RadioButton femaleRb;
    private CheckBox javaCb;
    private CheckBox androidCb;
    private CheckBox mathCb;
    private CheckBox enCb;
    private RadioGroup love;
    private RadioGroup gender;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actiity_login);
        setTitle("注册");
        //获取所有的id
        initView();
        //点击事件
        onClick();
    }

    /**
     * 点击事件
     */
    private void onClick() {
        loginBt.setOnClickListener(view -> {
            List<String> strings = new ArrayList<String>();
            for (int i = 0; i <love.getChildCount(); i++) {
                CheckBox checkBox = (CheckBox) love.getChildAt(i);
                if (checkBox.isChecked()){
                    strings.add(checkBox.getText().toString());
                }
            }
            String s;
            int checkedRadioButtonId = gender.getCheckedRadioButtonId();
            TextView viewById = findViewById(checkedRadioButtonId);
            s = viewById.getText().toString();
            String userN = username.getText().toString();
            String phoneEt = this.phoneEt.getText().toString();
            Toast.makeText(this,"姓名:"+userN+" 手机号:"+phoneEt + " 性别:"+ s + " 我喜欢的课程:" + strings.toString().substring(1,strings.toString().length()-1) ,Toast.LENGTH_LONG).show();
        });
    }

    /**
     * 初始化获得所有的id
     */
    private void initView() {
         loginBt = findViewById(R.id.login_bt);
         username = findViewById(R.id.username);
         phoneEt = findViewById(R.id.phone_et);
         gender = findViewById(R.id.gender);
         maleRb = findViewById(R.id.male_rb);
         femaleRb = findViewById(R.id.female_rb);
         javaCb = findViewById(R.id.java_cb);
         androidCb = findViewById(R.id.android_cb);
         mathCb = findViewById(R.id.math_cb);
         enCb = findViewById(R.id.en_cb);
         love = findViewById(R.id.love);

    }
}
