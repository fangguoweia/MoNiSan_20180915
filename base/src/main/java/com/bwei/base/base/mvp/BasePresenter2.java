package com.bwei.base.base.mvp;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by 房国伟 on 2018/9/12.
 */
public abstract class BasePresenter2<V extends IView> {
    protected CompositeDisposable compositeDisposable = new CompositeDisposable();
    protected V view;

    public BasePresenter2(V view){
        this.view=view;
        initModel();
    }

    protected abstract void initModel();
    public void onDestroy(){
        view=null;
        compositeDisposable.clear();
    }
}
