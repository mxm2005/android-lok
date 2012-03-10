/**
 * demo出处 CMD100
 * 
 * site: www.cmd100.com
 * 
 * @author zcmmwbd
 */
package CMD100.demo.slipButton;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class Main extends Activity implements OnChangedListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        SlipButton myBtn =(SlipButton) findViewById(R.id.slipBtn);//获得指定控件
        myBtn.SetOnChangedListener(this);//为控件设置监听器
    }

	@Override
	public void OnChanged(boolean CheckState) {//当按钮状态被改变时
		// TODO Auto-generated method stub
		if(CheckState)
			Toast.makeText(this,"打开了..." , Toast.LENGTH_SHORT).show();
		else
			Toast.makeText(this,"关闭了..." , Toast.LENGTH_SHORT).show();
	}
}