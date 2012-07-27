package com.wz.nurse.bean;

public class Record {
	private String title;
	private int type;// 1为输入框，0为下拉框
	private int allowLength;// 表示允许输入文本的长度
	private int point;// 表示文本的小数位数;
	private String unit;// 单位
	private int type2;
	private String range;// 值域
	private String senior;// 所属总类

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getAllowLength() {
		return allowLength;
	}

	public void setAllowLength(int allowLength) {
		this.allowLength = allowLength;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public int getType2() {
		return type2;
	}

	public void setType2(int type2) {
		this.type2 = type2;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	public String getSenior() {
		return senior;
	}

	public void setSenior(String senior) {
		this.senior = senior;
	}

}
