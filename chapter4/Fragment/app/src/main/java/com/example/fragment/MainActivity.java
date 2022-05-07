package com.example.fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.SpinnerAdapter;
import android.widget.TimePicker;

import com.example.fragment.databinding.ActivityMainBinding;

import java.io.Serializable;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
private ActivityMainBinding binding;
private BadHabits currentBadhabits;
final FragmentManager manager = getSupportFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //加载CrimeFragment
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            currentBadhabits = (BadHabits)bundle.getSerializable("badHabits");
        }
        //不为空就修改
        if (currentBadhabits != null){
            binding.edBadHabits.setText(currentBadhabits.getTitle());
            binding.btnTime.setText(String.valueOf(currentBadhabits.getTime()));
            if (currentBadhabits.getImageId() != 0){
                binding.checkbox.setChecked(true);
            }
        }
        //按钮点击事件
        binding.btnTime.setOnClickListener(view -> {
            Calendar calendar1 = Calendar.getInstance();
            int i = calendar1.get(Calendar.YEAR);
            int i4 = calendar1.get(Calendar.MONTH);
            int i5 = calendar1.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    String date = i+"年"+i1+"月"+i2+"日";
                    Bundle bundle = new Bundle();
                    bundle.putString("日期",date);
                    getSupportFragmentManager().setFragmentResult("Date",bundle);
                }
            }, i,i4,i5);
            dialog.show();
        });
        //监听回传
        manager.setFragmentResultListener("Date", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                //获取传输的数据
                String date = result.getString("日期");
                binding.btnTime.setText(date);
            }
        });


//        manager.beginTransaction()
//                .add(R.id.fragment_container_crime,crimeFragment)
//                .addToBackStack(null)
//                .commit();


    }
}