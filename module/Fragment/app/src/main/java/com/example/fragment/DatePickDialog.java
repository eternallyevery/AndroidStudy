package com.example.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.example.fragment.databinding.DatePickDialogBinding;

import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DatePickDialog#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DatePickDialog extends DialogFragment {
private DatePickDialogBinding binding;
    public DatePickDialog() {
    }


    public static DatePickDialog newInstance(String param1) {
        DatePickDialog fragment = new DatePickDialog();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
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
        binding = DatePickDialogBinding.inflate(inflater, container, false);
        //设置日历的变更时间
        binding.calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                //设置时间
                Date date = new Date(i-1900,i1-1,i2);
                //使用bundle加载数据
                Bundle bundle = new Bundle();
                bundle.putString("日期", date.toString());
                //进行数据的传输
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.setFragmentResult("Date",bundle);
                DatePickDialog.this.dismiss();
            }
        });
        return binding.getRoot();
    }
}