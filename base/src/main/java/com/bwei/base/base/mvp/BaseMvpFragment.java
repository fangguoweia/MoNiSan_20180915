package com.bwei.base.base.mvp;

import com.bwei.base.base.BaseFragment;

/**
 * Created by 房国伟 on 2018/9/6.
 */
public abstract class BaseMvpFragment <M extends IBaseModel,P extends BasePresenter> extends BaseFragment implements IBaseView{
    public M model;
    public P presenter;

    @Override
    protected abstract int bindLayoutId();

    protected void initData() {
        presenter = (P) initPresenter();
        if (presenter!=null){
            model = (M) presenter.getmModel();
            if (model!=null){
                presenter.attach(model,this);
            }
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter!=null){
            presenter.detach();//解除绑定，回收资源，释放内存，提高性能
        }
    }



}
