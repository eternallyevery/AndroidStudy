package com.example.recyclerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SpinnerAdapter;

import com.example.recyclerdemo.databinding.ActivityStudentUpdateBinding;

public class StudentUpdateActivity extends AppCompatActivity {
    private ActivityStudentUpdateBinding binding;
    private Student currentStudent;
    private boolean isUpdate = false;
    private StudentDAO studentDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudentUpdateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        studentDAO = new StudentDAO(this);

        //获取数据
        final Intent intent = getIntent();
        final Bundle bundle = intent.getExtras();
        if (bundle != null){
           currentStudent = (Student) bundle.get("student");
        }
        //不为空就修改
        if (currentStudent != null){
            isUpdate = true;
            binding.etName.setText(currentStudent.getName());
            binding.etAge.setText(String.valueOf(currentStudent.getAge()));

            SpinnerAdapter adapter = binding.spClassmate.getAdapter();
            for (int i = 0; i < adapter.getCount(); i++) {
                if(adapter.getItem(i).toString().equals(currentStudent.getClassmate())){
                    binding.spClassmate.setSelection(i);
                    break;
                }
            }
        }

        binding.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Student student = new Student(binding.etName.getText().toString(),
                        binding.spClassmate.getSelectedItem().toString(),
                        Integer.parseInt(binding.etAge.getText().toString()));

                if (isUpdate){
                    //更新到数据库
                    student.setId(currentStudent.getId());
                    studentDAO.update(student);
                }else{
                    //插入到数据库
                    int id = studentDAO.insert(student);
                    student.setId(id);
                }
                final Intent intent = new Intent();
                final Bundle bundle = new Bundle();
                bundle.putSerializable("student", student);
                intent.putExtras(bundle);
                intent.putExtra("flag", isUpdate);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}