package com.example.crimeintent;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import com.example.crimeintent.databinding.FragmentDatePickerBinding;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;


public class DatePickerFragment extends DialogFragment  {
private FragmentDatePickerBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDatePickerBinding.inflate(inflater, container, false);
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
                DatePickerFragment.this.dismiss();
            }
        });
        return binding.getRoot();
    }


}