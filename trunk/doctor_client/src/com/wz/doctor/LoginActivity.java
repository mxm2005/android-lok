package com.wz.doctor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LoginActivity extends Activity {
	private Button btn_login;//登录按钮
    private Button btn_exit;//退出按钮
    private Button btn_net_config;//网络配置按钮

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(buttonListener);
        btn_exit = (Button) findViewById(R.id.btn_exit);
        btn_exit.setOnClickListener(buttonListener);
        btn_net_config = (Button) findViewById(R.id.btn_net_config);
        btn_net_config.setOnClickListener(buttonListener);
    }
    
    private OnClickListener buttonListener = new OnClickListener() {
        
        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (((Button)v).getId()) {
            case R.id.btn_login://登录跳转到WelcomeActivity界面	登录控制在这里实现
                intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_exit://退出
                finish();
                break;
            case R.id.btn_net_config://跳转到NetConfigActivity界面，网络配置
                intent = new Intent(LoginActivity.this, NetConfigActivity.class);
                startActivity(intent);
                break;
            }
        }
    };
}
