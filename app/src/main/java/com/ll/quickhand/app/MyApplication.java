package com.ll.quickhand.app;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

public class MyApplication extends Application {

    private boolean isDebug = true;

    public void setDebug(boolean debug) {
        isDebug = debug;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (isDebug){
            ARouter.openDebug();
            ARouter.openLog();
        }
        ARouter.init(this);

    }
}
