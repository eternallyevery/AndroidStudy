package com.example.lesson4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.lesson4.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
private static ActivityMainBinding binding;
private NavigationBarView.OnItemSelectedListener navigation = new NavigationBarView.OnItemSelectedListener() {
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.navigation_home:
//                binding.tvMessage.setText(R.string.title_home);
                binding.contentMain.viewPage.setCurrentItem(0);
                return true;
            case R.id.navigation_dashboard:
//                binding.tvMessage.setText(R.string.title_dashboard);
                binding.contentMain.viewPage.setCurrentItem(1);
                return true;
            case R.id.navigation_notification:
//                binding.tvMessage.setText(R.string.title_notification);
                binding.contentMain.viewPage.setCurrentItem(2);
                return true;
            default:
                return false;
        }
    }
};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //设置自定义toolBar
        //界面主题设置为NoActionBar
        setSupportActionBar(binding.contentMain.toolbar);


        //配置viewPage的适配器
        final MainViewPageAdapter viewPageAdapter = new MainViewPageAdapter(this);
        binding.contentMain.viewPage.setAdapter(viewPageAdapter);
        binding.contentMain.viewPage.setOffscreenPageLimit(viewPageAdapter.getItemCount() - 1);
        //设置底部导航的监听器
        binding.contentMain.navigation.setOnItemSelectedListener(navigation);
        bindNavigationDrawer();

        //注册viewpager的滑动事件监听
        binding.contentMain.viewPage.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                binding.contentMain.navigation.getMenu().getItem(position).setChecked(true);
                binding.contentMain.toolbar.setTitle(binding.contentMain.navigation.getMenu().getItem(position).getTitle());
            }
        });

    }



    private void bindNavigationDrawer() {
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, binding.drawerLayout,
                binding.contentMain.toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        binding.navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                final int id = item.getItemId();
                if ( id == R.id.nav_home){
                    showSnackbarMsg("左侧导航菜单-主页");
                }else if (id == R.id.nav_gallery){
                    showSnackbarMsg("左侧导航菜单-相册");
                }else if (id == R.id.nav_slideshow){
                    showSnackbarMsg("左侧导航菜单-展示");
                }
                binding.drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    public void onFABClick(View view) {
        showSnackbarMsg("FAB button click");
    }


    private void showSnackbarMsg(String message){
        Snackbar make = Snackbar.make(binding.contentMain.coordinatorLayout, message, Snackbar.LENGTH_SHORT);
        make.setAction("ok", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                make.dismiss();
            }
        });
        make.show();
    }



    private static class MainViewPageAdapter extends FragmentStateAdapter{
        public MainViewPageAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }
        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position){
                case 0:
                    return HomeFragment.newInstance();
                case 1:
                    return DashFragment.newInstance();
                case 2:
                    return NotificationFragment.newInstance();
                default:

            }
            return  HomeFragment.newInstance();
        }
        @Override
        public int getItemCount() {
            return 3;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_setting,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_setting:
                Snackbar make = Snackbar.make(binding.contentMain.coordinatorLayout, "这是" + item.getTitle(), Snackbar.LENGTH_SHORT);
                make.setAction("ok", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        make.dismiss();
                    }
                });
                make.show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
