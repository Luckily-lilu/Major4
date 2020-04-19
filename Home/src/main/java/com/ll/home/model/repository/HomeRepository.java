package com.ll.home.model.repository;

import com.ll.home.callback.ResultCallback;
import com.ll.home.contract.HomeContract;
import com.ll.home.model.service.HomeService;

public class HomeRepository extends HomeContract.HomeRepository {
    @Override
    public void getData(ResultCallback callback) {
        mModel.getData(callback);
    }

    @Override
    protected void createModel() {
        mModel = new HomeService();
    }
}
