package com.example.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.fragment.databinding.FragmentCrimeBinding;

import java.util.Calendar;
import java.util.Date;

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
            DatePickerDialog dialog=new DatePickerDialog(getContext(), (datePicker, i4, i11, i21) -> {
                //设置时间
                Date date1 = new Date(i4 -1900, i11 -1, i21);
                //使用bundle加载数据
                Bundle bundle = new Bundle();
                bundle.putString("日期", date1.toString());
                //进行数据的传输
                FragmentManager manager = getParentFragmentManager();
                manager.setFragmentResult("Date",bundle);
            },i1,i2,i3);
            dialog.show();
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
        return binding.getRoot();
    }
}