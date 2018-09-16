package com.bwei.base.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gyf.barlibrary.ImmersionBar;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created on 2018/9/6.
 */
public abstract class BaseFragment extends Fragment {
    protected Activity mActivity;
    protected View mRootView;

   // protected ImmersionBar mImmersionBar;
    private Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(bindLayoutId(), container, false);
    }

    /**
     * 供子类实现
     *
     * @return
     */
    protected abstract int bindLayoutId();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        /*if (isImmersionBarEnabled()) {
            initImmersionBar();
        }*/
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initView();

    }
    protected abstract void initView();
    protected abstract void initData();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        /*if (!hidden && mImmersionBar != null)
            mImmersionBar.init();*/
    }

    /**
     * Sets layout id.
     *
     * @return the layout id
     */

    /**
     * 是否在Fragment使用沉浸式
     *
     * @return the boolean
     */
    /*protected boolean isImmersionBarEnabled() {
        return true;
    }*/

    /**
     * 初始化沉浸式
     */
    /*protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.keyboardEnable(true).navigationBarWithKitkatEnable(false).init();
    }*/






    /**
     * 找到activity的控件
     *
     * @param <T> the type parameter
     * @param id  the id
     * @return the t
     */
    @SuppressWarnings("unchecked")
    protected <T extends View> T findActivityViewById(@IdRes int id) {
        return (T) mActivity.findViewById(id);
    }
}