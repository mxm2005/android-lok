package cn.hnu.main;

import java.io.File;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		File file = new File("/mnt/sdcard/");
		String[] files = file.list();
		for (int i = 0; i < files.length; i++) {
			Log.e("", files[i]);
		}
	}

}