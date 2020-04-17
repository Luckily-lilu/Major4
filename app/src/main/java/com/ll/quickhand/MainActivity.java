package com.ll.quickhand;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.launcher.ARouter;
import com.ll.common.router.RouterPath;

public class MainActivity extends AppCompatActivity {
    private Button actAppBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        initEvent();
    }

    private void initView() {

        actAppBtn = (Button) findViewById(R.id.act_app_btn);

    }

    private void initEvent() {
        actAppBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(RouterPath.HOME_ACTIVITY).navigation();
            }
        });
    }
}
