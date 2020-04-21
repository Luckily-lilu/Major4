package com.ll.user.presenter;

import com.ll.net.protocol.resp.BaseEntity;
import com.ll.net.protocol.resp.TestUserEntity;
import com.ll.net.rx.BaseObservable;
import com.ll.net.rx.BaseObserver;
import com.ll.user.contract.UserContract;
import com.ll.user.model.protocol.resp.UserEntity;
import com.ll.user.model.repository.UserRepository;

import io.reactivex.Observable;

public class UserPresenter extends UserContract.UserPresenter {

    public UserPresenter(UserContract.UserView _v) {
        super(_v);
    }

    @Override
    public void register(UserEntity userEntity) {
        Observable<UserEntity> register = mRepository.register(userEntity);
        BaseObservable.doObservable(register,new BaseObserver<UserEntity>(){
            @Override
            public void onNext(UserEntity userEntity) {
                super.onNext(userEntity);
                if (mView != null && mView.get() != null){
                    mView.get().registerSuccess();

                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mView != null && mView.get() != null){
                    mView.get().registerFailed();
                }
            }
        },mView.get().getLifecycleOwner());
//        register.observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(new BaseObserver<UserEntity>(){
//                    @Override
//                    public void onNext(UserEntity userEntity) {
//                        super.onNext(userEntity);
//                        mView.get().registerSuccess();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        super.onError(e);
//                        mView.get().registerFailed();
//                    }
//                });
    }

    @Override
    public void register2(TestUserEntity testUserEntity) {
        Observable<BaseEntity<TestUserEntity>> register = mRepository.register2(testUserEntity);
        BaseObservable.doObservable(register,new BaseObserver<BaseEntity<TestUserEntity>>(){
            @Override
            public void onNext(BaseEntity<TestUserEntity> testUserEntityBaseEntity) {
                super.onNext(testUserEntityBaseEntity);
                if (mView != null && mView.get() != null){
                    mView.get().registerSuccess();
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mView != null && mView.get() != null){
                    mView.get().registerFailed();
                }
            }
        },mView.get().getLifecycleOwner());
    }

    @Override
    protected void createRepository() {
        mRepository = new UserRepository();
    }
}
