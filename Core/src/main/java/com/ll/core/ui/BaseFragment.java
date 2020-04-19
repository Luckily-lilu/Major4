package com.ll.core.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ll.core.BasePresenter;
import com.ll.core.IView;

/**
 * mvp中fragment的基类
 * @param <P>
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements IView {
    
    protected P mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View baseView = LayoutInflater.from(getContext()).inflate(getLayoutId(),container,false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        createPresenter();
        initView(savedInstanceState);
        initData();
        initEvent();

    }

    protected abstract int getLayoutId();

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
     * 获取布局组件ID
     * @param viewId
     * @param <T>
     * @return
     */
    protected <T extends View> T $(@IdRes int viewId){
        return this.getView().findViewById(viewId);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null){
            mPresenter = null;
        }
    }
}
