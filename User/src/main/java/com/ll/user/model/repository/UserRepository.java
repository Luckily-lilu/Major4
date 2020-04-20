package com.ll.user.model.repository;

import com.ll.net.protocol.resp.BaseEntity;
import com.ll.net.protocol.resp.TestUserEntity;
import com.ll.user.contract.UserContract;
import com.ll.user.model.protocol.resp.UserEntity;
import com.ll.user.model.service.UesrService;

import io.reactivex.Observable;

/**
 * 用户模块数据仓库
 */
public class UserRepository extends UserContract.UserRepository {
    @Override
    public Observable<UserEntity> register(UserEntity userEntity) {

        return mModel.register(userEntity);
    }

    @Override
    public Observable<BaseEntity<TestUserEntity>> register2(TestUserEntity testUserEntity) {
        return mModel.register2(testUserEntity);
    }

    @Override
    protected void createModel() {
        mModel = new UesrService();
    }
}
