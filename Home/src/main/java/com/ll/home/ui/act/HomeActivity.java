package com.ll.home.ui.act;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ll.common.router.RouterPath;
import com.ll.core.ui.BaseActivity;
import com.ll.home.R;
import com.ll.home.contract.HomeContract;
import com.ll.home.presenter.HomePresenter;

@Route(path = RouterPath.HOME_ACTIVITY)
public class HomeActivity extends BaseActivity<HomeContract.HomePresenter> implements HomeContract.HomeView {


    private Button actHomeBtn;
    private TextView actContentTv;


    @Override
    protected void createPresenter() {
        mPresenter = new HomePresenter(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {
        actHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.getData();
            }
        });
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        actHomeBtn = (Button) findViewById(R.id.act_home_btn);
        actContentTv = (TextView) findViewById(R.id.act_content_tv);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void success(String string) {
        showMsg("数据请求成功");
        actContentTv.setText(string);
    }

    @Override
    public void failed(Throwable throwable) {

    }
}
