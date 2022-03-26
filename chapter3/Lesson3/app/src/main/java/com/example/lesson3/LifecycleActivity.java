package com.example.lesson3;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lesson3.databinding.ActivityMainBinding;

import java.util.Arrays;
import java.util.List;

/**
 * @author lwj
 */
public class LifecycleActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "LifecycleActivity";
    //试图绑定对象
    private ActivityMainBinding activityMainBinding;
    //创建题目集合
    private final List<Question> questions = Arrays.asList(
            new Question(R.string.question_australia,true),
            new Question(R.string.question_oceans,true),
            new Question(R.string.question_mideast,false),
            new Question(R.string.question_africa,false),
            new Question(R.string.question_americas,true),
            new Question(R.string.question_asia,true)
    );
    //创建当前题目的索引变量
    private int currentIndex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: 被调用");
        //视图绑定
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        //设置事件监听按钮
        //true和false按钮：检查回答是否正确
        activityMainBinding.trueBt.setOnClickListener(view -> {
            checkAnswer(true);
        });
        activityMainBinding.falseBt.setOnClickListener(view -> {
            checkAnswer(false);
        });
        //Next按钮:跳转到下一道题
        activityMainBinding.nextBt.setOnClickListener(view -> {
            currentIndex = (currentIndex+1)%questions.size();
            updateQuestion();
        });
        //初始化显示第一题
        updateQuestion();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: 被调用");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: 被调用");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: 被调用");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: 被调用");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: 被调用");
    }

    private void updateQuestion(){
        int resId = questions.get(currentIndex).getTextResId();
        activityMainBinding.textQuest.setText(resId);
    }
    //判断选择的答案是否正确
    private void checkAnswer(boolean userAnswer) {
        boolean answer = questions.get(currentIndex).isAnswer();
        int msgId = answer == userAnswer? R.string.correct_toast:R.string.incorrect_toast;
        Toast.makeText(this,msgId,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.true_bt:
                break;
            case R.id.false_bt:
                break;
            case R.id.next_bt:
                break;
            default:
        }
    }
}
