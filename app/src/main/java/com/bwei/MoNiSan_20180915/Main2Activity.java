package com.bwei.MoNiSan_20180915;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bwei.base.base.mvp.BaseMvpActivity;
import com.bwei.base.base.mvp.BasePresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

public class Main2Activity extends BaseMvpActivity<Productcontract.ProductModel, Productcontract.ProductPresenter> implements Productcontract.ProductView {


    @BindView(R.id.f_recyclerView)
    RecyclerView fRecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.f_checkbox)
    CheckBox fCheckbox;
    @BindView(R.id.qx_tv)
    TextView qxTv;
    @BindView(R.id.f_price)
    TextView fPrice;
    private ProductAdapter productAdapter;

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_main2;
    }

    @Override
    protected void initData() {
        super.initData();
        refreshLayout.setRefreshHeader(new BezierRadarHeader(Main2Activity.this).setEnableHorizontalDrag(true));
        //设置 Footer 为 球脉冲 样式
        refreshLayout.setRefreshFooter(new BallPulseFooter(Main2Activity.this).setSpinnerStyle(SpinnerStyle.Scale));
        //请求数据
        EventBus.getDefault().register(this);
        HashMap<String, String> map = new HashMap<>();
        map.put("uid","17421");
        presenter.upload(map);
    }

    @Override
    protected void initView() {

    }


    @Override
    public BasePresenter initPresenter() {
        return new Productp();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void shub(String mas){
        productAdapter.notifyDataSetChanged();
        totalPrice();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void shua(String ma){
        productAdapter.notifyDataSetChanged();
        StringBuilder stringBuilder = new StringBuilder();
        if (productAdapter!=null){
            for (int i = 0; i < productAdapter.getBean().size(); i++) {
                stringBuilder.append(productAdapter.getBean().get(i).getIschekbox());
                for (int j = 0; j < productAdapter.getBean().get(i).getList().size(); j++) {
                    stringBuilder.append(productAdapter.getBean().get(i).getList().get(j).isIscheckbox());

                }
            }
        }
        if (stringBuilder.toString().contains("false")){
            fCheckbox.setChecked(false);
        }else {
            fCheckbox.setChecked(true);
        }
        totalPrice();
    }

    //请求成功
    @Override
    public void success(Productentity productentity) {

        Log.i("aaa",productentity+"");
        final List<Productentity.DataBean> datas = productentity.getData();
        fRecyclerView.setLayoutManager(new LinearLayoutManager(Main2Activity.this));
        productAdapter = new ProductAdapter(R.layout.three_item, datas);
        fRecyclerView.setAdapter(productAdapter);

        fCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fCheckbox.isChecked()){
                    for (int i = 0; i < datas.size(); i++) {
                        datas.get(i).setIschekbox(true);
                        for (int j = 0; j < datas.get(i).getList().size(); j++) {
                            datas.get(i).getList().get(j).setIscheckbox(true);
                        }
                    }
                }else {
                    for (int i = 0; i < datas.size(); i++) {
                        datas.get(i).setIschekbox(false);
                        for (int j = 0; j < datas.get(i).getList().size(); j++) {
                            datas.get(i).getList().get(j).setIscheckbox(false);
                        }
                    }
                }
                totalPrice();//价格
                productAdapter.notifyDataSetChanged();
            }
        });
    }
    private void totalPrice() {
        double totalprice=0;
        for (int i = 0; i < productAdapter.getBean().size(); i++) {
            for (int j = 0; j < productAdapter.getBean().get(i).getList().size(); j++) {
                if (productAdapter.getBean().get(i).getList().get(j).isIscheckbox()){
                    Productentity.DataBean.ListBean listBean=productAdapter.getBean().get(i).getList().get(j);
                    totalprice+=listBean.getTotalNum()*listBean.getBargainPrice();
                }
            }
        }
        //保留小数点后两位
        DecimalFormat nf = new DecimalFormat("##.##");
        String s = nf.format(totalprice);
        fPrice.setText("总价：￥"+s);
    }
    //请求失败
    @Override
    public void fail(String msg) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
