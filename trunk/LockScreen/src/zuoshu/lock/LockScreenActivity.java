package zuoshu.lock;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;

public class LockScreenActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View lock = View.inflate(this, R.layout.main, null);
        LockLayer lockLayer = LockLayer.getInstance(this);
        lockLayer.setLockView(lock);
        lockLayer.lock();
    }
}