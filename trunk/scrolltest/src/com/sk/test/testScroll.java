package com.sk.test;

import java.util.ArrayList;  
import java.util.HashMap;  

import com.sk.test.AutoLoadListener.AutoLoadCallBack;
import com.sk.test.LazyScrollView.OnScrollListener;
  
import android.app.Activity;  
import android.os.Bundle;  
import android.view.View;  
import android.widget.AdapterView;  
import android.widget.AdapterView.OnItemClickListener;  
import android.widget.GridView;  
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;  
import android.widget.TextView;
import android.widget.Toast;  
  
public class testScroll extends Activity {  
    /** Called when the activity is first created. */  
    @Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.scrollview);  
        final LinearLayout lin = (LinearLayout) findViewById(R.id.lin_sv);
        LazyScrollView sv = (LazyScrollView) findViewById(R.id.sss);
        sv.getView();  
        sv.setOnScrollListener(new OnScrollListener() {  
              
            @Override  
            public void onTop() {  
                // TODO Auto-generated method stub  
//                Log.d(tag,"------滚动到最上方------");  
            	Toast.makeText(testScroll.this, "最上面", Toast.LENGTH_SHORT).show();
            }  
              
            @Override  
            public void onScroll() {  
                // TODO Auto-generated method stub  
//                Log.d(tag,"没有到最下方，也不是最上方");
//            	Toast.makeText(testScroll.this, "中间", Toast.LENGTH_SHORT).show();
            }  
              
            @Override  
            public void onBottom() {  
                // TODO Auto-generated method stub  
//                Log.d(tag,"------滚动到最下方------");  
            	Toast.makeText(testScroll.this, "fdfdfdfdfdfdfdf", Toast.LENGTH_SHORT).show();
            	for (int i = 0; i < 10; i++){
            		TextView aa = new TextView(testScroll.this);
                	aa.setText("啦啦啦中。。。。。。");
                	lin.addView(aa);
            	}
            }  
        });  
    }
}  