package com.ll.user.model.service;

import com.ll.net.RetrofitManager;
import com.ll.net.protocol.resp.BaseEntity;
import com.ll.net.protocol.resp.TestUserEntity;
import com.ll.user.contract.UserContract;
import com.ll.user.model.api.UserApi;
import com.ll.user.model.protocol.resp.UserEntity;

import io.reactivex.Observable;

/**
 * 用户模块service
 */
public class UesrService implements UserContract.UserModel {
    @Override
    public Observable<UserEntity> register(UserEntity userEntity) {
        UserApi userApi = RetrofitManager.getInstance().create(UserApi.class);
        Observable<UserEntity> register = userApi.register(userEntity);
        return register;
    }

    @Override
    public Observable<BaseEntity<TestUserEntity>> register2(TestUserEntity testUserEntity) {
        UserApi userApi = RetrofitManager.getInstance().create(UserApi.class);
        Observable<BaseEntity<TestUserEntity>> register2 = userApi.register2(testUserEntity);
        return register2;
    }
}
