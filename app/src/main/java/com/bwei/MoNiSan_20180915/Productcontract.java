package com.bwei.MoNiSan_20180915;

import com.bwei.base.base.mvp.BasePresenter;
import com.bwei.base.base.mvp.IBaseModel;

import java.util.HashMap;

import io.reactivex.Observable;

/**
 * Created by 房国伟 on 2018/9/15.
 */
public interface Productcontract {

    //p
    abstract class ProductPresenter extends BasePresenter<ProductModel,ProductView>{
        @Override
        public ProductModel getmModel() {
            return new com.bwei.MoNiSan_20180915.ProductModel();
        }
        public abstract void upload(HashMap<String,String> map);
    }

    //m
    interface ProductModel extends IBaseModel{

        Observable<Productentity> upload(HashMap<String,String> map);

    }

    //v
    interface ProductView{
        void success(Productentity productentity);
        void fail(String msg);
    }
}
