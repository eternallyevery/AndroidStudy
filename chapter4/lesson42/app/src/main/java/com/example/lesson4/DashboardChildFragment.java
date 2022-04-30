package com.example.lesson4;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lesson4.databinding.FragmentDashboardChildBinding;

public class DashboardChildFragment extends Fragment {
private FragmentDashboardChildBinding binding;

    private static final String ARG_POSITION = "arg_position";
    public static DashboardChildFragment newInstance(int position) {
        DashboardChildFragment fragment = new DashboardChildFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDashboardChildBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null){
            final int position = getArguments().getInt(ARG_POSITION,-1);
            binding.tvChild.setText("dashboard" + position);
        }
    }
}