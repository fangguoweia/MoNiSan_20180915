package com.bwei.base.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bwei.base.base.mvp.BasePresenter;

import butterknife.ButterKnife;

/**
 * Created by 房国伟 on 2018/9/12.
 */
public abstract class BaseActivity2<P extends BasePresenter> extends AppCompatActivity {

    protected P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(provideLayoutId());
        ButterKnife.bind(this);
        presenter = providePresenter();

        initListener();
        initData();
    }

    public abstract int provideLayoutId();

    public abstract P providePresenter();


    public abstract void initListener();

    public abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
       if (presenter!=null){
           presenter=null;
       }
    }
}
