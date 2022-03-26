package com.example.bmicalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.bmicalculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
private ActivityMainBinding activityMainBinding;
private ActionMode actionMode;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        registerForContextMenu(activityMainBinding.height);
        registerForContextMenu(activityMainBinding.weight);
        setContentView(activityMainBinding.getRoot());
        //点击计算事件
        activityMainBinding.enter.setOnClickListener(this);
        activityMainBinding.weight.setOnClickListener(this::PopUpMenu);
        activityMainBinding.height.setOnClickListener(this::PopUpMenu);
//        activityMainBinding.height.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                if (actionMode != null){
//                    return  false;
//                }
//                actionMode = startActionMode(callback);
//                view.setSelected(true);
//                return false;
//            }
//        });
    }


    /**
     * 选项菜单
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option_menu,menu);
        return true;
    }

    /**
     * 选项菜单时间
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_clear: {
                activityMainBinding.height.setText("");
                activityMainBinding.weight.setText("");
            }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * 上下文菜单
     * @param menu
     * @param v
     * @param menuInfo
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menu.setHeaderTitle("操作");
        menuInflater.inflate(R.menu.context_menu,menu);
    }

    /**
     * 上下文菜单选项
     * @param item
     * @return
     */
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_delete: {
                activityMainBinding.height.setText("");
                activityMainBinding.weight.setText("");
            }
            return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

//    ActionMode.Callback callback = new ActionMode.Callback() {
//        @Override
//        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
//            actionMode.getMenuInflater().inflate(R.menu.context_menu,menu);
//            return true;
//        }
//
//        @Override
//        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
//            return false;
//        }
//
//        @Override
//        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
//            switch (menuItem.getItemId()){
//                case R.id.item_delete: {
//                    activityMainBinding.height.setText("");
//                }
//                return true;
//                default:
//                    break;
//            }
//            return true;
//        }
//
//        @Override
//        public void onDestroyActionMode(ActionMode actionMode) {
//            actionMode = null;
//        }
//    };

    /**
     * 弹出式菜单
     * @param view
     */
    public void PopUpMenu(View view){
        PopupMenu popupMenu = new PopupMenu(MainActivity.this,view);
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                    switch (menuItem.getItemId()){
                        case R.id.item_delete1: {
                            if (view.getId() == activityMainBinding.weight.getId()){
                                 activityMainBinding.weight.setText("");
                            }
                            if (view.getId() == activityMainBinding.height.getId()){
                                activityMainBinding.height.setText("");
                            }
                        }
                        return true;
                        default:
                            return false;
                    }

            }
        });
    }

    @Override
    public void onClick(View view) {
        //获取身高体重的id，进行计算bmi值
        String weight = activityMainBinding.weight.getText().toString();
        String height = activityMainBinding.height.getText().toString();
        if (weight.equals("")||height.equals("")){
            Toast.makeText(MainActivity.this,"请输入数据",Toast.LENGTH_SHORT).show();
        }else{
            double wFloat = Double.parseDouble(weight);
            double hFloat = Double.parseDouble(height);
            double h = Math.pow(hFloat,2);
            String sBmi = String.valueOf(Math.round(wFloat/h));
            activityMainBinding.BMI.setText(sBmi);
            // 获取性别
            int checkedRadioButtonId = activityMainBinding.gender.getCheckedRadioButtonId();
            //根据性别打印结果
            if (checkedRadioButtonId == activityMainBinding.male.getId()){
                int i = Integer.parseInt(sBmi);
                if (i<20){
                    activityMainBinding.diagnosis.setText("体重过轻");
                }else if (i<25){
                    activityMainBinding.diagnosis.setText("体重正常");
                }else if (i<27){
                    activityMainBinding.diagnosis.setText("体重超重");
                }else if (i<30){
                    activityMainBinding.diagnosis.setText("轻度肥胖");
                }else if (i<35){
                    activityMainBinding.diagnosis.setText("中度肥胖");
                }else{
                    activityMainBinding.diagnosis.setText("重度肥胖");
                }
            }else{
                int i = Integer.parseInt(sBmi);
                if (i<19){
                    activityMainBinding.diagnosis.setText("体重过轻");
                }else if (i<24){
                    activityMainBinding.diagnosis.setText("体重正常");
                }else if (i<26){
                    activityMainBinding.diagnosis.setText("体重超重");
                }else if (i<29){
                    activityMainBinding.diagnosis.setText("轻度肥胖");
                }else if (i<34){
                    activityMainBinding.diagnosis.setText("中度肥胖");
                }else{
                    activityMainBinding.diagnosis.setText("重度肥胖");
                }
            }
        }

    }
}