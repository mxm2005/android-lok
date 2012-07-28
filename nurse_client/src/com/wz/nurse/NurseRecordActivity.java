package com.wz.nurse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.wz.nurse.bean.Addition;
import com.wz.nurse.bean.Curve;
import com.wz.nurse.bean.Form;
import com.wz.nurse.bean.Grave;
import com.wz.nurse.bean.Record;
import com.wz.nurse.bean.Text;
import com.wz.nurse.util.JSONUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NurseRecordActivity extends Activity {
	private LinearLayout lin_record;
	private List<Map<String, Object>> lists;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nurse_record);
        initView();
        
//        JSONUtil ju = new JSONUtil();
//        try {
//        	lists = ju.getData(getApplicationContext(), "nurse_input.json");
//        	List<Record> records = new ArrayList<Record>();
//    		for (int i = 0; i < lists.size(); i++) {
//    			Record record = new Record();
//    			record.setTitle((String)lists.get(i).get("CBS05"));
//    			record.setAllowLength(Integer.parseInt((String)  lists.get(i).get("CBS08")));
//    			record.setPoint(Integer.parseInt((String)  lists.get(i).get("CBS09")));
//    			record.setUnit((String) lists.get(i).get("CBS10"));
//    			record.setType(Integer.parseInt((String)  lists.get(i).get("CBS11")));
//    			record.setRange((String) lists.get(i).get("CBS12"));
//    			record.setSenior((String) lists.get(i).get("CBS13"));
//    			records.add(record);
//    		}
//    		
//    		for (Record r : records) {
//    			if ("1)体温曲线项目".equals(r.getSenior())) {
////    				System.out.println(r.getTitle());
//    				if ("体温".equals(r.getTitle())) {
//    					//部位+物理降温
//    					//（部位为选择：腋温+口温+肛温）（物理降温为checkbox）
//    				} else if ("脉搏".equals(r.getTitle())) {
//    					//类型
//    					//（类型为选择：自然心率+起搏器+触不清+触不到+脉搏短拙）
//    				} else if ("呼吸".equals(r.getTitle())) {
//    					//呼吸机
//    					//（呼吸机为checkbox）
//    				} else {
//    					if (r.getType() == 0) {
//    						//生成文本框
//    						lin_record.addView(LayoutInflater.from(this).inflate(R.layout.lin_item_ev, null));
//    					} else if (r.getType() == 3) {
//    						//生成单选框
//    						lin_record.addView(LayoutInflater.from(this).inflate(R.layout.lin_item_cb, null));
//    					} else if (r.getType() == 4) {
//    						//生成复选框
//    					}
//    				}
//    			} else if ("5)附加项目".equals(r.getSenior())) {
//    				if ("小便次数".equals(r.getTitle())) {
//    					//导尿
//    					//（导尿为checkbox）
//    				} else if ("大便次数".equals(r.getTitle())) {
//    					//方式
//    					//（方式为选择：正常+灌肠+失禁+人工肛门） 
//    				} else {
//    					if (r.getType() == 0) {
//    						//生成文本框
//    						lin_record.addView(LayoutInflater.from(this).inflate(R.layout.lin_item_ev, null));
//    					} else if (r.getType() == 3) {
//    						//生成单选框
//    						lin_record.addView(LayoutInflater.from(this).inflate(R.layout.lin_item_cb, null));
//    					} else if (r.getType() == 4) {
//    						//生成复选框
//    					}
//    				}
//    			} else if ("2)体温表格项目".equals(r.getSenior())) {
//    				if (r.getType() == 0) {
//						//生成文本框
//    					lin_record.addView(LayoutInflater.from(this).inflate(R.layout.lin_item_ev, null));
//					} else if (r.getType() == 3) {
//						//生成单选框
//						lin_record.addView(LayoutInflater.from(this).inflate(R.layout.lin_item_cb, null));
//					} else if (r.getType() == 4) {
//						//生成复选框
//					}
//    			} else if ("3)危重记录项目".equals(r.getSenior())) {
//    				if (r.getType() == 0) {
//						//生成文本框
//    					lin_record.addView(LayoutInflater.from(this).inflate(R.layout.lin_item_ev, null));
//					} else if (r.getType() == 3) {
//						//生成单选框
//						lin_record.addView(LayoutInflater.from(this).inflate(R.layout.lin_item_cb, null));
//					} else if (r.getType() == 4) {
//						//生成复选框
//					}
//    			} else if ("4)文字记录项目".equals(r.getSenior())) {
//    				if (r.getType() == 0) {
//						//生成文本框
//    					lin_record.addView(LayoutInflater.from(this).inflate(R.layout.lin_item_ev, null));
//					} else if (r.getType() == 3) {
//						//生成单选框
//						lin_record.addView(LayoutInflater.from(this).inflate(R.layout.lin_item_cb, null));
//					} else if (r.getType() == 4) {
//						//生成复选框
//					}
//    			}
//    		}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        
        List<Record> records = new ArrayList<Record>();
		List<Curve> curves = new ArrayList<Curve>();
		List<Form> forms = new ArrayList<Form>();
		List<Grave> graves = new ArrayList<Grave>();
		List<Text> texts = new ArrayList<Text>();
		List<Addition> additions = new ArrayList<Addition>();
		JSONUtil ju = new JSONUtil();
		try {
			lists = ju.getData(getApplicationContext(), "nurse_input.json");
			for (int i = 0; i < lists.size(); i++) {
				Record record = new Record();
				record.setTitle((String)lists.get(i).get("CBS05"));
				record.setAllowLength(Integer.parseInt((String)  lists.get(i).get("CBS08")));
				record.setPoint(Integer.parseInt((String)  lists.get(i).get("CBS09")));
				record.setUnit((String) lists.get(i).get("CBS10"));
				record.setType(Integer.parseInt((String)  lists.get(i).get("CBS11")));
				record.setRange((String) lists.get(i).get("CBS12"));
				record.setSenior((String) lists.get(i).get("CBS13"));
				records.add(record);
			}
			
			for (Record r : records) {
				if ("1)体温曲线项目".equals(r.getSenior())) {
					Curve curve = new Curve();
					curve.setTitle(r.getTitle());
					curve.setUnit(r.getUnit());
					curve.setAllowLength(r.getAllowLength());
					curve.setPoint(r.getPoint());
					curve.setRange(r.getRange());
					curve.setType(r.getType());
					curves.add(curve);
				} else if ("5)附加项目".equals(r.getSenior())) {
					Addition addition = new Addition();
					addition.setTitle(r.getTitle());
					addition.setUnit(r.getUnit());
					addition.setAllowLength(r.getAllowLength());
					addition.setPoint(r.getPoint());
					addition.setRange(r.getRange());
					addition.setType(r.getType());
					additions.add(addition);
				} else if ("2)体温表格项目".equals(r.getSenior())) {
					Form form = new Form();
					form.setTitle(r.getTitle());
					form.setUnit(r.getUnit());
					form.setAllowLength(r.getAllowLength());
					form.setPoint(r.getPoint());
					form.setRange(r.getRange());
					form.setType(r.getType());
					forms.add(form);
				} else if ("3)危重记录项目".equals(r.getSenior())) {
					Grave grave = new Grave();
					grave.setTitle(r.getTitle());
					grave.setUnit(r.getUnit());
					grave.setAllowLength(r.getAllowLength());
					grave.setPoint(r.getPoint());
					grave.setRange(r.getRange());
					grave.setType(r.getType());
					graves.add(grave);
				} else if ("4)文字记录项目".equals(r.getSenior())) {
					Text text = new Text();
					text.setTitle(r.getTitle());
					text.setUnit(r.getUnit());
					text.setAllowLength(r.getAllowLength());
					text.setPoint(r.getPoint());
					text.setRange(r.getRange());
					text.setType(r.getType());
					texts.add(text);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (curves.size() != 0) {
			View view = LayoutInflater.from(this).inflate(R.layout.item_bg, null);
			TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
			tv_title.setText("1)体温曲线项目");
			LinearLayout lin_item = (LinearLayout) view.findViewById(R.id.lin_item);
			for (Curve c : curves) {
				if ("体温".equals(c.getTitle())) {
					//部位+物理降温
					//（部位为选择：腋温+口温+肛温）（物理降温为checkbox）
					TextView tv = new TextView(this);
					tv.setText("部位。。。标记下");
					lin_record.addView(tv);
				} else if ("脉搏".equals(c.getTitle())) {
					//类型
					//（类型为选择：自然心率+起搏器+触不清+触不到+脉搏短拙）
					TextView tv = new TextView(this);
					tv.setText("类型。。。标记下");
					lin_record.addView(tv);
				} else if ("呼吸".equals(c.getTitle())) {
					//呼吸机
					//（呼吸机为checkbox）
					TextView tv = new TextView(this);
					tv.setText("呼吸。。。标记下");
					lin_record.addView(tv);
				} else {
					if (c.getType() == 0) {
						//生成文本框
						View lin_item_ev = LayoutInflater.from(this).inflate(R.layout.lin_item_ev, null);
						TextView tvTitle = (TextView) lin_item_ev.findViewById(R.id.tvTitle);
						EditText etSummary = (EditText) lin_item_ev.findViewById(R.id.etSummary);
						tvTitle.setText(c.getTitle());
						lin_item.addView(lin_item_ev);
					} else if (c.getType() == 3) {
						//生成单选框
						lin_record.addView(LayoutInflater.from(this).inflate(R.layout.lin_item_cb, null));
					} else if (c.getType() == 4) {
						//生成复选框
					}
				}
			}
			lin_record.addView(view);
			TextView tv = new TextView(this);
			tv.setHeight(10);
			lin_record.addView(tv);
		}
		
		
		if (forms.size() != 0) {
			View view = LayoutInflater.from(this).inflate(R.layout.item_bg, null);
			TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
			tv_title.setText("2)体温表格项目");
			LinearLayout lin_item = (LinearLayout) view.findViewById(R.id.lin_item);
			for (Form f : forms) {
				if ("小便(次)".equals(f.getTitle())) {
					//导尿
					//（导尿为checkbox）
				} else if ("大便(次)".equals(f.getTitle())) {
					//方式
					//（方式为选择：正常+灌肠+失禁+人工肛门） 
				} else {
					if (f.getType() == 0) {
						//生成文本框
						View lin_item_ev = LayoutInflater.from(this).inflate(R.layout.lin_item_ev, null);
						TextView tvTitle = (TextView) lin_item_ev.findViewById(R.id.tvTitle);
						EditText etSummary = (EditText) lin_item_ev.findViewById(R.id.etSummary);
						tvTitle.setText(f.getTitle());
						lin_item.addView(lin_item_ev);
					} else if (f.getType() == 3) {
						//生成单选框
						
					} else if (f.getType() == 4) {
						//生成复选框
					}
				}
			}
			lin_record.addView(view);
			TextView tv = new TextView(this);
			tv.setHeight(10);
			lin_record.addView(tv);
		}
		
		if (graves.size() != 0) {
			View view = LayoutInflater.from(this).inflate(R.layout.item_bg, null);
			TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
			tv_title.setText("3)危重记录项目");
			LinearLayout lin_item = (LinearLayout) view.findViewById(R.id.lin_item);
			for (Grave g : graves) {
				if (g.getType() == 0) {
					//生成文本框
					View lin_item_ev = LayoutInflater.from(this).inflate(R.layout.lin_item_ev, null);
					TextView tvTitle = (TextView) lin_item_ev.findViewById(R.id.tvTitle);
					EditText etSummary = (EditText) lin_item_ev.findViewById(R.id.etSummary);
					tvTitle.setText(g.getTitle());
					lin_item.addView(lin_item_ev);
				} else if (g.getType() == 3) {
					//生成单选框
				} else if (g.getType() == 4) {
					//生成复选框
				}
			}
			lin_record.addView(view);
			TextView tv = new TextView(this);
			tv.setHeight(10);
			lin_record.addView(tv);
		}
		
		if (texts.size() != 0) {
			View view = LayoutInflater.from(this).inflate(R.layout.item_bg, null);
			TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
			tv_title.setText("4)文字记录项目");
			LinearLayout lin_item = (LinearLayout) view.findViewById(R.id.lin_item);
			for (Text t : texts) {
				if (t.getType() == 0) {
					//生成文本框
					View lin_item_ev = LayoutInflater.from(this).inflate(R.layout.lin_item_ev, null);
					TextView tvTitle = (TextView) lin_item_ev.findViewById(R.id.tvTitle);
					EditText etSummary = (EditText) lin_item_ev.findViewById(R.id.etSummary);
					tvTitle.setText(t.getTitle());
					lin_item.addView(lin_item_ev);
				} else if (t.getType() == 3) {
					//生成单选框
				} else if (t.getType() == 4) {
					//生成复选框
				}
			}
			lin_record.addView(view);
			TextView tv = new TextView(this);
			tv.setHeight(10);
			lin_record.addView(tv);
		}
		
		if (additions.size() != 0) {
			int index = 0;
			View view = LayoutInflater.from(this).inflate(R.layout.item_bg, null);
			TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
			tv_title.setText("5)附加项目");
			LinearLayout lin_item = (LinearLayout) view.findViewById(R.id.lin_item);
			lin_item.removeAllViews();
			for (Addition a : additions) {
				System.out.println(a.getType() + "dddddddddddddddddd");
				if (a.getType() == 0) {
					//生成文本框
					View lin_item_ev = LayoutInflater.from(this).inflate(R.layout.lin_item_ev, null);
					TextView tvTitle = (TextView) lin_item_ev.findViewById(R.id.tvTitle);
					EditText etSummary = (EditText) lin_item_ev.findViewById(R.id.etSummary);
					tvTitle.setText(a.getTitle());
					lin_item.addView(lin_item_ev);
				} else if (a.getType() == 3) {
					//生成单选框
				} else if (a.getType() == 4) {
					//生成复选框
				}
			}
			lin_record.addView(view);
			TextView tv = new TextView(this);
			tv.setHeight(10);
			lin_record.addView(tv);
		}
    }
	
	private void initView() {
		lin_record = (LinearLayout) findViewById(R.id.lin_record);
		lin_record.removeAllViews();
		View view = LayoutInflater.from(this).inflate(R.layout.nurse_info, null);
		lin_record.addView(view);
	}
}
