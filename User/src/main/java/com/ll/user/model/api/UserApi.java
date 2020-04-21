package com.ll.user.model.api;

import com.ll.net.protocol.resp.BaseEntity;
import com.ll.net.protocol.resp.TestUserEntity;
import com.ll.user.model.protocol.resp.UserEntity;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * 用户后台接口
 */
public interface UserApi {

    @POST("/videouser/register")
    Observable<UserEntity> register (@Body UserEntity userEntity);

    @POST("api/User/register")
    Observable<BaseEntity<TestUserEntity>> register2 (@Body TestUserEntity testUserEntity);
}
