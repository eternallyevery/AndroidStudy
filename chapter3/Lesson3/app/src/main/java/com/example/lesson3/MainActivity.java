package com.example.lesson3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.example.lesson3.databinding.ActivityMainBinding;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
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
//        activityMainBinding.trueBt.setOnClickListener(view -> {
//            checkAnswer(true);
//        });
//        activityMainBinding.falseBt.setOnClickListener(view -> {
//            checkAnswer(false);
//        });
//        //Next按钮:跳转到下一道题
//        activityMainBinding.nextBt.setOnClickListener(view -> {
//            currentIndex = (currentIndex+1)%questions.size();
//            updateQuestion();
//        });
        activityMainBinding.trueBt.setOnClickListener(this);
        activityMainBinding.falseBt.setOnClickListener(this);
        activityMainBinding.nextBt.setOnClickListener(this);
        //初始化显示第一题
        updateQuestion();
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
                checkAnswer(true);
                break;
            case R.id.false_bt:
                checkAnswer(false);
                break;
            case R.id.next_bt:
                currentIndex = (currentIndex+1)%questions.size();
                updateQuestion();
                break;
            default:
        }
    }
}