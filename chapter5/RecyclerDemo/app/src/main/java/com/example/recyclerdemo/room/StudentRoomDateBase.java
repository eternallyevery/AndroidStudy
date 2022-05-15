package com.example.recyclerdemo.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {StudentEntity.class}, version = 1, exportSchema = false)
public abstract class StudentRoomDateBase extends RoomDatabase {
    public static String DB_NAME = "stu_info";
    private static volatile StudentRoomDateBase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    //获取Dao的抽象方法
    public abstract StudentRoomDao getStudentRoomDao();
    //数据库写操作的线程池
    /**
     *
     */
    public static final ExecutorService writeExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    //单例模式
    public static StudentRoomDateBase getInstance(final Context context){
        if (INSTANCE == null){
            synchronized (StudentRoomDateBase.class){
                if (INSTANCE == null) {
                    synchronized (StudentRoomDateBase.class) {
                        if (INSTANCE == null) {
                            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    StudentRoomDateBase.class, StudentRoomDateBase.DB_NAME)
                                    .allowMainThreadQueries()
                                    .build();
                        }
                    }
                }
            }
        }
        return INSTANCE;
    }


    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }
}
