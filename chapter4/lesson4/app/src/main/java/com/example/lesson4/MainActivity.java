package com.example.lesson4;


import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.lesson4.databinding.ActivityMainBinding;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
private ActivityMainBinding mainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        //设置点击事件
        mainBinding.btnData.setOnClickListener(this);
        mainBinding.btnList.setOnClickListener(this);
        mainBinding.btnObject.setOnClickListener(this);
        mainBinding.btnBundle.setOnClickListener(this);
        mainBinding.btnReturn.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        final Intent intent = new Intent(MainActivity.this,SecondActivity.class);
        switch (view.getId()){
            case R.id.btn_data:
                intent.putExtra("data","今天天气好");
                break;
            case R.id.btn_list:
                ArrayList<Integer> list = new ArrayList<>();
                list.add(123);
                list.add(456);
                intent.putIntegerArrayListExtra("list",list);
                break;
            case R.id.btn_object:
                intent.putExtra("object",new User("lwj", 1));
                break;
            case R.id.btn_bundle:
                final Bundle bundle = new Bundle();
                bundle.putInt("id", 123);
                bundle.putString("name", "name");
                bundle.putSerializable("user",new User("lwj",123));
                intent.putExtras(bundle);
                break;
            case R.id.btn_return:
                intent.putExtra("data","传递字符");
               // startActivityForResult(intent,1);
                mylauncher.launch("传递字符串");
                return;

            default:
                break;
        }
        startActivity(intent);
    }
    //自定仪activityResultContract
    class MyResultContract extends  ActivityResultContract<String,String>{
        @NonNull
        @Override
        public Intent createIntent(@NonNull Context context, String input) {
           Intent intent = new Intent(MainActivity.this,SecondActivity.class);
           intent.putExtra("data",input);
           return intent;
        }
        @Override
        public String parseResult(int resultCode, @Nullable Intent intent) {
            if (resultCode == RESULT_OK && intent != null)
            {
                return intent.getStringExtra("data");
            }
            return null;
        }
    }

//注册Contract
private ActivityResultLauncher<String> mylauncher = registerForActivityResult(
        new MyResultContract(),
        new ActivityResultCallback<String>() {
            @Override
            public void onActivityResult(String result) {
                mainBinding.tvData.setText("返会的数据为" + result);
            }
        }


);

    //注册Contract
//    private ActivityResultLauncher<Intent> launcher = registerForActivityResult(
//            new ActivityResultContracts.StartActivityForResult(),
//            new ActivityResultCallback<ActivityResult>() {
//                @Override
//                public void onActivityResult(ActivityResult result) {
//                    if (result.getResultCode() == RESULT_OK) {
//                        final Intent data = result.getData();
//                        if (data != null) {
//                            mainBinding.tvData.setText("返会的数据为" + data.getStringExtra("data"));
//                        }
//                    } else {
//                        mainBinding.tvData.setText("出错了");
//                    }
//                }
//            }
//    );


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 1&& resultCode == RESULT_OK&&data != null){
//            final String returnData = data.getStringExtra("data");
//            if (returnData != null){
//                mainBinding.tvData.setText("返会的数据为" + returnData);
//            }else{
//                mainBinding.tvData.setText("出错了");
//            }
//        }
//    }
}