package com.ll.home.model.service;

import com.ll.home.callback.ResultCallback;
import com.ll.home.contract.HomeContract;

public class HomeService implements HomeContract.HomeModel {
    @Override
    public void getData(ResultCallback callback) {
        //TOOD:访问网络数据 读取SP 读取Sqlite
        String result = "我是网络请求的结果";
        callback.onSuccess(result);
    }
}
