package com.example.geoquiz;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.geoquiz.databinding.ActivityMainBinding;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 视图绑定
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        setContentView(R.layout.activity_main);

        // 设置按钮事件监听器
        // True和False按钮：检查回答是否正确，Toast给出提示
//        binding.trueButton.setOnClickListener(view -> {
//           checkAnswer(true);
//        });
//        binding.falseButton.setOnClickListener(view -> {
//            checkAnswer(false);
//        });
//        // Next按钮：跳转到下一道题
//        binding.nextButton.setOnClickListener(view -> {
//            // 更新currentIndex，如果到最后一题就回到第一题
//            currentIndex = (currentIndex + 1) % questions.size();
//            updateQuestion();
//        });
        binding.trueButton.setOnClickListener(this);
        binding.falseButton.setOnClickListener(this);
        binding.nextButton.setOnClickListener(this);
        // 初始化显示第1题
        updateQuestion();
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
                currentIndex = (currentIndex + 1) % questions.size();
                updateQuestion();
                break;
        }
    }
}

// 1. 创建题目集合
//    private final List<Question> questionBank = Arrays.asList(
//            new Question(R.string.question_australia, true),
//            new Question(R.string.question_africa, false),
//            new Question(R.string.question_asia, true),
//            new Question(R.string.question_americas, true),
//            new Question(R.string.question_oceans, true),
//            new Question(R.string.question_mideast, false)
//    );
// 2. 当前显示的题目索引
//    private int currentIndex = 0;

//        binding.trueButton.setOnClickListener(view -> checkAnswer(true));
//        binding.falseButton.setOnClickListener(view -> checkAnswer(false));
//        binding.nextButton.setOnClickListener(view -> {
//            currentIndex = (currentIndex + 1) % questionBank.size();
//            updateQuestion();
//        });
//        updateQuestion();

// 更新题目
//    private void updateQuestion() {
//        final int textResId = questionBank.get(currentIndex).textResId;
//        binding.questionTextView.setText(textResId);
//    }

// 检查回答是否正确
//    private void checkAnswer(boolean userAnswer) {
//        final boolean correctAnswer = questionBank.get(currentIndex).answer;
//        int textResId = userAnswer == correctAnswer ? R.string.correct_toast : R.string.incorrect_toast;
//        Toast.makeText(this, textResId, Toast.LENGTH_SHORT).show();
//    }