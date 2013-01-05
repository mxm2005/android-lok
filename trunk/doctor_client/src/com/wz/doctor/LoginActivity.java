package com.wz.doctor;

import com.wz.doctor.service.UpdateService;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LoginActivity extends Activity {
	private Button btn_login;//登录按钮
    private Button btn_exit;//退出按钮
    private Button btn_net_config;//网络配置按钮
    private MyApplication myApplication;

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
        
        checkVersion();
    }
    
    @SuppressWarnings("static-access")
	public void checkVersion() {
		myApplication = (MyApplication) getApplication();
		if (myApplication.localVersion < myApplication.serverVersion) {

			// 发现新版本，提示用户更新
			AlertDialog.Builder alert = new AlertDialog.Builder(this);
			alert.setTitle("软件升级")
					.setMessage("发现新版本,建议立即更新使用.")
					.setPositiveButton("更新",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									Intent updateIntent = new Intent(LoginActivity.this, UpdateService.class);
									updateIntent.putExtra("app_name", getResources().getString(R.string.app_name));
									startService(updateIntent);
								}
							})
					.setNegativeButton("取消",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.dismiss();
								}
							});
			alert.create().show();

		}
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
