package com.wz.nurse;

import java.util.List;
import java.util.Map;

import com.wz.nurse.adapter.PatientAdapter;
import com.wz.nurse.util.JSONUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;
/**
 * 我的病人界面
 * @author Administrator
 *
 */
public class MyPatientsActivity extends Activity {
	private GridView gv_patient;//九宫图控件
	private PatientAdapter adapter;//我的病人适配器
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_mypatient);
        initView();//初始化view
    }
    
    /**
     * 九宫图点击事件
     */
    private OnItemClickListener gvListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			TextView tvName = (TextView) view.findViewById(R.id.tvName);
			String name = tvName.getText().toString();
			Intent intent = new Intent(MyPatientsActivity.this, DetailedActivity.class);//点击九宫图跳转到DetailedActivity界面
			intent.putExtra("name", name);//如果要控制点击不同的九宫图跳转加载不同的json数据在这里控制
			startActivity(intent);
		}
	};
    
	/**
	 * 为PatientAdapter适配器提醒数据
	 * @return
	 */
    private List<Map<String, Object>> getPatientData() {
    	JSONUtil ju = new JSONUtil();//解析json类
    	try {
			return ju.getData(getApplicationContext(), "patient_list.json");//解析我的病人这个json
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }
    
    //初始化view
    private void initView() {
    	gv_patient = (GridView) findViewById(R.id.gv_patient);
    	adapter = new PatientAdapter(getApplicationContext(), getPatientData());//适配器的使用，第二个参数为数据
    	gv_patient.setAdapter(adapter);//适配器应用到九宫图控件上
    	gv_patient.setOnItemClickListener(gvListener);//点击九宫图事件绑定
    }
}
