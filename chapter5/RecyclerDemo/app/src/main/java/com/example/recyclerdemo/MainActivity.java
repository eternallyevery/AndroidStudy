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
                Toast.makeText(MainActivity.this,"θΏεη»ζ",Toast.LENGTH_SHORT).show();
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
        //<mearge>ηbindingζΉεΌ
        toolbarBinding = ToolbarLayoutBinding.bind(binding.getRoot());
        setSupportActionBar(toolbarBinding.toolbar);

        //εε§εζ°ζ?
        initData();
        //εε§εθ§εΎ
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerview.addItemDecoration( new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter = new StudentAdapter(students);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //θ·ειδΈ­ηζ°ζ?οΌεΉΆζ΄ζ°ιδΈ­δ½η½?ηθζ―θ²
                StudentAdapter.ViewHolder viewHolder = (StudentAdapter.ViewHolder) view.getTag();
                currentPos = viewHolder.getAdapterPosition();
                adapter.setCurrentIndex(currentPos);
                //ε°ιδΈ­δ½η½?ηζ°ζ?δΌ ιη»Student Update Activity
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
        //θ·εsearchviewε―Ήθ±‘
        SearchView searchView = (SearchView)menu.findItem(R.id.item_search).getActionView();
        initSearchView(searchView);
        searchDate(searchView);
        return super.onCreateOptionsMenu(menu);
    }
    //εε§εSearchviewζ ·εΌ
    private void initSearchView(SearchView searchView){
        //θ?Ύη½?ζδΊ€ζι?ε―θ§
        searchView.setSubmitButtonEnabled(true);
        //ζη΄’ζ‘ViewοΌθ?Ύη½?ζ ·εΌ
        EditText searchEditView = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        searchEditView.setHint("θ―·θΎε₯ζη΄’εε?Ή");
        searchEditView.setTextColor(getResources().getColor(R.color.white));
        searchEditView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        //ε»ζζη΄’ζ‘ι»θ?€ηδΈεηΊΏ
        LinearLayout searchPlate = searchView.findViewById(androidx.appcompat.R.id.search_plate);
        searchPlate.setBackground(null);
    }
    //ηε¬search viewηζζ¬εε
    private void searchDate(SearchView searchView){
        //ηε¬ζζ¬εε
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                //ζδΊ€ζζ¬ζΆθ°η¨
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                //ζζ¬ζη΄’ζ‘εηεεζΆθ°η¨
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