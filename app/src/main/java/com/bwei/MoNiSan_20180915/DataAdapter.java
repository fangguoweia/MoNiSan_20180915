package com.bwei.MoNiSan_20180915;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by 房国伟 on 2018/9/15.
 */
public class DataAdapter extends BaseQuickAdapter<DataEntity.DataBean,BaseViewHolder>{


    public DataAdapter(int layoutResId, @Nullable List<DataEntity.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DataEntity.DataBean item) {
        helper.setText(R.id.item_title,item.getTitle());
        helper.setText(R.id.item_price,item.getPrice()+"");
        String[] split = item.getImages().split("\\|");
        SimpleDraweeView view = helper.getView(R.id.item_sdv);
        view.setImageURI(split[0]);
    }
}
