package com.example.crimeintent;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.crimeintent.databinding.FragmentTitleBinding;

import java.text.Bidi;
import java.util.concurrent.CancellationException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TitleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TitleFragment extends Fragment {
    private FragmentTitleBinding binding;
    private static final String ARG_param = "param";
    private String mparam;
    public TitleFragment() {

    }
    public static TitleFragment newInstance(String param) {
        TitleFragment fragment = new TitleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_param, param);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mparam = getArguments().getString(ARG_param);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTitleBinding.inflate(inflater,container,false);
        if (mparam != null){
            binding.textTitle.setText(mparam);
        }
        getParentFragmentManager().setFragmentResultListener("key", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String value = result.getString("title");
                binding.textTitle.setText(value);
            }
        });

        return binding.getRoot();
    }
}