package com.example.geoquiz;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.geoquiz.databinding.ActivityMainBinding;

import java.util.Arrays;
import java.util.List;

public class QuizViewModelActivity extends AppCompatActivity implements View.OnClickListener {
    // 日志的标志
    private static final String TAG = "LifecycleActivity";
    // savedInstanceState保存的题目索引
    private static final String KEY_INDEX = "current_index";
    // 视图绑定对象
    private ActivityMainBinding binding;
    // ViewModel对象
    private QuizViewModel quizViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 视图绑定
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        quizViewModel = new ViewModelProvider(this).get(QuizViewModel.class);
        Log.d(TAG, "获取一个QuizViewModel实例: " + quizViewModel);

        // 设置按钮事件监听器
        // True和False按钮：检查回答是否正确，Toast给出提示
        binding.trueButton.setOnClickListener(this);
        binding.falseButton.setOnClickListener(this);
        binding.nextButton.setOnClickListener(this);
        binding.cheatButton.setOnClickListener(this);

        // 获取savedInstanceState存储的题目索引，初始化题目
        quizViewModel.setCurrentIndex(savedInstanceState != null ? savedInstanceState.getInt(KEY_INDEX, 0) : 0);
        updateQuestion();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.true_button:
                checkAnswer(true);
                break;
            case R.id.false_button:
                checkAnswer(false);
                break;
            case R.id.next_button:
                binding.showAnswer.setText("");
                quizViewModel.moveToNext();
                updateQuestion();
                break;
            case R.id.cheat_button:
                 final Intent intent = new Intent(QuizViewModelActivity.this,CheatActivity.class);
                 Bundle bundle = new Bundle();
                 //将当前题目和答案添加
                 bundle.putInt("quizid",quizViewModel.getCurrentQuestionResId());
                 bundle.putBoolean("TRF",quizViewModel.getCurrentQuestionAnswer());
                 intent.putExtra("answer",bundle);
                 launcher.launch(intent);
                 break;
            default:
        }
    }

    private ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK){
                        final Intent data = result.getData();
                        if (data != null){
                            binding.showAnswer.setText(data.getStringExtra("returnAnswer"));
                        }else{
                            Toast.makeText(QuizViewModelActivity.this,"Cheat is wrong",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
    );

    // 保存当前题目的索引
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: 被调用");
        outState.putInt(KEY_INDEX, quizViewModel.getCurrentIndex());
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
        int resId = quizViewModel.getCurrentQuestionResId();
        binding.questionTextView.setText(resId);
    }
    // 判断选择的答案是否正确
    private void checkAnswer(boolean userAnswer) {
        final boolean correntAnswer = quizViewModel.getCurrentQuestionAnswer();
        int msgId = correntAnswer == userAnswer ? R.string.correct_toast : R.string.incorrect_toast;
        Toast.makeText(this, msgId, Toast.LENGTH_SHORT).show();
    }
}
