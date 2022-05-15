package com.example.recyclerdemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String T_STUDENT_NAME = "t_student";
    private static final String T_STUDENT = "create table t_student(id integer primary key autoincrement," +
            "name varchar(255)," +
            "classmate varchar(255)," +
            "age integer)";
    private static String DROP_STUDENT = "drop table if exists t_student";
    public static String DB_NAME = "student.db";
    public static int DB_VERSION = 1;
    public DBHelper(@Nullable Context context) {
        super(context, DBHelper.DB_NAME, null, DBHelper.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DBHelper.T_STUDENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DBHelper.DROP_STUDENT);
        onCreate(sqLiteDatabase);

    }
}
