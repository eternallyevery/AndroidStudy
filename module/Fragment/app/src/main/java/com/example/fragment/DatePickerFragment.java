package com.example.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class DatePickerFragment extends AppCompatDialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new AlertDialog.Builder(requireContext())
                .setTitle("日历")
                .setMessage("打开日历？")
                .setPositiveButton("确认", (dialogInterface, i) -> {
                    DatePickDialog datePickDialog = new DatePickDialog();
                    datePickDialog.show(getActivity().getSupportFragmentManager(), "日历");
                    dialogInterface.dismiss();
                }).setNegativeButton("取消", (dialogInterface, i) -> dialogInterface.dismiss())
                .create();

    }
}