package com.ll.user.contract;

import com.ll.core.BasePresenter;
import com.ll.core.IModel;
import com.ll.core.IView;
import com.ll.core.Repository;
import com.ll.net.protocol.resp.BaseEntity;
import com.ll.net.protocol.resp.TestUserEntity;
import com.ll.user.model.protocol.resp.UserEntity;

import io.reactivex.Observable;

public interface UserContract {

    interface UserModel extends IModel{
        Observable<UserEntity> register(UserEntity userEntity);
        Observable<BaseEntity<TestUserEntity>> register2(TestUserEntity testUserEntity);
    }

    abstract class UserRepository extends Repository<UserModel>{
        public abstract Observable<UserEntity> register(UserEntity userEntity);
        public abstract Observable<BaseEntity<TestUserEntity>> register2(TestUserEntity testUserEntity);
    }

    interface UserView extends IView{
        void registerSuccess();
        void registerFailed();
    }

    abstract class UserPresenter extends BasePresenter<UserRepository,UserView>{

        public UserPresenter(UserView _v) {
            super(_v);
        }
        public abstract void register(UserEntity userEntity);

        public abstract void register2(TestUserEntity testUserEntity);
    }
}
