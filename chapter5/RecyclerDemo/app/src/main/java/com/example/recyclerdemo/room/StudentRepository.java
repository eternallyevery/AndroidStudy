package com.example.recyclerdemo.room;

import android.app.Application;
import android.text.TextUtils;

import com.example.recyclerdemo.room.StudentEntity;
import com.example.recyclerdemo.room.StudentRoomDao;
import com.example.recyclerdemo.room.StudentRoomDateBase;

import java.util.List;

public class StudentRepository {
    private StudentRoomDao studentRoomDao;

    /**
     * 初始化StudentRoomDao对象
     * @param application application
     */
    public StudentRepository(Application application){
        StudentRoomDateBase db = StudentRoomDateBase.getInstance(application);
        studentRoomDao = db.getStudentRoomDao();
    }

    /**
     * 在子线程中执行添加操作
     * @param student student
     */
    public void insert(final StudentEntity student){
        StudentRoomDateBase.writeExecutor.execute(new Runnable() {
            @Override
            public void run() {
                studentRoomDao.insert(student);
            }
        });
    }

    /**
     * 在子线程执行更新操作
     * @param student student
     */
    public void update(StudentEntity student){
        StudentRoomDateBase.writeExecutor.execute(new Runnable() {
            @Override
            public void run() {
                studentRoomDao.update(student);
            }
        });
    }

    /**
     * 在子线程中执行删除操作
     * @param student student
     */
    public void delete(StudentEntity student){
        StudentRoomDateBase.writeExecutor.execute(new Runnable() {
            @Override
            public void run() {
                studentRoomDao.delete(student);
            }
        });
    }

    /**
     * 查询所有
     * @return List<StudentEntity>
     */
    public List<StudentEntity> getAll(){
        return studentRoomDao.getAll();
    }

    public List<StudentEntity> getAll(String kw){
        if (TextUtils.isEmpty(kw)){
            return studentRoomDao.getAll();
        }
        return studentRoomDao.getAll(kw);
    }


}
