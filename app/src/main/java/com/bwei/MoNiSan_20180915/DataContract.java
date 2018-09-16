package com.bwei.MoNiSan_20180915;

import com.bwei.base.base.mvp.BasePresenter;
import com.bwei.base.base.mvp.IBaseModel;
import com.bwei.base.base.mvp.IBaseView;

import java.util.HashMap;

import io.reactivex.Observable;

/**
 * Created on 2018/9/15.
 */
public interface DataContract {
    //p
    abstract class DataPresenter extends BasePresenter<DataModel,DataView>{
        @Override
        public DataModel getmModel() {
            return new com.bwei.MoNiSan_20180915.DataModel();
        }
        public abstract void datagetdsata(HashMap<String,String> map);
    }
    //m
    interface DataModel extends IBaseModel{
        Observable<DataEntity> datagetdata(HashMap<String,String> map);
    }
    //v
    interface DataView extends IBaseView{
        void success(DataEntity dataEntity);
        void fail(String msg);
    }
}
