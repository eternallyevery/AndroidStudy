package com.example.recyclerdemo.room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SpinnerAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.recyclerdemo.Student;
import com.example.recyclerdemo.StudentDAO;
import com.example.recyclerdemo.databinding.ActivityStudentUpdateBinding;

public class StudentRoomUpdateActivity extends AppCompatActivity {
    private ActivityStudentUpdateBinding binding;
    /**
     * 更新/添加的标志位
     */
    private StudentEntity currentStudent;
    private boolean isUpdate = false;
    private StudentRepository studentRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudentUpdateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        studentRepository = new StudentRepository(getApplication());

        //获取数据
        final Intent intent = getIntent();
        final Bundle bundle = intent.getExtras();
        if (bundle != null){
           currentStudent = (StudentEntity) bundle.get("student");
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
                final StudentEntity student = new StudentEntity(
                        binding.etName.getText().toString(),
                        binding.spClassmate.getSelectedItem().toString(),
                        Integer.parseInt(binding.etAge.getText().toString()));

                if (isUpdate){
                    //更新到数据库
                    student.setId(currentStudent.getId());
                    studentRepository.update(student);
                }else{
                    //插入到数据库
                    studentRepository.insert(student);
                }
                final Intent intent1 = new Intent();
                final Bundle bundle1 = new Bundle();
                bundle1.putSerializable("student", student);
                intent1.putExtras(bundle1);
                intent1.putExtra("flag", isUpdate);
                setResult(RESULT_OK, intent1);
                finish();
            }
        });
    }
}