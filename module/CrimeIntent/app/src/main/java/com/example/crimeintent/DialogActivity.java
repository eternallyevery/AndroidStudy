package com.example.crimeintent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.widget.Toast;

import com.example.crimeintent.databinding.ActivityDialogBinding;


public class DialogActivity extends AppCompatActivity
implements LoginDialog.LoginCallBack{
private ActivityDialogBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDialogBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnLogin.setOnClickListener(view -> {
            LoginDialog dlg = new LoginDialog();
            dlg.show(getSupportFragmentManager(),"登录");
        });
        binding.btnExit.setOnClickListener(view -> {
            LogoutDialog dlg = new LogoutDialog();
            dlg.show(getSupportFragmentManager(),"退出");
        });

    }

    @Override
    public void onDlgPositiveClick(String username, String password) {
        Toast.makeText(this,username+","+password,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDlgNegativeClick(DialogFragment dialog) {
        Toast.makeText(this,"取消登录",Toast.LENGTH_SHORT).show();
    }
}