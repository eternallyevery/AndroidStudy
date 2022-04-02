package com.example.geoquiz;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class QuizViewModel extends ViewModel implements Serializable {
    private static final String TAG = "QuizViewModel";
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

    public QuizViewModel() {
        super();
        Log.d(TAG, "QuizViewModel: 创建了一个ViewModel实例");
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "onCleared: QuizViewModel实例被销毁");
    }
    // 获取当前题目的题干
    public int getCurrentQuestionResId() {
        return questions.get(currentIndex).getTextResId();
    }
    // 获取当前题目的答案
    public boolean getCurrentQuestionAnswer() {
        return questions.get(currentIndex).isAnswer();
    }
    // 移到下一题
    public void moveToNext() {
        currentIndex = (currentIndex + 1) % questions.size();
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }
    public int getCurrentIndex() {
        return currentIndex;
    }
}
