package com.example.recyclerdemo;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;



import com.example.recyclerdemo.databinding.ActivityMainBinding;
import com.example.recyclerdemo.databinding.ToolbarLayoutBinding;
import com.example.recyclerdemo.room.StudentEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lwj
 */
public class MainActivity extends AppCompatActivity {
private ActivityMainBinding binding;
private ToolbarLayoutBinding toolbarBinding;
private List<Student> students;
private StudentAdapter adapter;
private int currentPos;
private StudentDAO studentDAO;
private ActivityResultLauncher<Intent> launcher = registerForActivityResult(
        new ActivityResultContracts.StartActivityForResult(),
        new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result != null && result.getResultCode() == RESULT_OK && result.getData() != null){
                    changeDate(result);
                }
/*
                Toast.makeText(MainActivity.this,"返回结果",Toast.LENGTH_SHORT).show();
*/
            }
        });

private void changeDate(ActivityResult result){
    students.clear();
    students.addAll(studentDAO.getAllStudent());
    adapter.notifyDataSetChanged();
}
    public List<Student> updateStudent(String kw){
        return studentDAO.getStudent(kw);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //<mearge>的binding方式
        toolbarBinding = ToolbarLayoutBinding.bind(binding.getRoot());
        setSupportActionBar(toolbarBinding.toolbar);

        //初始化数据
        initData();
        //初始化视图
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerview.addItemDecoration( new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter = new StudentAdapter(students);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取选中的数据，并更新选中位置的背景色
                StudentAdapter.ViewHolder viewHolder = (StudentAdapter.ViewHolder) view.getTag();
                currentPos = viewHolder.getAdapterPosition();
                adapter.setCurrentIndex(currentPos);
                //将选中位置的数据传递给Student Update Activity
                final Intent intent = new Intent(MainActivity.this,StudentUpdateActivity.class);
                final  Bundle bundle = new Bundle();
                bundle.putSerializable("student",students.get(currentPos));
                intent.putExtras(bundle);
                launcher.launch(intent);
            }
        });
        binding.recyclerview.setAdapter(adapter);
    }



    private void initData() {
        students = new ArrayList<>();
        studentDAO = new StudentDAO(this);
        students = studentDAO.getAllStudent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_student, menu);
        //获取searchview对象
        SearchView searchView = (SearchView)menu.findItem(R.id.item_search).getActionView();
        initSearchView(searchView);
        searchDate(searchView);
        return super.onCreateOptionsMenu(menu);
    }
    //初始化Searchview样式
    private void initSearchView(SearchView searchView){
        //设置提交按钮可见
        searchView.setSubmitButtonEnabled(true);
        //搜索框View，设置样式
        EditText searchEditView = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        searchEditView.setHint("请输入搜索内容");
        searchEditView.setTextColor(getResources().getColor(R.color.white));
        searchEditView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        //去掉搜索框默认的下划线
        LinearLayout searchPlate = searchView.findViewById(androidx.appcompat.R.id.search_plate);
        searchPlate.setBackground(null);
    }
    //监听search view的文本变化
    private void searchDate(SearchView searchView){
        //监听文本变化
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                //提交文本时调用
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                //文本搜索框发生变化时调用
                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_add:
                final Intent intent = new Intent(MainActivity.this, StudentUpdateActivity.class);
                launcher.launch(intent);
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}