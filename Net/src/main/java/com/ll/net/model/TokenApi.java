package com.ll.net.model;

import com.ll.net.protocol.TokenRespEntity;
import com.ll.net.protocol.resp.BaseEntity;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface TokenApi {
    @FormUrlEncoded
    @POST("token")
    Call<TokenRespEntity> getToken(@Field("grant_type") String grant_type, @Field("username") String username, @Field("password") String password);
}
