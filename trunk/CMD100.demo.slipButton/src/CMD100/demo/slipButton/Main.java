/**
 * demo���� CMD100
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
        
        SlipButton myBtn =(SlipButton) findViewById(R.id.slipBtn);//���ָ���ؼ�
        myBtn.SetOnChangedListener(this);//Ϊ�ؼ����ü�����
    }

	@Override
	public void OnChanged(boolean CheckState) {//����ť״̬���ı�ʱ
		// TODO Auto-generated method stub
		if(CheckState)
			Toast.makeText(this,"����..." , Toast.LENGTH_SHORT).show();
		else
			Toast.makeText(this,"�ر���..." , Toast.LENGTH_SHORT).show();
	}
}