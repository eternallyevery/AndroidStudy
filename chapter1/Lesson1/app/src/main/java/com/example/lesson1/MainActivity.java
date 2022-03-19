package com.example.lesson1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author lwj
 */
public class MainActivity extends AppCompatActivity {
    public static String username = "username";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("登录");
        //将头像变成圆形
        ImageView img = findViewById(R.id.img);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.icon);
        Drawable drawable = new CircleImageDrawable(bitmap);
        img.setImageDrawable(drawable);

        //获取登录id
        View login = findViewById(R.id.enter_bt);
        //获取usernaem和password
        TextView userName = findViewById(R.id.username);
        TextView passWord = findViewById(R.id.password);
        //设置点击事件跳转到登录页面
        login.setOnClickListener(view -> {
            if ("123".equals(userName.getText().toString())&&"123".equals(passWord.getText().toString())){
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.putExtra(username,userName.getText().toString());
                startActivity(intent);
            }else{
                Toast.makeText(this,"输入错误",Toast.LENGTH_SHORT).show();
            }

        });
    }
}