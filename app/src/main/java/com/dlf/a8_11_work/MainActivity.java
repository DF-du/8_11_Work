package com.dlf.a8_11_work;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dlf.a8_11_work.activity.HomeActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TextView mTitleTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        rxjava();
        anim();
    }

    private void anim() {
        ObjectAnimator alpha = ObjectAnimator.ofFloat(mTitleTv, "Alpha", 0, 1);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(mTitleTv, "ScaleX", 0, 1);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(mTitleTv, "ScaleY", 0, 1);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(3000);
        animatorSet.playTogether(alpha,scaleX,scaleY);
        animatorSet.start();
    }

    private void rxjava() {
        Observable.interval(1,1, TimeUnit.SECONDS)
                .take(2)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        if (aLong == 1) {
                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTitleTv = (TextView) findViewById(R.id.tv_title);
        mToolbar.setTitle("我的笔记");
        setSupportActionBar(mToolbar);
    }
}
