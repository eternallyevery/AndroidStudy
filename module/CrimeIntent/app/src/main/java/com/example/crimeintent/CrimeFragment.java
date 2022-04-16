package com.example.crimeintent;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.crimeintent.databinding.FragmentCrimeBinding;

import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CrimeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CrimeFragment extends Fragment {
private FragmentCrimeBinding binding;
    public CrimeFragment() {
    }
    public static CrimeFragment newInstance(String param1){
        CrimeFragment fragment = new CrimeFragment();
        Bundle args = new Bundle();
        args.putString("TAG", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCrimeBinding.inflate(inflater, container, false);
        FragmentManager fragmentManager = getParentFragmentManager();
        //获取当前时间
        Date date = new Date();
        //将当前时间设置到页面上
        binding.btnTime.setText(date.toString());
        //按钮点击事件
        binding.btnTime.setOnClickListener(view -> {
            DatePickerFragment dpf = new DatePickerFragment();
            dpf.show(getActivity().getSupportFragmentManager(), "日历");
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