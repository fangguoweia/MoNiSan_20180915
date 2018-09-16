package com.bwei.base.base.mvp;

import java.lang.ref.WeakReference;

/**
 * Created by 房国伟 on 2018/9/6.
 */
public abstract class BasePresenter<M,V> {

    public M mModel;
    public V mView;
    private WeakReference<V> weakReference;

    public abstract M getmModel();

    /**
     * 绑定view
     *
     * @param mModel
     * @param mView
     */
    public void attach(M mModel, V mView) {
        this.mModel = mModel;
        weakReference = new WeakReference<>(mView);
        this.mView = weakReference.get();
    }

    /**
     * 解绑
     */
    public void detach() {

        if (weakReference != null) {
            weakReference.clear();//清空若引用对象
            weakReference = null;
        }


    }
}
