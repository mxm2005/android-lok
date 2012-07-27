package com.wz.nurse.bean;

import android.content.Intent;
/**
 * 底部选项卡bean
 * @author Administrator
 *
 */
public class TabItem {
	private String title;		// tab名字
	private int icon;			// tab图片
	private int bg;			// tab背景
	private Intent intent;	// tab跳转intent
	
	public TabItem(String title, int icon, int bg, Intent intent) {
		super();
		this.title = title;
		this.icon = icon;
		this.bg = bg;
		this.intent = intent;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getIcon() {
		return icon;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}

	public int getBg() {
		return bg;
	}

	public void setBg(int bg) {
		this.bg = bg;
	}

	public Intent getIntent() {
		return intent;
	}

	public void setIntent(Intent intent) {
		this.intent = intent;
	}
}
