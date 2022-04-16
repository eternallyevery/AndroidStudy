package com.example.crimeintent;

import android.content.Context;
import android.os.Binder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.crimeintent.databinding.FragmentBlankBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 * @author lwj
 */
public class BlankFragment extends Fragment {
    private static final String ARG_PARAM = "param";
    private  String param;
    private String TAG = "Fragment";

    public BlankFragment() {
    }
    public static BlankFragment newInstance(String param) {
        BlankFragment fragment = new BlankFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_PARAM,param);
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments()!= null){
            param = getArguments().getString(ARG_PARAM);
        }
        Log.d(TAG, "Fragment:onCreate: ");
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(TAG, "Fragment:onAttach: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "Fragment:onResume: ");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "Fragment:onStart: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "Fragment:onStop: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Fragment:onDestroy: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "Fragment:onPause: ");
    }

    /**
     * oncreateview()完成activity的oncreate()方法的功能，也就是初始化。
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       // View view = inflater.inflate(R.layout.fragment_blank, container, false);
        FragmentBlankBinding fragmentBlankBinding = FragmentBlankBinding.inflate(inflater, container, false);
      //  Toast.makeText(requireContext(),"fragment的值:" + fragmentBlankBinding.frText.getText().toString(),Toast.LENGTH_SHORT).show();
       if(param!= null && !param.isEmpty()){
           fragmentBlankBinding.frText.setText(param);
        }
        Log.d(TAG, "Fragment:onCreateView: ");
        return fragmentBlankBinding.getRoot();
    }
}