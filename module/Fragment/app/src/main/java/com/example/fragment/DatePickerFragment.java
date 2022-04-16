package com.example.fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.fragment.databinding.FragmentDatePickerBinding;

import java.util.Calendar;
import java.util.Date;


public class DatePickerFragment extends DialogFragment {
    public static String TAG = "TAG";
    private static Date date = null;
    private FragmentDatePickerBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDatePickerBinding.inflate(inflater, container, false);
        DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                date = new Date(i-1900,i1-1,i2);
                Bundle bundle = new Bundle();
                bundle.putString("日期",date.toString());
                getParentFragmentManager().setFragmentResult("Date",bundle);
            }
        }, Calendar.YEAR,Calendar.MONTH,Calendar.DAY_OF_MONTH);
        dialog.show();
        return binding.getRoot();
    }


}