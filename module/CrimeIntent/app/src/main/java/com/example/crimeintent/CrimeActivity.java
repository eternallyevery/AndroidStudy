package com.example.crimeintent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.crimeintent.databinding.ActivityCrimeBinding;

/**
 * @author lwj
 */
public class CrimeActivity extends AppCompatActivity {
private ActivityCrimeBinding binding;
final FragmentManager manager = getSupportFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCrimeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //加载CrimeFragment
        Fragment crimeFragment = CrimeFragment.newInstance("");
        manager.beginTransaction()
                .replace(R.id.fragment_container_crime,crimeFragment)
                .addToBackStack(null)
                .commit();



    }

}