package com.example.fragment;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.fragment.databinding.ActivityListBinding;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListActivity extends AppCompatActivity {
private ActivityListBinding binding;
private List<BadHabits> badHabits;
private ListAdapter adapter;
private int currentPos;

    private ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    changeDate(result);
/*
                Toast.makeText(MainActivity.this,"返回结果",Toast.LENGTH_SHORT).show();
*/
                }
            });

    private void changeDate(ActivityResult result){
        if (result != null && result.getResultCode() == RESULT_OK && result.getData() != null){
            final Intent intent = result.getData();
            boolean isUpdate = intent.getBooleanExtra("flag", false);
            BadHabits badHabit = (BadHabits) intent.getExtras().get("badHabit");
            if (isUpdate){
                //更新：修改选中位置的student数据，并更新列表
                badHabits.set(currentPos, badHabit);
                adapter.notifyItemChanged(currentPos);
            }else{
                //添加：在列表最后添加数据，并更新最后一行列表数据
                badHabits.add(badHabit);
                adapter.notifyItemChanged(badHabits.size() - 1);
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //initDate() 初始化数据
        initData();
        //初始化视图
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter = new ListAdapter(badHabits);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取选中的数据，并更新选中位置的背景色
                ListAdapter.ViewHolder viewHolder = (ListAdapter.ViewHolder) view.getTag();
                currentPos = viewHolder.getAdapterPosition();
                adapter.setCurrentIndex(currentPos);
                //将选中位置的数据传递给Student Update Activity
                final Intent intent = new Intent(ListActivity.this,MainActivity.class);
                final  Bundle bundle = new Bundle();
                bundle.putSerializable("badHabits",badHabits.get(currentPos));
                intent.putExtras(bundle);
                launcher.launch(intent);
            }
        });
        binding.recyclerView.setAdapter(adapter);


    }

    private void initData() {
        badHabits = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            BadHabits bad = new BadHabits("Crime #" + i, new Date().toString());
            if (i % 2 == 0){
                bad.setImageId(R.drawable.ic_baseline_my_location_24);
            }
            badHabits.add(bad);
        }
    }
}