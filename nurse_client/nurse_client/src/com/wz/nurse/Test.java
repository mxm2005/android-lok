package com.wz.nurse;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.wz.nurse.util.JSONUtil;

import net.sf.json.JSONObject;

//import org.json.JSONObject;

import android.test.AndroidTestCase;

public class Test extends AndroidTestCase {
	public void test3() throws Exception {
		JSONUtil ju = new JSONUtil();
		List<Map<String, Object>> lists = ju.getData(getContext(), "patient_list.json");
		for (int i = 0; i < lists.size(); i++) {
			System.out.println("姓名：" + lists.get(i).get("VAA05") + "\n" 
						+ "性别：" + lists.get(i).get("ABW02") + "\n" 
						+ "年龄：" + lists.get(i).get("Agep") + "\n" 
						+ "床号：" + lists.get(i).get("BCQ04B") + "\n"
						+ "编号：" + lists.get(i).get("AAG01") + "\n"
						+ "名称：" + lists.get(i).get("AAG02") + "\n");
		}
	}
	
	
	@SuppressWarnings("rawtypes")
	public void test() throws Exception {
		Map<String, Object> maps = null;
		InputStream is = getContext().getAssets().open("patient_list.json");
		int size = is.available();
		byte[] buffer = new byte[size];
		is.read(buffer);
		is.close();
		String text = new String(buffer, "utf-8");
		org.json.JSONObject d = new org.json.JSONObject(text);
		List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
		for (int i = 1; i < 28; i++) {
			maps = new HashMap<String, Object>();
			String str = d.getString(String.valueOf(i));
			org.json.JSONObject dd = new org.json.JSONObject(str);
			for (Iterator iter = dd.keys(); iter.hasNext();) {
				String key = (String) iter.next();
				if ("VAA05".equals(key) || "ABW02".equals(key) || "Agep".equals(key) || "BCQ04B".equals(key) || "AAG01".equals(key) || "AAG02".equals(key)) {
					maps.put(key, dd.get(key));
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
	
	@SuppressWarnings("rawtypes")
	public void test2() throws Exception {
		Map<String, Object> maps = null;
		InputStream is = getContext().getAssets().open("patient_list.json");
		int size = is.available();
		byte[] buffer = new byte[size];
		is.read(buffer);
		is.close();
		String text = new String(buffer, "utf-8");
		org.json.JSONObject d = new org.json.JSONObject(text);
		List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
		for (Iterator iter = d.keys(); iter.hasNext();) {
			String key = (String) iter.next();
			System.out.println("key :" + key);
			if (isInteger(key)) {
				maps = new HashMap<String, Object>();
				String str = d.getString(key);
				org.json.JSONObject dd = new org.json.JSONObject(str);
				for (Iterator it = dd.keys(); it.hasNext();) {
					String keys = (String) it.next();
					if ("VAA05".equals(keys) || "ABW02".equals(keys) || "Agep".equals(keys) || "BCQ04B".equals(keys) || "AAG01".equals(keys) || "AAG02".equals(keys)) {
						maps.put(keys, dd.get(keys));
					}
				}
				lists.add(maps);
			}
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
	
	private boolean isInteger(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
	@SuppressWarnings("rawtypes")
	public void test1() throws Exception {
		Map<String, Object> maps = null;
		JSONObject jo = null;
		InputStream is = getContext().getAssets().open("patient_area.json");
		int size = is.available();
		byte[] buffer = new byte[size];
		is.read(buffer);
		is.close();
		String text = new String(buffer, "utf-8");
		JSONObject joo = JSONObject.fromObject(text);
		List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
		for (Iterator iter = joo.keys(); iter.hasNext();) {
			String key = (String) iter.next();
			System.out.println("key :" + key);
			if (isInteger(key)) {
				System.out.println("key2:" + key);
				maps = new HashMap<String, Object>();
				String str = joo.getString(key);
				jo = JSONObject.fromObject(str);
				for (Iterator it = jo.keys(); it.hasNext();) {
					String keyset = (String) it.next();
					if ("BCK01".equals(keyset) || "BCK03".equals(keyset)) {
						maps.put(keyset, jo.get(keyset));
					}
				}
				lists.add(maps);
			}
		}
		System.out.println(lists.size() + "oooooooooooooooooooooooooooooooooooooooooooo数量");
		for (int i = 0; i < lists.size(); i++) {
			System.out.println("病区编号：" + lists.get(i).get("BCK01") + "\n" 
						+ "病区名称：" + lists.get(i).get("BCK03") + "\n");
		}
	}
}