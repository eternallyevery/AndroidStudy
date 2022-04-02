package com.example.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.geoquiz.databinding.ActivityCheatBinding;

import java.io.Serializable;

public class CheatActivity extends AppCompatActivity {
    private ActivityCheatBinding cheatBinding;
    String trf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cheatBinding = ActivityCheatBinding.inflate(getLayoutInflater());
        setContentView(cheatBinding.getRoot());

        Intent intent = getIntent();
        //获取传输的数据
        Bundle answer = intent.getBundleExtra("answer");
        //判断是否为空
        if (answer != null){
            int quizId = answer.getInt("quizid");
            trf = String.valueOf(answer.getBoolean("TRF"));
            cheatBinding.questionTextView.setText(quizId);
            cheatBinding.answerTextView.setText(trf);
        }else{
            Toast.makeText(this,"wrong",Toast.LENGTH_SHORT).show();
        }

        //跳转
        cheatBinding.showAnswer.setOnClickListener(view -> {
            Intent intentRe = new Intent();
            intentRe.putExtra("returnAnswer",trf);
            setResult(RESULT_OK,intentRe);
            finish();
        });



    }
}