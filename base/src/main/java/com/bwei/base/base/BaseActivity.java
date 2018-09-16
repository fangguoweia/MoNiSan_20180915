package com.bwei.base.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created on 2018/9/6.
 * 基类的抽取
 */
public abstract class BaseActivity extends AppCompatActivity{

    private boolean isFullScreen = false;//是否全屏
    private boolean isStatus = false;//是否沉浸式状态栏
    private boolean isToolBar = false;//是否显示标题栏
    private Unbinder unbinder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bindLayoutId());
        unbinder = ButterKnife.bind(this);
        initView();
        initData();
    }

    protected abstract void initData();

    public void setFullScreen(boolean isFullScreen){

        //全屏代码
        if (isFullScreen){
//            getWindow().setFlags();
        }
    }

    /**
     * 初始化控件
     */
    protected abstract void initView();

    /**
     * 绑定根布局
     * @return
     */
    protected abstract int bindLayoutId();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder!=null){
            unbinder.unbind();//释放资源，回收内存，优化性能
            unbinder = null;
        }
    }

    /**
     * 无参数跳转
     * @param clz
     */
    public void startActivity(Class<? extends Activity> clz){
        startActivity(new Intent(this,clz));
    }
    /**
     * 有参数跳转
     * @param clz
     */
    public void startActivity(Class<? extends Activity> clz,Bundle bundle){
        Intent intent = new Intent(this,clz);
        intent.putExtras(bundle);
        startActivity(intent);


    }


    /**
     * 显示toast、
     * @param msg
     */
    public void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    /**
     * 显示带时间的toast
     * @param msg
     */
    public void showToast(String msg,int time){
        Toast.makeText(this, msg, time).show();
    }
}

