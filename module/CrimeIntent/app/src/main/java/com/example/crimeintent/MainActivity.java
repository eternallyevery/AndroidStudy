package com.example.crimeintent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.crimeintent.databinding.ActivityMainBinding;

import java.time.LocalDate;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private String TAG = "MainActivity";
private ActivityMainBinding activityMainBinding;
private FragmentManager fragmentManager;
private int index = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
    // 1.获取FragmentManager对象
        fragmentManager = getSupportFragmentManager();
//        2. 创建fragment对象
//        Fragment fragment = BlankFragment.newInstance();
//         3.判断当前是否加载fragment
//        final Fragment currentFragment = fragmentManager.findFragmentById(R.id.fragment_container);
//        if (currentFragment == null){
//            4.动态加载fragment
//            fragmentManager.beginTransaction()
//                    .add(R.id.fr_text,fragment)
//                    .commit();
//        }
        String fragmentName = String.format("Fragment %s",++index);
        Fragment fragment = BlankFragment.newInstance(fragmentName);
        fragmentManager.beginTransaction()
                .add(R.id.fragment_container,fragment)
                .addToBackStack(null)
                .commit();
        activityMainBinding.btnAdd.setOnClickListener(this);
        activityMainBinding.btnRemove.setOnClickListener(this);
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_add:
                String fragmentName = String.format("Fragment %s",++index);
                Fragment fragment = BlankFragment.newInstance(fragmentName);
                fragmentManager.beginTransaction()
                        .add(R.id.fragment_container,fragment)
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.btn_remove:
                fragment = fragmentManager.findFragmentById(R.id.fragment_container);
                if (fragment != null){
                    --index;
                    fragmentManager.beginTransaction()
                        .remove(fragment)
                            .commit();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }
}