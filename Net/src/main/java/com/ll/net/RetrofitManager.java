package com.ll.net;

import android.text.TextUtils;
import android.util.Log;
import android.widget.RelativeLayout;

import com.ll.net.common.ConfigUrl;
import com.ll.net.model.TokenApi;
import com.ll.net.protocol.TokenRespEntity;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.ll.net.common.ConfigUrl.RETROFIT_BASEURL;

public class RetrofitManager {

    private static RetrofitManager retrofitManager;
    private Retrofit retrofit;
    private OkHttpClient.Builder builder;

    public RetrofitManager() {
       initRetrofit();
    }

    /**
     * Double check 双检查 解决多线程引起的并发问题
     * @return
     */
    public static RetrofitManager getInstance(){
        if (retrofitManager == null){
            synchronized (RetrofitManager.class){
                if (retrofitManager == null){
                    retrofitManager = new RetrofitManager();
                }
            }
        }
        return retrofitManager;
    }

    /**
     * 初始化Retrofit
     */
    private void initRetrofit(){
        retrofit = new Retrofit.Builder()
                .baseUrl(RETROFIT_BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(creatOkhttpClient())
                .build();
    }


    private OkHttpClient creatOkhttpClient() {

        return getBuilder()
                .writeTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(createRequestInterceptor())
                .addInterceptor(createLogIntercepter())
                .build();
    }

    private Interceptor createRequestInterceptor() {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Response response = chain.proceed(request);

                if (checkHttp401(response)){
                    String token = requestToken();
                    if (TextUtils.isEmpty(token)){
                        Log.e("Error","token is null ....");
                    }
                    Request.Builder newBuilder = request.newBuilder().addHeader("Authorization","bearer " + token);
                    Request newRequest = newBuilder.build();

                    return chain.proceed(newRequest);
                }

                return response;
            }
        };
        return interceptor;
    }

    private String requestToken() {

        TokenApi tokenApi = create(TokenApi.class);
        Call<TokenRespEntity> tokenService = tokenApi.getToken("password", ConfigUrl.AUTHCODE, "");
        try {
            retrofit2.Response<TokenRespEntity> result = tokenService.execute();
            if (result!=null&&result.body()!=null){
                return  result.body().getAccess_token();
            }
        } catch (IOException e) {
            Log.e("123",e.getMessage());
        }

        return "";
    }

    private boolean checkHttp401(Response response) {
        if (response == null){
            return false;
        }
        if (response.code() == 401){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 创建Okhttp日志拦截器
     * @return
     */
    private HttpLoggingInterceptor createLogIntercepter() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    /**
     * 获取Okhttp构建者
     * @return
     */
    private OkHttpClient.Builder getBuilder() {
        if (builder == null){
            builder = new OkHttpClient.Builder();
        }
        return builder;
    }

    /**
     * 创建服务
     * @param service
     * @param <T>
     * @return
     */
    public <T> T create(Class<T> service){
        return retrofit.create(service);
    }

    /**
     * 懒汉单例
     */
//    private static RetrofitManager retrofitManager;
//
//    public static RetrofitManager getInstance(){
//        if (retrofitManager == null){
//            retrofitManager = new RetrofitManager();
//        }
//        return retrofitManager;
//    }

    /**
     * 饿汉单例
     */
//    private static RetrofitManager retrofitManager = new RetrofitManager();
//
//    public static RetrofitManager getInstance(){
//        return retrofitManager;
//    }
}
