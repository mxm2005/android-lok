package com.wz.nurse.bean;

/**
 * 共同属性
 * 
 * @author yjh
 * 
 */
public class Share {
	protected String title;// 标题 ooooooooooo
	private int mode;// 0为文本 1为数字
	protected int allowLength;// 表示允许输入文本的长度 ooooooooooo
	protected int point;// 表示文本的小数位数; xxxx
	protected String unit;// 单位 xxxx
	protected int type;// 0文本，3单选 ，4复选 ooooooooooo
	protected String range;// 值域 lllllllllllllllllllllllllllllllllllllllllll
	protected String name;// CBS15 字段名

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
