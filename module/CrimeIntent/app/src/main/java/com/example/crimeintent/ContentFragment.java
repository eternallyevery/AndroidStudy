package com.example.crimeintent;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.crimeintent.databinding.FragmentContentBinding;

import java.util.concurrent.CancellationException;

import javax.security.auth.callback.Callback;

public class ContentFragment extends Fragment {
    private FragmentContentBinding binding;
    public ContentFragment() {

    }

    public static ContentFragment newInstance() {

        Bundle args = new Bundle();

        ContentFragment fragment = new ContentFragment();
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
        binding = FragmentContentBinding.inflate(inflater,container,false);
        //5.通过点击事件调用回调方法，将数据传递给dataactivity
        binding.textContent.setOnClickListener(view -> {
            callback.onItemSelected(12);
            //生成bundle数据传递给
            Bundle value = new Bundle();
            value.putString("title","第1条新闻标题");
            getParentFragmentManager().setFragmentResult("key",value);
    });
        return binding.getRoot();
    }
    //1.定义一个内部回调接口
    public interface FragmentCallback{
        void onItemSelected(int position);
    }
    //3.定义一个接口对象
    private FragmentCallback callback;

    //4.获取activity的回调接口
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            this.callback = (FragmentCallback) context;
        }catch (CancellationException e){
            throw new ClassCastException(context + "必须实现FragmentCall接口");
        }
    }
}