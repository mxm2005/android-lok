package com.wz.nurse.bean;

public class Record {
	private String title;//标题							ooooooooooo
	private int allowLength;// 表示允许输入文本的长度		ooooooooooo
	private int point;// 表示文本的小数位数;				xxxx
	private String unit;// 单位                         				xxxx
	private int type;// 0文本，3单选 ，4复选			    ooooooooooo
	private String range;// 值域							lllllllllllllllllllllllllllllllllllllllllll
	private String senior;// 所属总类					ooooooooooo

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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