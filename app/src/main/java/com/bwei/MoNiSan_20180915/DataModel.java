package com.bwei.MoNiSan_20180915;

import com.bwei.MoNiSan_20180915.Api;
import com.bwei.MoNiSan_20180915.DataApi;
import com.bwei.MoNiSan_20180915.DataEntity;
import com.bwei.MoNiSan_20180915.DataContract;
import com.bwei.base.net.RetrofitUtils;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 房国伟 on 2018/9/15.
 */
public class DataModel implements DataContract.DataModel{


    @Override
    public Observable<DataEntity> datagetdata(HashMap<String, String> map) {
        Observable<DataEntity> on= RetrofitUtils.getInstance()
                .createApi(Api.APIURL, DataApi.class).showData(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return on;
    }
}
