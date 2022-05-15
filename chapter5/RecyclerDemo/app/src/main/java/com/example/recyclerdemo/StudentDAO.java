package com.example.recyclerdemo;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

//定义student表的crud操作代码
public class StudentDAO {
    private DBHelper dbHelper;

    public StudentDAO(Context context){
        this.dbHelper = new DBHelper(context);
    }

    public int insert(Student student){
        //1.组装数据
        ContentValues values = new ContentValues();
        values.put("name", student.getName());
        values.put("classmate", student.getClassmate());
        values.put("age", student.getAge());

        //2.获取数据库对象
        final SQLiteDatabase db = dbHelper.getWritableDatabase();

        //3.插入
        long id = db.insert(DBHelper.T_STUDENT_NAME, null, values);

        //4.关闭
        db.close();

        return (int)id;
    }

    /**
     * 查询所有
     * @return
     */
    @SuppressLint("Range")
    public List<Student> getAllStudent(){
        //1.创建返回类型对象
        List<Student> students = new ArrayList<>();
        //2.获得数据库对象
        SQLiteDatabase readableDatabase = dbHelper.getReadableDatabase();
        //3.执行查询语句
        final Cursor query = readableDatabase.query(DBHelper.T_STUDENT_NAME, null, null, null, null, null, null);

        //4.解析表数据
        while(query.moveToNext()){
             Student student = new Student(query.getString(query.getColumnIndex("name")),
                    query.getString(query.getColumnIndex("classmate")),
                    query.getInt(query.getColumnIndex("age")));
            student.setId(query.getInt(query.getColumnIndex("id")));
            students.add(student);
        }
        //5.关闭资源
        readableDatabase.close();
        query.close();
        //6.返回结果
        return students;
    }

    /**
     * 查询单个
     * @param kw
     * @return
     */
    @SuppressLint("Range")
    public List<Student> getStudent(String kw){
        //1.创建返回类型对象
        List<Student> students = new ArrayList<>();
        //2.获得数据库对象
        SQLiteDatabase readableDatabase = dbHelper.getReadableDatabase();
        //3.执行查询语句
        String condition = "name like ? or classmate like ? or age like ?";
        String[] conditionArgs = new String[]{"%"+kw+"%","%"+kw+"%","%"+kw+"%"};
        final Cursor query = readableDatabase.query(DBHelper.T_STUDENT_NAME, null, condition, conditionArgs, null, null, null);

        //4.解析表数据
        while(query.moveToNext()){
            Student student = new Student(query.getString(query.getColumnIndex("name")),
                    query.getString(query.getColumnIndex("classmate")),
                    query.getInt(query.getColumnIndex("age")));
            student.setId(query.getInt(query.getColumnIndex("id")));
            students.add(student);
        }
        //5.关闭资源
        readableDatabase.close();
        //6.返回结果
        return  students;
    }

    public void update(Student student){
        //1.组装数据
        ContentValues values = new ContentValues();

        values.put("name", student.getName());
        values.put("classmate", student.getClassmate());
        values.put("age", student.getAge());

        //2.获取数据库对象
        final SQLiteDatabase db = dbHelper.getWritableDatabase();

        //3.插入
        db.update(DBHelper.T_STUDENT_NAME,values,"id = ?",new String[]{String.valueOf(student.getId())});

        //4.关闭
        db.close();
    }
    public void delete(Student student){
        //2.获取数据库对象
        final SQLiteDatabase db = dbHelper.getWritableDatabase();

        //3.插入
        db.delete(DBHelper.T_STUDENT_NAME,"id = ?",new String[]{String.valueOf(student.getId())});

        //4.关闭
        db.close();
    }
}
