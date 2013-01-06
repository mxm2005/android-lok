package org.dengzh;

import android.app.Activity;
import android.app.Service;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class Test extends Activity
{
	SeekBar volSeekBar;
	AudioManager mAudioManager;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		volSeekBar = (SeekBar) findViewById(R.id.seek);
		mAudioManager = (AudioManager) getSystemService(Service.AUDIO_SERVICE);
		int maxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		Log.w("", "maxVolume: " + maxVolume);
		volSeekBar.setMax(maxVolume);
		int currentVolume = mAudioManager.getStreamVolume (AudioManager.STREAM_MUSIC);
		Log.w("", "currentVolume: " + currentVolume);
		setVolum(0);
		volSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener()
		{
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar)
			{
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar)
			{
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
			{
				setVolum(progress);
			}
		});
	}

	private void setVolum(int progress)
	{
		mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, AudioManager.FLAG_PLAY_SOUND | AudioManager.FLAG_SHOW_UI);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
    	int currentVolume = mAudioManager.getStreamVolume (AudioManager.STREAM_MUSIC);
	    switch (keyCode) {
	    case KeyEvent.KEYCODE_VOLUME_UP:
	    	mAudioManager.adjustStreamVolume(
	            AudioManager.STREAM_MUSIC,
	            AudioManager.ADJUST_RAISE,
	            AudioManager.FLAG_PLAY_SOUND | AudioManager.FLAG_SHOW_UI);
	    	volSeekBar.setProgress(currentVolume);
	        return true;
	    case KeyEvent.KEYCODE_VOLUME_DOWN:
	    	mAudioManager.adjustStreamVolume(
	            AudioManager.STREAM_MUSIC,
	            AudioManager.ADJUST_LOWER,
	            AudioManager.FLAG_PLAY_SOUND | AudioManager.FLAG_SHOW_UI);
	    	volSeekBar.setProgress(currentVolume);
	        return true;
	    default:
	        break;
	    }
	    return super.onKeyDown(keyCode, event);
	}

}
