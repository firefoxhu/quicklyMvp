package com.quickly.module.mvp.base;

import android.arch.lifecycle.Lifecycle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.AutoDisposeConverter;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

public class MvpBaseActivity extends AppCompatActivity {

    private static final String TAG = "MvpBaseActivity";

    /**
     * 初始化toolbar
     * @param toolbar
     * @param homeAsUpEnabled
     * @param title
     */
    protected void initToolbar(Toolbar toolbar, boolean homeAsUpEnabled, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnabled);
    }

    /**
     * 啄个出栈
     */
    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count == 0) {
            super.onBackPressed();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }



    /**
     *Fragment 和 Activity 中添加AutoDispose解决RxJava内存泄漏  因为他们都继承了 LifecycleOwner 所以可以用
     * @param <X>
     * @return
     */
    public <X> AutoDisposeConverter<X> bindAutoDispose() {
        return AutoDispose.autoDisposable(AndroidLifecycleScopeProvider
                .from(this, Lifecycle.Event.ON_DESTROY));
    }


}
