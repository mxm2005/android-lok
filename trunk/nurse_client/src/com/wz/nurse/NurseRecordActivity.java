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
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class NurseRecordActivity extends Activity {
	private LinearLayout lin_add, lin_text, lin_gra, lin_form, lin_cur;
	private List<Map<String, Object>> lists;
	private LayoutInflater layoutInflater;
	private Button btn_back, btn_comfrim;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nurse_record);
        initView();
        
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
				record.setMode(Integer.parseInt((String) lists.get(i).get("CBS07")));
				record.setAllowLength(Integer.parseInt((String)  lists.get(i).get("CBS08")));
				record.setPoint(Integer.parseInt((String)  lists.get(i).get("CBS09")));
				record.setUnit((String) lists.get(i).get("CBS10"));
				record.setType(Integer.parseInt((String)  lists.get(i).get("CBS11")));
				record.setRange((String) lists.get(i).get("CBS12"));
				record.setSenior((String) lists.get(i).get("CBS13"));
				record.setName((String) lists.get(i).get("CBS15"));
				records.add(record);
			}
			
			for (Record r : records) {
				if ("1)体温曲线项目".equals(r.getSenior())) {
					Curve curve = new Curve();
					curve.setTitle(r.getTitle());
					curve.setMode(r.getMode());
					curve.setUnit(r.getUnit());
					curve.setAllowLength(r.getAllowLength());
					curve.setPoint(r.getPoint());
					curve.setRange(r.getRange());
					curve.setType(r.getType());
					curve.setName(r.getName());
					curves.add(curve);
				} else if ("5)附加项目".equals(r.getSenior())) {
					Addition addition = new Addition();
					addition.setTitle(r.getTitle());
					addition.setMode(r.getMode());
					addition.setUnit(r.getUnit());
					addition.setAllowLength(r.getAllowLength());
					addition.setPoint(r.getPoint());
					addition.setRange(r.getRange());
					addition.setType(r.getType());
					addition.setName(r.getName());
					additions.add(addition);
				} else if ("2)体温表格项目".equals(r.getSenior())) {
					Form form = new Form();
					form.setTitle(r.getTitle());
					form.setMode(r.getMode());
					form.setUnit(r.getUnit());
					form.setAllowLength(r.getAllowLength());
					form.setPoint(r.getPoint());
					form.setRange(r.getRange());
					form.setType(r.getType());
					form.setName(r.getName());
					forms.add(form);
				} else if ("3)危重记录项目".equals(r.getSenior())) {
					Grave grave = new Grave();
					grave.setTitle(r.getTitle());
					grave.setMode(r.getMode());
					grave.setUnit(r.getUnit());
					grave.setAllowLength(r.getAllowLength());
					grave.setPoint(r.getPoint());
					grave.setRange(r.getRange());
					grave.setType(r.getType());
					grave.setName(r.getName());
					graves.add(grave);
				} else if ("4)文字记录项目".equals(r.getSenior())) {
					Text text = new Text();
					text.setTitle(r.getTitle());
					text.setMode(r.getMode());
					text.setUnit(r.getUnit());
					text.setAllowLength(r.getAllowLength());
					text.setPoint(r.getPoint());
					text.setRange(r.getRange());
					text.setType(r.getType());
					text.setName(r.getName());
					texts.add(text);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (curves.size() != 0) {
			lin_cur.setVisibility(View.VISIBLE);
			TextView tv_title = (TextView) lin_cur.findViewById(R.id.tv_title);
			tv_title.setText("1)体温曲线项目");
			LinearLayout lin_item = (LinearLayout) lin_cur.findViewById(R.id.lin_item4);
			for (Curve c : curves) {
				if ("体温".equals(c.getTitle())) {
					//部位+物理降温
					//（部位为选择：腋温+口温+肛温）（物理降温为checkbox）
					String[] m = {"腋温", "口温", "肛温"};
					ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,m);
					adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					View lin_item_sp = layoutInflater.inflate(R.layout.lin_item_sp, null);
					TextView tvTitle3 = (TextView) lin_item_sp.findViewById(R.id.tvTitle);
					tvTitle3.setText("部位");
					Spinner spSummary = (Spinner) lin_item_sp.findViewById(R.id.etSummary);
					spSummary.setAdapter(adapter);
					TextView tvTag = (TextView) lin_item_sp.findViewById(R.id.tvTag);
					tvTag.setText(m[0]);
					spListener(spSummary, tvTag, m);
					lin_item.addView(lin_item_sp);
					View lin_item_cb = layoutInflater.inflate(R.layout.lin_item_cb, null);
					TextView tvTitle = (TextView) lin_item_cb.findViewById(R.id.tvTitle);
					tvTitle.setText("物理降温");
					lin_item.addView(lin_item_cb);
					View lin_item_ev = LayoutInflater.from(this).inflate(R.layout.lin_item_ev, null);
					TextView tvTitle2 = (TextView) lin_item_ev.findViewById(R.id.tvTitle);
					tvTitle2.setTag(c.getName());
					EditText etSummary = (EditText) lin_item_ev.findViewById(R.id.etSummary);
					if ("".equals(c.getUnit().trim()) || c.getUnit().trim() == null) {
						tvTitle2.setText(c.getTitle());
					} else {
						tvTitle2.setText(c.getTitle() + "(" + c.getUnit() + ")");
					}
					if (c.getMode() == 1) {//输入类型为数字的时候
						etSummary.setTag(c.getRange());
						etSummary.setOnFocusChangeListener(focusListener);//值域
						setFilterByNumber(etSummary, c.getPoint(), c.getAllowLength());//设置小数位数，c.getPoint()为数字
					} else if (c.getMode() == 0) {
						setFilterByText(etSummary, c.getAllowLength());
					}
					lin_item.addView(lin_item_ev);
				} else if ("脉搏".equals(c.getTitle())) {
					//类型
					//（类型为选择：自然心率+起搏器+触不清+触不到+脉搏短拙）
					String[] m = {"自然心率", "起搏器温", "触不清温", "触不到", "脉搏短拙"};
					ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,m);
					adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					View lin_item_sp = layoutInflater.inflate(R.layout.lin_item_sp, null);
					TextView tvTitle3 = (TextView) lin_item_sp.findViewById(R.id.tvTitle);
					tvTitle3.setText("类型");
					Spinner spSummary = (Spinner) lin_item_sp.findViewById(R.id.etSummary);
					spSummary.setAdapter(adapter);
					TextView tvTag = (TextView) lin_item_sp.findViewById(R.id.tvTag);
					tvTag.setText(m[0]);
					spListener(spSummary, tvTag, m);
					lin_item.addView(lin_item_sp);
					View lin_item_ev = LayoutInflater.from(this).inflate(R.layout.lin_item_ev, null);
					TextView tvTitle2 = (TextView) lin_item_ev.findViewById(R.id.tvTitle);
					tvTitle2.setTag(c.getName());
					EditText etSummary = (EditText) lin_item_ev.findViewById(R.id.etSummary);
					if ("".equals(c.getUnit().trim()) || c.getUnit().trim() == null) {
						tvTitle2.setText(c.getTitle());
					} else {
						tvTitle2.setText(c.getTitle() + "(" + c.getUnit() + ")");
					}
					if (c.getMode() == 1) {//输入类型为数字的时候
						etSummary.setTag(c.getRange());
						etSummary.setOnFocusChangeListener(focusListener);//值域
						setFilterByNumber(etSummary, c.getPoint(), c.getAllowLength());//设置小数位数，c.getPoint()为数字
					} else if (c.getMode() == 0) {
						setFilterByText(etSummary, c.getAllowLength());
					}
					lin_item.addView(lin_item_ev);
				} else if ("呼吸".equals(c.getTitle())) {
					//呼吸机
					//（呼吸机为checkbox）
					View lin_item_cb = layoutInflater.inflate(R.layout.lin_item_cb, null);
					TextView tvTitle = (TextView) lin_item_cb.findViewById(R.id.tvTitle);
					tvTitle.setText("呼吸机");
					lin_item.addView(lin_item_cb);
					View lin_item_ev = LayoutInflater.from(this).inflate(R.layout.lin_item_ev, null);
					TextView tvTitle2 = (TextView) lin_item_ev.findViewById(R.id.tvTitle);
					tvTitle2.setTag(c.getName());
					EditText etSummary = (EditText) lin_item_ev.findViewById(R.id.etSummary);
					if ("".equals(c.getUnit().trim()) || c.getUnit().trim() == null) {
						tvTitle2.setText(c.getTitle());
					} else {
						tvTitle2.setText(c.getTitle() + "(" + c.getUnit() + ")");
					}
					if (c.getMode() == 1) {//输入类型为数字的时候
						etSummary.setTag(c.getRange());
						etSummary.setOnFocusChangeListener(focusListener);//值域
						setFilterByNumber(etSummary, c.getPoint(), c.getAllowLength());//设置小数位数，c.getPoint()为数字
					} else if (c.getMode() == 0) {
						setFilterByText(etSummary, c.getAllowLength());
					}
					lin_item.addView(lin_item_ev);
				} else {
					if (c.getType() == 0) {
						//生成文本框
						View lin_item_ev = LayoutInflater.from(this).inflate(R.layout.lin_item_ev, null);
						TextView tvTitle = (TextView) lin_item_ev.findViewById(R.id.tvTitle);
						tvTitle.setTag(c.getName());
						EditText etSummary = (EditText) lin_item_ev.findViewById(R.id.etSummary);
						if ("".equals(c.getUnit().trim()) || c.getUnit().trim() == null) {
							tvTitle.setText(c.getTitle());
						} else {
							tvTitle.setText(c.getTitle() + "(" + c.getUnit() + ")");
						}
						if (c.getMode() == 1) {//输入类型为数字的时候
							etSummary.setTag(c.getRange());
							etSummary.setOnFocusChangeListener(focusListener);//值域
							setFilterByNumber(etSummary, c.getPoint(), c.getAllowLength());//设置小数位数，c.getPoint()为数字
						} else if (c.getMode() == 0) {
							setFilterByText(etSummary, c.getAllowLength());
						}
						lin_item.addView(lin_item_ev);
					} else if (c.getType() == 3) {
						//生成单选框
					} else if (c.getType() == 4) {
						//生成复选框
					}
				}
			}
		}
		
		
		if (forms.size() != 0) {
			lin_form.setVisibility(View.VISIBLE);
			TextView tv_title = (TextView) lin_form.findViewById(R.id.tv_title);
			tv_title.setText("2)体温表格项目");
			LinearLayout lin_item = (LinearLayout) lin_form.findViewById(R.id.lin_item3);
			for (Form f : forms) {
				if ("小便(次)".equals(f.getTitle())) {
					//导尿
					//（导尿为checkbox）
					View lin_item_cb = layoutInflater.inflate(R.layout.lin_item_cb, null);
					TextView tvTitle = (TextView) lin_item_cb.findViewById(R.id.tvTitle);
					tvTitle.setText("导尿");
					lin_item.addView(lin_item_cb);
					View lin_item_ev = LayoutInflater.from(this).inflate(R.layout.lin_item_ev, null);
					TextView tvTitle2 = (TextView) lin_item_ev.findViewById(R.id.tvTitle);
					tvTitle2.setTag(f.getName());
					EditText etSummary = (EditText) lin_item_ev.findViewById(R.id.etSummary);
					if ("".equals(f.getUnit().trim()) || f.getUnit().trim() == null) {
						tvTitle2.setText(f.getTitle());
					} else {
						tvTitle2.setText(f.getTitle() + "(" + f.getUnit() + ")");
					}
					if (f.getMode() == 1) {//输入类型为数字的时候
						etSummary.setTag(f.getRange());
						etSummary.setOnFocusChangeListener(focusListener);//值域
						setFilterByNumber(etSummary, f.getPoint(), f.getAllowLength());//设置小数位数，c.getPoint()为数字
					} else if (f.getMode() == 0) {
						setFilterByText(etSummary, f.getAllowLength());
					}
					lin_item.addView(lin_item_ev);
				} else if ("大便(次)".equals(f.getTitle())) {
					//方式
					//（方式为选择：正常+灌肠+失禁+人工肛门） 
					String[] m = { "自正常", "灌肠", "失禁", "人工肛门" };
					ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,m);
					adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					View lin_item_sp = layoutInflater.inflate(R.layout.lin_item_sp, null);
					TextView tvTitle3 = (TextView) lin_item_sp.findViewById(R.id.tvTitle);
					tvTitle3.setText("方式");
					Spinner spSummary = (Spinner) lin_item_sp.findViewById(R.id.etSummary);
					spSummary.setAdapter(adapter);
					TextView tvTag = (TextView) lin_item_sp.findViewById(R.id.tvTag);
					tvTag.setText(m[0]);
					spListener(spSummary, tvTag, m);
					lin_item.addView(lin_item_sp);
					View lin_item_ev = LayoutInflater.from(this).inflate(R.layout.lin_item_ev, null);
					TextView tvTitle2 = (TextView) lin_item_ev.findViewById(R.id.tvTitle);
					tvTitle2.setTag(f.getName());
					EditText etSummary = (EditText) lin_item_ev.findViewById(R.id.etSummary);
					if ("".equals(f.getUnit().trim()) || f.getUnit().trim() == null) {
						tvTitle2.setText(f.getTitle());
					} else {
						tvTitle2.setText(f.getTitle() + "(" + f.getUnit() + ")");
					}
					if (f.getMode() == 1) {//输入类型为数字的时候
						etSummary.setTag(f.getRange());
						etSummary.setOnFocusChangeListener(focusListener);//值域
						setFilterByNumber(etSummary, f.getPoint(), f.getAllowLength());//设置小数位数，c.getPoint()为数字
					} else if (f.getMode() == 0) {
						setFilterByText(etSummary, f.getAllowLength());
					}
					lin_item.addView(lin_item_ev);
				} else {
					if (f.getType() == 0) {
						//生成文本框
						View lin_item_ev = LayoutInflater.from(this).inflate(R.layout.lin_item_ev, null);
						TextView tvTitle = (TextView) lin_item_ev.findViewById(R.id.tvTitle);
						tvTitle.setTag(f.getName());
						EditText etSummary = (EditText) lin_item_ev.findViewById(R.id.etSummary);
						if ("".equals(f.getUnit().trim()) || f.getUnit().trim() == null) {
							tvTitle.setText(f.getTitle());
						} else {
							tvTitle.setText(f.getTitle() + "(" + f.getUnit() + ")");
						}
						if (f.getMode() == 1) {//输入类型为数字的时候
							etSummary.setTag(f.getRange());
							etSummary.setOnFocusChangeListener(focusListener);//值域
							setFilterByNumber(etSummary, f.getPoint(), f.getAllowLength());//设置小数位数，c.getPoint()为数字
						} else if (f.getMode() == 0) {
							setFilterByText(etSummary, f.getAllowLength());
						}
						lin_item.addView(lin_item_ev);
					} else if (f.getType() == 3) {
						//生成单选框
						
					} else if (f.getType() == 4) {
						//生成复选框
					}
				}
			}
		}
		
		if (graves.size() != 0) {
			lin_gra.setVisibility(View.VISIBLE);
			TextView tv_title = (TextView) lin_gra.findViewById(R.id.tv_title);
			tv_title.setText("3)危重记录项目");
			LinearLayout lin_item = (LinearLayout) lin_gra.findViewById(R.id.lin_item2);
			for (Grave g : graves) {
				if (g.getType() == 0) {
					//生成文本框
					View lin_item_ev = LayoutInflater.from(this).inflate(R.layout.lin_item_ev, null);
					TextView tvTitle = (TextView) lin_item_ev.findViewById(R.id.tvTitle);
					tvTitle.setTag(g.getName());
					EditText etSummary = (EditText) lin_item_ev.findViewById(R.id.etSummary);
					if ("".equals(g.getUnit().trim()) || g.getUnit().trim() == null) {
						tvTitle.setText(g.getTitle());
					} else {
						tvTitle.setText(g.getTitle() + "(" + g.getUnit() + ")");
					}
					if (g.getMode() == 1) {//输入类型为数字的时候
						etSummary.setTag(g.getRange());
						etSummary.setOnFocusChangeListener(focusListener);//值域
						setFilterByNumber(etSummary, g.getPoint(), g.getAllowLength());//设置小数位数，c.getPoint()为数字
					} else if (g.getMode() == 0) {
						setFilterByText(etSummary, g.getAllowLength());
					}
					lin_item.addView(lin_item_ev);
				} else if (g.getType() == 3) {//单选框有值域，CBS12
					//生成单选框
//					String[] m = { "√清楚", "+朦胧", "+嗜睡", "+谵妄", "++半昏迷", "+++昏迷" };
					String range = g.getRange();
					String[] m = range.split(";");
					ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,m);
					adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					View lin_item_sp = layoutInflater.inflate(R.layout.lin_item_sp, null);
					TextView tvTitle3 = (TextView) lin_item_sp.findViewById(R.id.tvTitle);
					tvTitle3.setTag(g.getName());
					if ("".equals(g.getUnit().trim()) || g.getUnit().trim() == null) {
						tvTitle3.setText(g.getTitle());
					} else {
						tvTitle3.setText(g.getTitle() + "(" + g.getUnit() + ")");
					}
					Spinner spSummary = (Spinner) lin_item_sp.findViewById(R.id.etSummary);
					spSummary.setAdapter(adapter);
					TextView tvTag = (TextView) lin_item_sp.findViewById(R.id.tvTag);
					tvTag.setText(m[0]);
					spListener(spSummary, tvTag, m);
					lin_item.addView(lin_item_sp);
				} else if (g.getType() == 4) {
					//生成复选框
				}
			}
		}
		
		if (texts.size() != 0) {
			lin_text.setVisibility(View.VISIBLE);
			TextView tv_title = (TextView) lin_text.findViewById(R.id.tv_title);
			tv_title.setText("4)文字记录项目");
			LinearLayout lin_item = (LinearLayout) lin_text.findViewById(R.id.lin_item1);
			for (Text t : texts) {
				if (t.getType() == 0) {
					//生成文本框
					LinearLayout lin_item_ev = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.lin_item_ev, null);
					TextView tvTitle = (TextView) lin_item_ev.findViewById(R.id.tvTitle);
					tvTitle.setTag(t.getName());
					EditText etSummary = (EditText) lin_item_ev.findViewById(R.id.etSummary);
					if ("".equals(t.getUnit().trim()) || t.getUnit().trim() == null) {
						tvTitle.setText(t.getTitle());
					} else {
						tvTitle.setText(t.getTitle() + "(" + t.getUnit() + ")");
					}
					if (t.getMode() == 1) {//输入类型为数字的时候
						etSummary.setTag(t.getRange());
						etSummary.setOnFocusChangeListener(focusListener);//值域
						setFilterByNumber(etSummary, t.getPoint(), t.getAllowLength());//设置小数位数，c.getPoint()为数字
					} else if (t.getMode() == 0) {
						setFilterByText(etSummary, t.getAllowLength());
					}
					lin_item.addView(lin_item_ev);
				} else if (t.getType() == 3) {
					//生成单选框
				} else if (t.getType() == 4) {
					//生成复选框
				}
			}
		}
		
		if (additions.size() != 0) {
			lin_add.setVisibility(View.VISIBLE);
			TextView tv_title = (TextView) lin_add.findViewById(R.id.tv_title);
			tv_title.setText("5)附加项目");
			LinearLayout lin_item = (LinearLayout) lin_add.findViewById(R.id.lin_item);
			for (Addition a : additions) {
				if (a.getType() == 0) {
					//生成文本框
					LinearLayout lin_item_ev= (LinearLayout) layoutInflater.inflate(R.layout.lin_item_ev, null);
					TextView tvTitle = (TextView) lin_item_ev.findViewById(R.id.tvTitle);
					tvTitle.setTag(a.getName());
					EditText etSummary = (EditText) lin_item_ev.findViewById(R.id.etSummary);
					if ("".equals(a.getUnit().trim()) || a.getUnit().trim() == null) {
						tvTitle.setText(a.getTitle());
					} else {
						tvTitle.setText(a.getTitle() + "(" + a.getUnit() + ")");
					}
					if (a.getMode() == 1) {//输入类型为数字的时候
						etSummary.setTag(a.getRange());
						etSummary.setOnFocusChangeListener(focusListener);//值域
						setFilterByNumber(etSummary, a.getPoint(), a.getAllowLength());//设置小数位数，c.getPoint()为数字
					} else if (a.getMode() == 0) {
						setFilterByText(etSummary, a.getAllowLength());
					}
					lin_item.addView(lin_item_ev);
				} else if (a.getType() == 3) {
					//生成单选框
				} else if (a.getType() == 4) {
					//生成复选框
				}
			}
		}
    }
	
	private OnClickListener buttonListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.btn_comfrim:
				if (lin_cur.isShown()) getDate(lin_cur);
				if (lin_form.isShown()) getDate(lin_form);
				if (lin_gra.isShown()) getDate(lin_gra);
				if (lin_text.isShown()) getDate(lin_text);
				if (lin_add.isShown()) getDate(lin_add);
				break;
			case R.id.btn_back:
				NurseRecordActivity.this.setResult(RESULT_OK);
				NurseRecordActivity.this.finish();
				break;
			}
		}
	};
	
	/**
	 * 读取护理记录填入内容
	 * @param lin 每个项目的父LinearLayout
	 */
	private void getDate (LinearLayout lin) {
		LinearLayout childLin = (LinearLayout) lin.getChildAt(0);
		TextView tv_title = (TextView) childLin.findViewById(R.id.tv_title);
		System.out.println(tv_title.getText().toString() + "pppppppppp标题");
		LinearLayout childLin2 = (LinearLayout) lin.getChildAt(1);
		int count = childLin2.getChildCount();
		for (int i = 0; i < count; i++) {
			LinearLayout gsLin = (LinearLayout) childLin2.getChildAt(i);
			TextView tv = (TextView) gsLin.findViewById(R.id.tvTitle);
			View view = gsLin.findViewById(R.id.etSummary);
			if (view instanceof EditText) {
				System.out.println(tv.getText().toString() + "pppppppppppppppp" + ((EditText)view).getText().toString() + "CBS15:" + (String)tv.getTag());
			} else if (view instanceof CheckBox) {
				System.out.println(tv.getText().toString() + "pppppppppppppppp" + ((CheckBox)view).isChecked() + "CBS15:" + (String)tv.getTag());
			} else if (view instanceof Spinner) {
				TextView tvTag = (TextView) gsLin.findViewById(R.id.tvTag);
				System.out.println(tv.getText().toString() + "pppppppppppppppp" + tvTag.getText().toString() + "CBS15:" + (String)tv.getTag());
			}
		}
	}
	
	/**
	 * 监听下拉选择控件 spinner
	 * @param sp
	 * @param tv 预存textview
	 * @param str spinner的内容数组
	 */
	private void spListener(Spinner sp, final TextView tv, final String[]str) {
		sp.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0,
							View arg1, int arg2, long arg3) {
						// TODO Auto-generated method stub
						tv.setText(str[arg2]);
					}
					@Override
					public void onNothingSelected(
							AdapterView<?> arg0) {
						// TODO Auto-generated method stub
					}
				});
	}
	
	int min;//值域最小
	int max;//值域最大
	
	/**
	 * editText监听器
	 */
	private View.OnFocusChangeListener focusListener = new OnFocusChangeListener() {
		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			// TODO Auto-generated method stub
			if (hasFocus) {// 获得焦点
				// 在这里可以对获得焦点进行处理
				String range = (String) ((EditText)v).getTag();
				String[] str = range.split(";");
				try {
					((EditText)v).setRawInputType(InputType.TYPE_CLASS_NUMBER);//设为数字键盘
					min = Integer.parseInt(str[0]);
					max = Integer.parseInt(str[1]);
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("range:" + range);
			} else {// 失去焦点
				try {
					if ((Float.parseFloat(((EditText)v).getText().toString()) > max) || (Float.parseFloat(((EditText)v).getText().toString()) < min)) {
						Toast.makeText(NurseRecordActivity.this, "请输入" + min + "-" + max + "范围的数据", Toast.LENGTH_SHORT).show();
						((EditText)v).setText("");
					}
				} catch (Exception e) {
					((EditText)v).setText("");
					e.printStackTrace();
				}
			}
		}
	};
	
	InputFilter[] input = new InputFilter[2];//数字editText过滤器
	InputFilter[] input2 = new InputFilter[1];//文本 editText过滤器
	
	//数字过滤
	private void setFilterByNumber(EditText et, final int count, int length) {
		input[0] = new InputFilter() {
			@Override
			public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
				// TODO Auto-generated method stub
				// 删除等特殊字符，直接返回
				if ("".equals(source.toString())) {
					return null;
				}
				String dValue = dest.toString();
				String[] splitArray = dValue.split("\\.");
				if (splitArray.length > 1) {
					String dotValue = splitArray[1];
					int diff = dotValue.length() + 1 - count;/* 小数长度; */
					if (diff > 0) {
						return source.subSequence(start, end - diff);
					}
				}
				return null;
			}
		};
		input[1] = new InputFilter.LengthFilter(length);
		et.setFilters(input);
	}
	
	//文本过滤，只控件文本输入长度
	private void setFilterByText(EditText et, int length) {
		input2[0] = new InputFilter.LengthFilter(length);
		et.setFilters(input2);
	}
	
	private void initView() {
		layoutInflater = getLayoutInflater();
		lin_add = (LinearLayout) findViewById(R.id.lin_add);
		lin_text = (LinearLayout) findViewById(R.id.lin_text);
		lin_gra = (LinearLayout) findViewById(R.id.lin_gra);
		lin_form = (LinearLayout) findViewById(R.id.lin_form);
		lin_cur = (LinearLayout) findViewById(R.id.lin_cur);
		btn_back = (Button) findViewById(R.id.btn_back);
		btn_back.setOnClickListener(buttonListener);
		btn_comfrim = (Button) findViewById(R.id.btn_comfrim);
		btn_comfrim.setOnClickListener(buttonListener);
	}
}
