package com.wz.nurse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
    private Button btn_login;
    private Button btn_exit;
    private Button btn_net_config;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
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
            case R.id.btn_login:
                intent = new Intent(MainActivity.this, WelcomeActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_exit:
                finish();
                break;
            case R.id.btn_net_config:
                intent = new Intent(MainActivity.this, NetConfigActivity.class);
                startActivity(intent);
                break;
            }
        }
    };
}