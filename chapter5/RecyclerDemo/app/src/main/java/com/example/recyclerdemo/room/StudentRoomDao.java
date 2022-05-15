package com.example.recyclerdemo.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * @author lwj
 */
@Dao
public interface StudentRoomDao {
    /**
     * 查询所有
     * @return
     */
    @Query("select * from stu_room")
    List<StudentEntity> getAll();


    /**
     * 根据条件进行查询，使用||拼接字符串
     * @param kw 关键字
     * @return 返回studentEntity列表
     */
    @Query("select * from stu_room where student_name like '%'||:kw||'%' or classmate like '%'||:kw||'%'")
    List<StudentEntity> getAll(String kw);

    /**
     * 添加
     * @param student 添加student
     */
    @Insert
    void insert(StudentEntity student);

    /**
     * 根据student的id更新所有列的值
     * @param student 修改student
     */
    @Update
    void update(StudentEntity student);

    /**
     * 更新年龄
     * @param id 根据id
     * @param age 修改年龄
     */
    @Query("update stu_room set age = :age where id = :id")
    void update(int id, int age);

    /**
     * 根据id进行删除
     * @param id id
     */
    @Query("delete from stu_room where id = :id")
    void delete(int id);

    /**
     *  删除
     * @param student student
     */
    @Delete
    void delete(StudentEntity student);

}
