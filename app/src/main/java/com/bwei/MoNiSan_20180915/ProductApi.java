package com.bwei.MoNiSan_20180915;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by 房国伟 on 2018/9/15.
 */
public interface ProductApi {

    @POST("product/getCarts")
    @FormUrlEncoded
    Observable<Productentity> ProductData(@FieldMap HashMap<String,String> params);
}
