package com.bwei.base.base.mvp;

/**
 * Created on 2018/9/6.
 */
public interface IBaseView {

    BasePresenter initPresenter();

    void showLoading();
    void hideLoading();

    void fail(String msg);//请求失败
}
