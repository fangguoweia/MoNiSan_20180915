package com.bwei.MoNiSan_20180915;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.bwei.base.base.mvp.BaseMvpActivity;
import com.bwei.base.base.mvp.BasePresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseMvpActivity<DataContract.DataModel, DataContract.DataPresenter> implements DataContract.DataView {

    @BindView(R.id.Recycler_View)
    RecyclerView RecyclerView;
    @BindView(R.id.smarrt_fresh)
    SmartRefreshLayout smart_fresh;
    @BindView(R.id.btngouwu)
    Button btngouwu;
    private int page=1;

    //布局
    @Override
    protected int bindLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        super.initData();

        GETData();
        setSmart();
        btnClick();
    }

    private void btnClick() {
        btngouwu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Main2Activity.class));
            }
        });
    }

    private void GETData() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("keywords","笔记本");
        hashMap.put("page",page+"");
        presenter.datagetdsata(hashMap);
    }

    private void setSmart() {
        //设置 Header 为 贝塞尔雷达 样式
        smart_fresh.setRefreshHeader(new BezierRadarHeader(MainActivity.this).setEnableHorizontalDrag(true));
        //设置 Footer 为 球脉冲 样式
        smart_fresh.setRefreshFooter(new BallPulseFooter(MainActivity.this).setSpinnerStyle(SpinnerStyle.Scale));
        smart_fresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                GETData();
                smart_fresh.finishRefresh(2000);

            }
        });
        smart_fresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                GETData();
                smart_fresh.finishLoadMore(2000);
            }
        });
    }

    @Override
    public BasePresenter initPresenter() {
        return new DataPresenter3();
    }

    @Override
    protected void initView() {}
    @Override
    public void showLoading() {}
    @Override
    public void hideLoading() {}

    @Override
    public void success(DataEntity dataEntity) {
        //获取数据
        List<DataEntity.DataBean> data = dataEntity.getData();
        //展示数据
        RecyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
        DataAdapter dataAdapter = new DataAdapter(R.layout.layout_data, data);
        RecyclerView.setAdapter(dataAdapter);
    }

    @Override
    public void fail(String msg) {}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
