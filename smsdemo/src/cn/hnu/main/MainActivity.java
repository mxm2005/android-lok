package cn.hnu.main;

import cn.hnu.main.util.SharedPreferencesUtil;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private EditText et_number;
	private Button btn_submit;
	private TextView tv_content;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		et_number = (EditText) findViewById(R.id.et_number);
		btn_submit = (Button) findViewById(R.id.btn_submit);
		tv_content = (TextView) findViewById(R.id.tv_content);
		btn_submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SharedPreferencesUtil.getInstance(getApplicationContext())
						.setSharedString("number",
								et_number.getText().toString().trim());
				Toast.makeText(getApplicationContext(), "设置拦截号码成功，重启手机才能生效", Toast.LENGTH_SHORT).show();
			}
		});
		tv_content.setText(SharedPreferencesUtil.getInstance(getApplicationContext()).getSharedString("sender") + "\n"
				+ SharedPreferencesUtil.getInstance(getApplicationContext()).getSharedString("content") + "\n"
				+ SharedPreferencesUtil.getInstance(getApplicationContext()).getSharedString("time"));
	}

}