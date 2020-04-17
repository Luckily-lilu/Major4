package com.ll.home.ui.act;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ll.common.router.RouterPath;
import com.ll.home.R;

@Route(path = RouterPath.HOME_ACTIVITY)
public class HomeActivity extends AppCompatActivity {
    private Button actHomeBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
    }

    private void initView() {
        actHomeBtn = (Button) findViewById(R.id.act_home_btn);
        actHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "欢迎来到HOME_ACTIVITY", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
