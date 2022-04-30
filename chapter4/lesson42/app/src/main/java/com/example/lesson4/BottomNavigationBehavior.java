package com.example.lesson4;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

/**
 * @author lwj
 */
public class BottomNavigationBehavior extends CoordinatorLayout.Behavior<BottomNavigationView> {
    public BottomNavigationBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull BottomNavigationView child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }
    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull BottomNavigationView child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
        child.setTranslationY(Math.max(0.0f,Math.min(child.getHeight(),child.getTranslationX() + dy)));
    }
    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull BottomNavigationView child, @NonNull View dependency) {
        if (dependency instanceof Snackbar.SnackbarLayout){
            this.updateSnackbar(child, (Snackbar.SnackbarLayout)dependency);
        }
        return super.layoutDependsOn(parent, child, dependency);
    }
    private void updateSnackbar(BottomNavigationView child, Snackbar.SnackbarLayout dependency){
        final ViewGroup.LayoutParams params = dependency.getLayoutParams();
        if (params instanceof CoordinatorLayout.LayoutParams){
            CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) params;
            layoutParams.setAnchorId(child.getId());
            layoutParams.gravity = Gravity.TOP;
            dependency.setLayoutParams(layoutParams);
        }
    }

}
