package com.wz.nurse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.wz.nurse.adapter.AreaAdapter;
import com.wz.nurse.adapter.ViewHolder;
import com.wz.nurse.util.JSONUtil;
/**
 * 我的病区
 * @author Administrator
 *
 */
public class MyInpatientAreaActivity extends Activity {
	private AreaAdapter adapter;//我的病区适配器
	private ListView lv_area;//我的病区列表控件
	private List<Map<String, Object>> list2 = null; 
	 ArrayList<String> listStr = null;
	private String[] name;
	private SimpleAdapter sAdapter;
	
	private static final String[] GENRES = new String[] {
        "Action", "Adventure", "Animation", "Children", "Comedy", "Documentary", "Drama",
        "Foreign", "History", "Independent", "Romance", "Sci-Fi", "Television", "Thriller"
    };
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_inpatient_area);
		lv_area = (ListView) findViewById(R.id.lv_area);
		sAdapter = new SimpleAdapter(this, getAreaDate(), R.layout.area_item,
				new String[] { "BCK01", "BCK03" }, new int[] { R.id.tvNumber,
						R.id.tvArea });
		ArrayAdapter a = new ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, convert());
		lv_area.setItemsCanFocus(false);
		lv_area.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		lv_area.setAdapter(a);
//		lv_area.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> arg0, View view, int arg2,
//					long arg3) {
//				// TODO Auto-generated method stub
//				CheckBox cb = (CheckBox) view.findViewById(R.id.cb);
//				if (cb.isChecked()) {
//					cb.setChecked(false);
//				} else {
//					cb.setChecked(true);
//				}
//			}
//		});
//		showCheckBoxListView();
	}
	
	// 显示带有checkbox的listview   
    public void showCheckBoxListView() {
    	List<String> list = new ArrayList<String>();
    	for (Map<String, Object> map : getAreaDate()) {
    		list.add((String)map.get("BCK01"));
    	}
    	name = list.toArray(new String[list.size()]);
        list2 = new ArrayList<Map<String,Object>>();
        for (int i = 0; i < getAreaDate().size(); i++) {
            Map<String, Object> map = new HashMap<String, Object>();  
            map.put("BCK01", name[i]);
            map.put("item_cb", false);  
            list2.add(map);  
  
            adapter = new AreaAdapter(MyInpatientAreaActivity.this, getAreaDate(), R.layout.area_item, new String[]{ "BCK01", "BCK03", "item_cb"}, new int[]{ R.id.tvNumber, R.id.tvArea, R.id.cb});
            lv_area.setAdapter(adapter);  
            listStr = new ArrayList<String>();  
            lv_area.setOnItemClickListener(new OnItemClickListener() {  
  
                @Override  
                public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {
                    ViewHolder holder = (ViewHolder) view.getTag();  
                    holder.cb.toggle();// 在每次获取点击的item时改变checkbox的状态   
                    AreaAdapter.isSelected.put(position, holder.cb.isChecked()); // 同时修改map的值保存状态   
                    if (holder.cb.isChecked() == true) {  
                        listStr.add(name[position]);  
                    } else {  
                        listStr.remove(name[position]);  
                    }  
                }  
  
            });  
        }  
    }  
    
    private String[] convert() {
    	String[] str;
    	List<String> lists = new ArrayList<String>();
    	for (Map<String, Object> map : getAreaDate()) {
    		lists.add((String)map.get("BCK01") + "         " + (String)map.get("BCK03"));
    	}
    	str = lists.toArray(new String[lists.size()]);
    	return str;
    }

	/**
	 * 为适配器提供数据
	 * @return
	 */
	private List<Map<String, Object>> getAreaDate() {
		JSONUtil ju = new JSONUtil();
		try {
			List<Map<String, Object>> lists = ju.getData(getApplicationContext(), "patient_area.json");//读取我的病区json数据
			return lists;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
