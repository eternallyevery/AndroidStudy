package com.example.lesson1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lesson1.databinding.ActivityLoginBinding;
import com.google.android.material.snackbar.Snackbar;


/**
 * @author lwj
 */
public class LoginActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    private ActivityLoginBinding loginBinding;
    String selected = "";
    LinearLayout lLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_login);
        setContentView(loginBinding.getRoot());
        setTitle("登录");
        //将头像变成圆形
        ImageView img = loginBinding.img;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.icon);
        Drawable drawable = new CircleImageDrawable(bitmap);
        img.setImageDrawable(drawable);
        //获取main页面传过来的信息,设置姓名
        Intent intent = getIntent();
        String strUsername = intent.getStringExtra(MainActivity.username);
        loginBinding.username.setText(strUsername);
        //复选框的更改事件
        loginBinding.mathCb.setOnCheckedChangeListener(this);
        loginBinding.javaCb.setOnCheckedChangeListener(this);
        loginBinding.enCb.setOnCheckedChangeListener(this);
        loginBinding.androidCb.setOnCheckedChangeListener(this);
        //SnackBar用法
        lLayout = loginBinding.llayout;
        //点击事件
        onClick();
    }
    /**
     * 点击事件
     */
    private void onClick() {
        loginBinding.loginBt.setOnClickListener(view -> {
            String s;
            int checkedRadioButtonId = loginBinding.gender.getCheckedRadioButtonId();
            TextView viewById = findViewById(checkedRadioButtonId);
            s = viewById.getText().toString();
            String userN = loginBinding.username.getText().toString();
            String phoneEt = this.loginBinding.phoneEt.getText().toString();
            Toast.makeText(this,"姓名:"+userN+" 手机号:"+phoneEt + " 性别:"+ s + " 我喜欢的课程:" + selected ,Toast.LENGTH_LONG).show();
        });
    }

    /**
     * 处理checkbox的转换
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        //转换为checkbox
            CheckBox checkBox = (CheckBox) buttonView;
            if (isChecked){
                selected += checkBox.getText().toString() + ",";
            }else{
                selected = selected.replace(checkBox.getText().toString() + ",","");
            }
            Snackbar.make(lLayout,selected,Snackbar.LENGTH_LONG).show();
    }
}
