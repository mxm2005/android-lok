package com.wz.doctor.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import android.content.Context;

/**
 * json解析工具类
 * @author Administrator
 *
 */
@SuppressWarnings("rawtypes")
public class JSONUtil {
	/**
	 * 判断传过来的string值是否可以转为int型
	 * @param str
	 * @return
	 */
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
	
	/**
	 * 解析json，存储在List<Map<String, Object>>
	 * @param context
	 * @param file 存在assets文件夹下的json文件
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getData(Context context, String file) throws Exception {
		Map<String, Object> maps = null;
		InputStream is = context.getAssets().open(file);
		int size = is.available();
		byte[] buffer = new byte[size];
		is.read(buffer);
		is.close();
		String text = new String(buffer, "utf-8");
		JSONObject jo = new JSONObject(text);
		List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
		for (Iterator iter = jo.keys(); iter.hasNext();) {
			String key = (String) iter.next();
			if (isInteger(key)) {
				maps = new HashMap<String, Object>();
				String str = jo.getString(key);
				JSONObject joo = new JSONObject(str);
				for (Iterator it = joo.keys(); it.hasNext();) {
					String keys = (String) it.next();
//					if ("VAA05".equals(keys) || "ABW02".equals(keys) || "Agep".equals(keys) || "BCQ04B".equals(keys) || "AAG01".equals(keys) || "AAG02".equals(keys)) {
						maps.put(keys, joo.get(keys));
//					}
				}
				lists.add(maps);
			}
		}
		return lists;
	}
	
//	/**
//	 * 把xml标签的值，转换成对象里属性的值
//	 * @param t 对象
//	 * @param name xml标签名
//	 * @param value xml标签名对应的值
//	 */
//	private void setXmlValue(Object t, String name, String value) {
//		try {
//			Field[] f = t.getClass().getDeclaredFields();
//			for (int i = 0; i < f.length; i++) {
//				if (f[i].getName().equalsIgnoreCase(name)) {
//					f[i].setAccessible(true);
//					// 获得属性类型
//					Class<?> fieldType = f[i].getType();
//					if (fieldType == String.class) {
//						f[i].set(t, value);
//					} else if (fieldType == Integer.TYPE) {
//						f[i].set(t, Integer.parseInt(value));
//					} else if (fieldType == Float.TYPE) {
//						f[i].set(t, Float.parseFloat(value));
//					} else if (fieldType == Double.TYPE) {
//						f[i].set(t, Double.parseDouble(value));
//					} else if (fieldType == Long.TYPE) {
//						f[i].set(t, Long.parseLong(value));
//					} else if (fieldType == Short.TYPE) {
//						f[i].set(t, Short.parseShort(value));
//					} else if (fieldType == Boolean.TYPE) {
//						f[i].set(t, Boolean.parseBoolean(value));
//					} else {
//						f[i].set(t, value);
//					}
//				}
//			}
//		} catch (Exception e) {
//			Log.e("xml error", e.toString());
//			e.printStackTrace();
//		}
//	}
}
