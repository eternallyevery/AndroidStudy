package com.example.fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.TimePicker;

import com.example.fragment.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
private ActivityMainBinding binding;
final FragmentManager manager = getSupportFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //加载CrimeFragment
        Fragment blankFragment = new CrimeFragment();
        manager.beginTransaction()
                .add(R.id.fragment_container_crime,blankFragment)
                .addToBackStack(null)
                .commit();


    }
}