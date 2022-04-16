package com.example.crimeintent;

import android.icu.text.CaseMap;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.crimeintent.databinding.ActivityDataBinding;

public class DataActivity extends AppCompatActivity
        implements ContentFragment.FragmentCallback {
private ActivityDataBinding activityDataBinding;
final FragmentManager manager = getSupportFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDataBinding = ActivityDataBinding.inflate(getLayoutInflater());
        setContentView(activityDataBinding.getRoot());

        //加载titlefragment和contentfragment
        TitleFragment titleFragment = TitleFragment.newInstance("新闻标题");
        replaceFragment(R.id.container_title,titleFragment);
        ContentFragment contentFragment = ContentFragment.newInstance();
        replaceFragment(R.id.container_content,contentFragment);

}
    private void replaceFragment(int containerId,Fragment fragment){
        manager.beginTransaction()
                .replace(containerId,fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onItemSelected(int position) {
        Toast.makeText(this,"获取ContentFragment传递的数据为:" + position,Toast.LENGTH_SHORT).show();
    }
}