package com.bwei.MoNiSan_20180915;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by 房国伟 on 2018/9/15.
 */
public class Product2Adapter extends BaseQuickAdapter<Productentity.DataBean.ListBean,BaseViewHolder> {

    private List<Productentity.DataBean.ListBean> bean;

    public Product2Adapter(int layoutResId, @Nullable List<Productentity.DataBean.ListBean> data) {
        super(layoutResId, data);
        bean=data;
    }

    @Override
    protected void convert(BaseViewHolder helper, Productentity.DataBean.ListBean item) {
        item.setTotalNum(item.getNum());

        int position = helper.getLayoutPosition();
        final Productentity.DataBean.ListBean listBean = bean.get(position);

        helper.setText(R.id.show_price,"优惠价：¥"+item.getBargainPrice());
        helper.setText(R.id.zu_ed,item.getNum()+"");

        String[] split = item.getImages().split("\\|");
        SimpleDraweeView view1 = helper.getView(R.id.show2_ima);
        view1.setImageURI(split[0]);

        helper.setText(R.id.show2_text,listBean.getTitle());
        helper.setChecked(R.id.up_cb,listBean.isIscheckbox());


        EditText view = helper.getView(R.id.zu_ed);
        //checkbox
        final CheckBox up_cb = helper.getView(R.id.up_cb);

        //加减器添加可点击事件
        helper.setText(R.id.zu_btn_del,"-").addOnClickListener(R.id.zu_btn_del);
        helper.setText(R.id.zu_btn_add, "+").addOnClickListener(R.id.zu_btn_add);
        //点击checkbox
        up_cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (up_cb.isChecked()){
                    listBean.setIscheckbox(true);
                }else {
                    listBean.setIscheckbox(false);
                }
                //局部刷新
                notifyDataSetChanged();
                //通知父类
                String ma="";
                EventBus.getDefault().post(ma);
            }
        });

    }
}
