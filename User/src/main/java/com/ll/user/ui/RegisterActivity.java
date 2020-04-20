package com.ll.user.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ll.core.IView;
import com.ll.core.ui.BaseActivity;
import com.ll.net.protocol.resp.TestUserEntity;
import com.ll.user.R;
import com.ll.user.contract.UserContract;
import com.ll.user.model.protocol.resp.UserEntity;
import com.ll.user.presenter.UserPresenter;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RegisterActivity extends BaseActivity<UserPresenter> implements UserContract.UserView {

    private android.widget.EditText actRegNameEt;
    private android.widget.EditText actRegPwdEt;
    private android.widget.Button actRegBtn;

    @Override
    protected void createPresenter() {
        mPresenter = new UserPresenter(this);
    }

    @Override
    protected void initData() {
//        Observable.interval(1, TimeUnit.SECONDS)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this)))
//                .subscribe(new Observer<Long>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(Long aLong) {
//                        Log.d("123", "onNext: ");
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.d("123", "onComplete: ");
//                    }
//                });
    }

    @Override
    protected void initEvent() {
        actRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = actRegNameEt.getText().toString();
                String pwd = actRegPwdEt.getText().toString();
//                UserEntity userEntity = new UserEntity();
//                userEntity.setUserPassWord(pwd);
//                userEntity.setPhoneNum(Integer.parseInt(name));
//                mPresenter.register(userEntity);

                TestUserEntity testUserEntity = new TestUserEntity();
                testUserEntity.setUsername(name);
                testUserEntity.setPwd(pwd);
                mPresenter.register2(testUserEntity);
            }
        });
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        actRegNameEt = (EditText) findViewById(R.id.act_reg_name_et);
        actRegPwdEt = (EditText) findViewById(R.id.act_reg_pwd_et);
        actRegBtn = (Button) findViewById(R.id.act_reg_btn);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void registerSuccess() {
        Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void registerFailed() {
        Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public LifecycleOwner getLifecycleOwner() {
        return this;
    }
}
