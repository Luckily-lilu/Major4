package com.ll.core.ui;

import android.app.Application;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ll.core.BasePresenter;
import com.ll.core.IView;

/**
 * @param <P>
 *mvp中activity的基类
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IView {

    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        createPresenter();
        initView(savedInstanceState);
        initData();
        initEvent();
    }

    /**
     * 创建/初始化P
     */
    protected abstract void createPresenter();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     *初始化页面事件
     */
    protected abstract void initEvent();

    /**
     * 初始化布局
     * @param savedInstanceState
     */
    protected abstract void initView(Bundle savedInstanceState);

    /**
     * 获取布局ID
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 显示消息
     * @param msg
     */
    protected void showMsg(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null){
            mPresenter = null;
        }
    }
}
