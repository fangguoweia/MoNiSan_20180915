package com.bwei.MoNiSan_20180915;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by 房国伟 on 2018/9/15.
 */
public interface DataApi {

    @POST("product/searchProducts")
    @FormUrlEncoded
    Observable<DataEntity> showData(@FieldMap Map<String,String> params);
}
