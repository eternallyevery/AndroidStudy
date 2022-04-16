package com.example.crimeintent;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class LogoutDialog extends AppCompatDialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new AlertDialog.Builder(requireContext())
                .setTitle("退出")
                .setMessage("确认退出？")
                .setPositiveButton("确认", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                    LogoutDialog.this.requireActivity().finish();
                }).setNegativeButton("取消", (dialogInterface, i) -> dialogInterface.dismiss())
                .create();
    }
}
