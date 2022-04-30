package com.example.lesson4;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lesson4.databinding.FragmentDashBinding;
import com.example.lesson4.databinding.FragmentHomeBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class DashFragment extends Fragment {
private FragmentDashBinding binding;

    public static DashFragment newInstance() {
        DashFragment fragment = new DashFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDashBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //设置view pager的适配器adapter
        DashboardPagerAdapter adapter = new DashboardPagerAdapter(this);
        binding.viewPager.setAdapter(adapter);
        binding.viewPager.setOffscreenPageLimit(adapter.getItemCount() - 1);
        //将tablayout与view进行关联
        new TabLayoutMediator(binding.tabsDashboard, binding.viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText("dash" + position);
            }
        }).attach();

    }
    private static class DashboardPagerAdapter extends FragmentStateAdapter{
        public DashboardPagerAdapter(@NonNull Fragment fragment) {
            super(fragment);

        }
        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return DashboardChildFragment.newInstance(position);
        }

        @Override
        public int getItemCount() {
            return 2;
        }
    }
}