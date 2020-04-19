package com.ll.home.contract;

import com.ll.core.BasePresenter;
import com.ll.core.IModel;
import com.ll.core.IView;
import com.ll.core.Repository;
import com.ll.home.callback.ResultCallback;

public interface HomeContract {
    interface HomeModel extends IModel{
        void getData(ResultCallback callback);
    }

    abstract class HomeRepository extends Repository<HomeModel>{
        public abstract void getData(ResultCallback callback);
    }

    interface HomeView extends IView{
        void success(String string);
        void failed(Throwable throwable);
    }
    abstract class HomePresenter extends BasePresenter<HomeRepository,HomeView>{

        public HomePresenter(HomeView _v) {
            super(_v);
        }

        public abstract void getData();
    }
}
