package com.example.geoquiz;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.geoquiz.databinding.ActivityMainBinding;

import java.util.Arrays;
import java.util.List;

public class LifecycleActivity extends AppCompatActivity {
    // 视图绑定对象
    private ActivityMainBinding binding;

    // 创建题目集合
    private List<Question> questions = Arrays.asList(
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true)
    );
    // 创建当前题目的索引的变量
    private int currentIndex = 0;

    private static final String TAG = "LifecycleActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: 被调用");
        // 视图绑定
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        setContentView(R.layout.activity_main);

        // 设置按钮事件监听器
        // True和False按钮：检查回答是否正确，Toast给出提示
        binding.trueButton.setOnClickListener(view -> {
           checkAnswer(true);
        });
        binding.falseButton.setOnClickListener(view -> {
            checkAnswer(false);
        });


        // Next按钮：跳转到下一道题
        binding.nextButton.setOnClickListener(view -> {
            // 更新currentIndex，如果到最后一题就回到第一题
            currentIndex = (currentIndex + 1) % questions.size();
            updateQuestion();
        });
        // 初始化显示第1题
        updateQuestion();
    }




    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: 被调用");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: 被调用");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: 被调用");
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

    private void updateQuestion() {
        int resId = questions.get(currentIndex).getTextResId();
        binding.questionTextView.setText(resId);
    }
    // 判断选择的答案是否正确
    private void checkAnswer(boolean userAnswer) {
        final boolean correntAnswer = questions.get(currentIndex).isAnswer();
        int msgId = correntAnswer == userAnswer ? R.string.correct_toast : R.string.incorrect_toast;
        Toast.makeText(this, msgId, Toast.LENGTH_SHORT).show();
    }
}
