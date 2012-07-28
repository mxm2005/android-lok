package com.android.study;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Main extends Activity {
    /** Called when the activity is first created. */
	/****
	 * 功能:点击按钮动态增加view
	 * 
	 * 学习 LayoutInflater的使用办法.
	 * 
	 * 
	 * 
	 */
	private Button buttonAdd1;
	private Button buttonAdd2;
	private LayoutInflater  layoutInflater;
	private LinearLayout mainLayout;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //方法一:取得LayoutInflater
        layoutInflater = getLayoutInflater();
        
        //方法二:
        //layoutInflater = LayoutInflater.from(this);
        
        //方法三:
        //layoutInflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);        
        
        
        mainLayout = (LinearLayout)findViewById(R.id.layout01);
        buttonAdd1 = (Button)findViewById(R.id.button1);
        buttonAdd2 = (Button)findViewById(R.id.button2);
        //给按钮增加监听事件
        buttonAdd1.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
				//根据LayoutInflater创建一个新的TextView,所以下面这一行代码不能放到外面事先生成.
				TextView newTextView = (TextView)layoutInflater.inflate(R.layout.infater01,null);
				
				
				//查看每次生成的TextView是否相同
				newTextView.setText(newTextView.getText() + "::hashCode " + newTextView.toString());
				
				//把TextView加入的当前layout中,R.id.layout01 是主layout的xml里面的id,如果你没有,需要加上
				mainLayout.addView(newTextView);
				//再加一次看能否成功.不能成功,说明了addView 必须不相同.
				//((LinearLayout)findViewById(R.id.layout01)).addView(newTextView);
			}
		});
        
        
        
        
        
       
        
        
        buttonAdd2.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
				layoutInflater.inflate(R.layout.infater02,mainLayout);
				//可以重复添加,能明白其中的原因吧
				layoutInflater.inflate(R.layout.infater02,mainLayout);
			}
		});
    }
}