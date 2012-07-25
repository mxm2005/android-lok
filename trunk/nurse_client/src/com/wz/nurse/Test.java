package com.wz.nurse;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import android.test.AndroidTestCase;

public class Test extends AndroidTestCase {
	@SuppressWarnings("rawtypes")
	public void test() throws Exception {
		Map<String, Object> maps = null;
		JSONObject jo = null;
		InputStream is = getContext().getAssets().open("patient_list.json");
		int size = is.available();
		byte[] buffer = new byte[size];
		is.read(buffer);
		is.close();
		String text = new String(buffer, "utf-8");
		JSONObject joo = JSONObject.fromObject(text);
		List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
		for (int i = 1; i < 28; i++) {
			maps = new HashMap<String, Object>();
			String str = joo.getString(String.valueOf(i));
			jo = JSONObject.fromObject(str);
			for (Iterator iter = jo.keys(); iter.hasNext();) {
				String key = (String) iter.next();
				if ("VAA05".equals(key) || "ABW02".equals(key) || "Agep".equals(key) || "BCQ04B".equals(key) || "AAG01".equals(key) || "AAG02".equals(key)) {
					maps.put(key, jo.get(key));
				}
			}
			lists.add(maps);
		}
		System.out.println(lists.size() + "oooooooooooooooooooooooooooooooooooooooooooo数量");
		for (int i = 0; i < lists.size(); i++) {
			System.out.println("姓名：" + lists.get(i).get("VAA05") + "\n" 
						+ "性别：" + lists.get(i).get("ABW02") + "\n" 
						+ "年龄：" + lists.get(i).get("Agep") + "\n" 
						+ "床号：" + lists.get(i).get("BCQ04B") + "\n"
						+ "编号：" + lists.get(i).get("AAG01") + "\n"
						+ "名称：" + lists.get(i).get("AAG02") + "\n");
		}
	}
}