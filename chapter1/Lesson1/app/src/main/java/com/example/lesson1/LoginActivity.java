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

import com.example.lesson1.databinding.ActiityLoginBinding;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lwj
 */
public class LoginActivity extends AppCompatActivity {
    private ActiityLoginBinding loginBinding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = ActiityLoginBinding.inflate(getLayoutInflater());
        setContentView(loginBinding.getRoot());
        //点击事件
        onClick();
    }

    /**
     * 点击事件
     */
    private void onClick() {
        loginBinding.loginBt.setOnClickListener(view -> {
            List<String> strings = new ArrayList<String>();
            for (int i = 0; i <loginBinding.love.getChildCount(); i++) {
                CheckBox checkBox = (CheckBox) loginBinding.love.getChildAt(i);
                if (checkBox.isChecked()){
                    strings.add(checkBox.getText().toString());
                }
            }
            String s;
            int checkedRadioButtonId = loginBinding.gender.getCheckedRadioButtonId();
            TextView viewById = findViewById(checkedRadioButtonId);
            s = viewById.getText().toString();
            String userN = loginBinding.username.getText().toString();
            String phoneEt = this.loginBinding.phoneEt.getText().toString();
            Toast.makeText(this,"姓名:"+userN+" 手机号:"+phoneEt + " 性别:"+ s + " 我喜欢的课程:" + strings.toString().substring(1,strings.toString().length()-1) ,Toast.LENGTH_LONG).show();
        });
    }
}
