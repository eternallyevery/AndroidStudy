package com.example.crimeintent;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.crimeintent.databinding.DialogLoginBinding;

public class LoginDialog extends DialogFragment {
    private DialogLoginBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DialogLoginBinding.inflate(inflater,container,false);
        binding.btnLogin.setOnClickListener(view -> {
            //获取用户名和密码
            String username = binding.etUsername.getText().toString();
            String password = binding.etPassword.getText().toString();
            //通过DIalogActivity的回调方法将数据传递给activity
            callBack.onDlgPositiveClick(username,password);
            //关闭对话框
            LoginDialog.this.dismiss();
        });
        binding.btnCancel.setOnClickListener(view -> {
            //通过DialogActivity的回调方法将数据传递给activity
            callBack.onDlgNegativeClick(LoginDialog.this);
            //关闭对话框
            LoginDialog.this.dismiss();
        });
        return binding.getRoot();
    }
    private LoginCallBack callBack;

    public interface LoginCallBack{
        void onDlgPositiveClick(String username,String password);
        void onDlgNegativeClick(DialogFragment dialog);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof LoginCallBack){
            this.callBack = (LoginCallBack) context;
        }else{
            throw new ClassCastException(context + "未实现LoginCallback");
        }
    }
}
