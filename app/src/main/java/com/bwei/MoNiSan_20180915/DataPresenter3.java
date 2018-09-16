package com.bwei.MoNiSan_20180915;

import com.bwei.MoNiSan_20180915.DataEntity;
import com.bwei.MoNiSan_20180915.DataContract;

import java.util.HashMap;

import io.reactivex.functions.Consumer;

/**
 * Created by 房国伟 on 2018/9/15.
 */
public class DataPresenter3 extends DataContract.DataPresenter {
    @Override
    public void datagetdsata(HashMap<String, String> map) {
        mModel.datagetdata(map).subscribe(new Consumer<DataEntity>() {
            @Override
            public void accept(DataEntity dataEntity) throws Exception {
                mView.success(dataEntity);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        });
    }
}
