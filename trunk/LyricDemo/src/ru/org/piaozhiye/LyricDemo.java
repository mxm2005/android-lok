package ru.org.piaozhiye;

import java.io.IOException;

import ru.org.piaozhiye.lyric.LyricView;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;

public class LyricDemo extends Activity {
	private MediaPlayer mp;
	private LyricView lyricView;
	private String path = "/mnt/sdcard/Because Of You.mp3";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		lyricView = (LyricView) findViewById(R.id.audio_lrc);
		mp = new MediaPlayer();
		mp.reset();
		try {
			mp.setDataSource(path);
			mp.prepare();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mp.start();
		new Thread(new UIUpdateThread()).start();

	}

	class UIUpdateThread implements Runnable {
		long time = 100; // 开始 的时间，不能为零，否则前面几句歌词没有显示出来

		public void run() {

			while (mp.isPlaying()) {

				long sleeptime = lyricView.updateIndex(time);
				time += sleeptime;
				mHandler.post(mUpdateResults);

				if (sleeptime == -1)
					return;
				try {
					Thread.sleep(sleeptime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}

	Handler mHandler = new Handler();
	Runnable mUpdateResults = new Runnable() {
		public void run() {
			lyricView.invalidate(); // 更新视图
		}
	};
}