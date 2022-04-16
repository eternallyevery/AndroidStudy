package com.example.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.example.fragment.databinding.FragmentCrimeBinding;

import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

public class CrimeFragment extends Fragment {
    private FragmentCrimeBinding binding;
    public CrimeFragment() {
    }

    public static CrimeFragment newInstance(String param1) {
        return null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCrimeBinding.inflate(inflater,container, false);
        Calendar calendar = Calendar.getInstance();
        int i1 = calendar.get(Calendar.YEAR);
        int i2 = calendar.get(Calendar.MONTH);
        int i3 = calendar.get(Calendar.DAY_OF_MONTH);
        FragmentManager fragmentManager = getParentFragmentManager();
        //获取当前时间
        Date date = new Date();
        //将当前时间设置到页面上
        binding.btnTime.setText(date.toString());
        //按钮点击事件
        binding.btnTime.setOnClickListener(view -> {
            Calendar calendar1 = Calendar.getInstance();
            int i = calendar1.get(Calendar.YEAR);
            int i4 = calendar1.get(Calendar.MONTH);
            int i5 = calendar1.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(this.getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    String date = i+"年"+i1+"月"+i2+"日";
                    Bundle bundle = new Bundle();
                    bundle.putString("日期",date);
                    getParentFragmentManager().setFragmentResult("Date",bundle);
                }
            }, i,i4,i5);
            dialog.show();
        });
        binding.btnTimer.setOnClickListener(view -> {
            Calendar calendar1 = Calendar.getInstance();
            int i = calendar1.get(Calendar.HOUR);
            int i0 = calendar1.get(Calendar.MINUTE);
            new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int i, int i1) {
                    String time = i+"时"+i1+"秒";
                    Bundle bundle = new Bundle();
                    bundle.putString("时间",time);
                    getParentFragmentManager().setFragmentResult("Time",bundle);
                }
            },i,i0,true).show();

        });

        //监听回传
        fragmentManager.setFragmentResultListener("Date", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                //获取传输的数据
                    String date = result.getString("日期");
                    binding.btnTime.setText(date);
            }
        });
        //监听回传
        fragmentManager.setFragmentResultListener("Time", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                //获取传输的数据
                String time = result.getString("时间");
                binding.btnTimer.setText(time);
            }
        });
        return binding.getRoot();
    }
}