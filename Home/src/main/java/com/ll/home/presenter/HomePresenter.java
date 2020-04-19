package com.ll.home.presenter;

import android.util.Log;

import com.ll.home.callback.ResultCallback;
import com.ll.home.contract.HomeContract;
import com.ll.home.model.repository.HomeRepository;

public class HomePresenter extends HomeContract.HomePresenter {

    private static final String TAG = "HomePresenter";

    public HomePresenter(HomeContract.HomeView _v) {
        super(_v);
    }

    @Override
    public void getData() {
        mRepository.getData(new ResultCallback() {
            @Override
            public void onSuccess(String string) {
                if (mView != null && mView.get() != null){
                    mView.get().success(string);
                } else {
                    Log.i(TAG, "mView or mView.get() is null");
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    @Override
    protected void createRepository() {
        mRepository = new HomeRepository();
    }
}
