package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.bmicalculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
private ActivityMainBinding activityMainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        //点击计算事件
        activityMainBinding.enter.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //获取身高体重的id，进行计算bmi值
        String weight = activityMainBinding.weight.getText().toString();
        String height = activityMainBinding.height.getText().toString();
        double wFloat = Double.parseDouble(weight);
        double hFloat = Double.parseDouble(height);
        double h = Math.pow(hFloat,2);
        String sBmi = String.valueOf(Math.round(wFloat/h));
        activityMainBinding.BMI.setText(sBmi);
        // 获取性别
        int checkedRadioButtonId = activityMainBinding.gender.getCheckedRadioButtonId();
        //根据性别打印结果
        if (checkedRadioButtonId == activityMainBinding.male.getId()){
            int i = Integer.parseInt(sBmi);
            if (i<20){
                activityMainBinding.diagnosis.setText("体重过轻");
            }else if (i<25){
                activityMainBinding.diagnosis.setText("体重正常");
            }else if (i<27){
                activityMainBinding.diagnosis.setText("体重超重");
            }else if (i<30){
                activityMainBinding.diagnosis.setText("轻度肥胖");
            }else if (i<35){
                activityMainBinding.diagnosis.setText("中度肥胖");
            }else{
                activityMainBinding.diagnosis.setText("重度肥胖");
            }
        }else{
            int i = Integer.parseInt(sBmi);
            if (i<19){
                activityMainBinding.diagnosis.setText("体重过轻");
            }else if (i<24){
                activityMainBinding.diagnosis.setText("体重正常");
            }else if (i<26){
                activityMainBinding.diagnosis.setText("体重超重");
            }else if (i<29){
                activityMainBinding.diagnosis.setText("轻度肥胖");
            }else if (i<34){
                activityMainBinding.diagnosis.setText("中度肥胖");
            }else{
                activityMainBinding.diagnosis.setText("重度肥胖");
            }
        }
    }
}