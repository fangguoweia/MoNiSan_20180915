package com.bwei.MoNiSan_20180915;

import com.bwei.MoNiSan_20180915.Productentity;
import com.bwei.MoNiSan_20180915.Productcontract;

import java.util.HashMap;

import io.reactivex.functions.Consumer;

/**
 * Created by 房国伟 on 2018/9/15.
 */
public class Productp extends Productcontract.ProductPresenter {
    @Override
    public void upload(HashMap<String, String> map) {
       mModel.upload(map).subscribe(new Consumer<Productentity>() {
           @Override
           public void accept(Productentity productentity) throws Exception {
                mView.success(productentity);
           }
       }, new Consumer<Throwable>() {
           @Override
           public void accept(Throwable throwable) throws Exception {

           }
       });
    }
}
