package com.example.thecalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.example.thecalculator.databinding.ActivityMainBinding;
import com.googlecode.aviator.AviatorEvaluator;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
private ActivityMainBinding activityMainBinding;
    String map = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        activityMainBinding.one.setOnClickListener(this);
        activityMainBinding.two.setOnClickListener(this);
        activityMainBinding.three.setOnClickListener(this);
        activityMainBinding.four.setOnClickListener(this);
        activityMainBinding.five.setOnClickListener(this);
        activityMainBinding.six.setOnClickListener(this);
        activityMainBinding.seven.setOnClickListener(this);
        activityMainBinding.eight.setOnClickListener(this);
        activityMainBinding.nine.setOnClickListener(this);
        activityMainBinding.jia.setOnClickListener(this);
        activityMainBinding.jian.setOnClickListener(this);
        activityMainBinding.chen.setOnClickListener(this);
        activityMainBinding.chu.setOnClickListener(this);
        activityMainBinding.dengyu.setOnClickListener(this);
        activityMainBinding.dian.setOnClickListener(this);
        activityMainBinding.chuyu.setOnClickListener(this);
        activityMainBinding.CE.setOnClickListener(this);
        activityMainBinding.C.setOnClickListener(this);
        activityMainBinding.tuige.setOnClickListener(this);
        activityMainBinding.zero.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == activityMainBinding.one.getId()){
            map += activityMainBinding.one.getText().toString();
            activityMainBinding.text.setText(map);
        }else if(v.getId() == activityMainBinding.two.getId()){
            map += activityMainBinding.two.getText().toString();
            activityMainBinding.text.setText(map);
        }else if(v.getId() == activityMainBinding.three.getId()){
            map += activityMainBinding.three.getText().toString();
            activityMainBinding.text.setText(map);
        }else if(v.getId() == activityMainBinding.four.getId()){
            map += activityMainBinding.four.getText().toString();
            activityMainBinding.text.setText(map);
        }else if(v.getId() == activityMainBinding.five.getId()){
            map += activityMainBinding.five.getText().toString();
            activityMainBinding.text.setText(map);
        }else if(v.getId() == activityMainBinding.six.getId()){
            map += activityMainBinding.six.getText().toString();
            activityMainBinding.text.setText(map);
        }else if(v.getId() == activityMainBinding.seven.getId()){
            map += activityMainBinding.seven.getText().toString();
            activityMainBinding.text.setText(map);
        }else if(v.getId() == activityMainBinding.eight.getId()){
            map += activityMainBinding.eight.getText().toString();
            activityMainBinding.text.setText(map);
        }else if(v.getId() == activityMainBinding.nine.getId()){
            map += activityMainBinding.nine.getText().toString();
            activityMainBinding.text.setText(map);
        }else if(v.getId() == activityMainBinding.jia.getId()){
            map += activityMainBinding.jia.getText().toString();
            activityMainBinding.text.setText(map);
        }else if(v.getId() == activityMainBinding.jian.getId()){
            map += activityMainBinding.jian.getText().toString();
            activityMainBinding.text.setText(map);
        }else if(v.getId() == activityMainBinding.chen.getId()){
            map += activityMainBinding.chen.getText().toString();
            activityMainBinding.text.setText(map);
        }else if(v.getId() == activityMainBinding.chu.getId()){
            map += activityMainBinding.chu.getText().toString();
            activityMainBinding.text.setText(map);
        }else if(v.getId() == activityMainBinding.chuyu.getId()){
            map += activityMainBinding.chuyu.getText().toString();
            activityMainBinding.text.setText(map);
        }else if(v.getId() == activityMainBinding.CE.getId()){
            map = "";
            activityMainBinding.text.setText(map);
        }else if(v.getId() == activityMainBinding.C.getId()){
            map = "";
            activityMainBinding.text.setText(map);
        }else if(v.getId() == activityMainBinding.tuige.getId()){
            map = map.substring(0,map.length()-1);
            activityMainBinding.text.setText(map);
        }else if(v.getId() == activityMainBinding.dian.getId()){
            map += activityMainBinding.dian.getText().toString();
            activityMainBinding.text.setText(map);
        }else if(v.getId() == activityMainBinding.zero.getId()){
            map += activityMainBinding.zero.getText().toString();
            activityMainBinding.text.setText(map);
        }
        if (v.getId() == activityMainBinding.dengyu.getId()){
            if (map == ""){
                activityMainBinding.text.setText(map);
            }else if (map.charAt(map.length()-1) == '+'||map.charAt(map.length()-1) == '-'||map.charAt(map.length()-1) == '*'||map.charAt(map.length()-1) == '/'
            ||map.charAt(map.length()-1) == '%'){
                map = map.substring(0,map.length()-1);
                activityMainBinding.text.setText(map);
            }else{
                map = String.valueOf(AviatorEvaluator.execute(map));
                activityMainBinding.text.setText(map);
            }

        }

    }
}