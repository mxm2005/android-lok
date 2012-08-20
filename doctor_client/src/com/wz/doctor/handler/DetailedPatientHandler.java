package com.wz.doctor.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.wz.doctor.HomeActivity;
import com.wz.doctor.R;
import com.wz.doctor.util.JSONUtil;

import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
/**
 * 病人详情
 * @author yjh
 *
 */
public class DetailedPatientHandler {
	private HomeActivity mHomeActivity;
	private LayoutInflater layoutInflater;

	public DetailedPatientHandler(HomeActivity mHomeActivity) {
		this.mHomeActivity = mHomeActivity;
		layoutInflater = mHomeActivity.getLayoutInflater();
	}

	/**
	 * 加载单个病人详细列表
	 */
	public LinearLayout patientItem() {
		LinearLayout patient_detailed = (LinearLayout) layoutInflater.inflate(
				R.layout.patient_detailed, null);
		ListView lv_detailed = (ListView) patient_detailed
				.findViewById(R.id.lv_detailed);
		SimpleAdapter adapter = new SimpleAdapter(mHomeActivity,
				getDetailedData(""), R.layout.detailed_item,
				new String[] { "tv_detailed" }, new int[] { R.id.tv_detailed });
		lv_detailed.setAdapter(adapter);
		return patient_detailed;
	}

	@SuppressWarnings("rawtypes")
	private List<Map<String, Object>> getDetailedData(String name) {
		JSONUtil ju = new JSONUtil();
		List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		try {
			List<Map<String, Object>> patientList = ju.getData(mHomeActivity,
					"patient_item.json");
			for (Map<String, Object> pl : patientList) {
				Iterator iter = pl.entrySet().iterator();
				while (iter.hasNext()) {
					map = new HashMap<String, Object>();
					Map.Entry entry = (Map.Entry) iter.next();
					String key = (String) entry.getKey();
					if ("VAA05".equals(key)) {
						map.put("tv_detailed", "姓名：" + entry.getValue());
						lists.add(map);
					} else if ("ABW02".equals(key)) {
						map.put("tv_detailed", "性别：" + entry.getValue());
						lists.add(map);
					} else if ("Agep".equals(key)) {
						map.put("tv_detailed", "年龄：" + entry.getValue());
						lists.add(map);
					} else if ("BCQ04B".equals(key)) {
						map.put("tv_detailed", "床号：" + entry.getValue());
						lists.add(map);
					} else if ("AAG02".equals(key)) {
						map.put("tv_detailed", "级别：" + entry.getValue());
						lists.add(map);
					} else if ("BCE03C".equals(key)) {
						map.put("tv_detailed", "住院医生：" + entry.getValue());
						lists.add(map);
					} else if ("BCK03".equals(key)) {
						map.put("tv_detailed", "住院科室：" + entry.getValue());
						lists.add(map);
					} else if ("VBM04".equals(key)) {
						map.put("tv_detailed", "病人余额：" + entry.getValue());
						lists.add(map);
					} else if ("ABC02".equals(key)) {
						map.put("tv_detailed", "病人费用：" + entry.getValue());
						lists.add(map);
					}
				}
			}
			return lists;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
