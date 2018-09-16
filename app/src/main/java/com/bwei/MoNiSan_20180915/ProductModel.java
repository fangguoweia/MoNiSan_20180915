package com.bwei.MoNiSan_20180915;

import com.bwei.MoNiSan_20180915.Api;
import com.bwei.MoNiSan_20180915.ProductApi;
import com.bwei.MoNiSan_20180915.Productentity;
import com.bwei.MoNiSan_20180915.Productcontract;
import com.bwei.base.net.RetrofitUtils;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 房国伟 on 2018/9/15.
 */
public class ProductModel implements Productcontract.ProductModel {
    @Override
    public Observable<Productentity> upload(HashMap<String, String> map) {

        Observable<Productentity> on = RetrofitUtils.getInstance().createApi(Api.APIURL, ProductApi.class).ProductData(map)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        return on;
    }
}
