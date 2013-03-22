package com.softel.poet.bean;

public class ObjMap
{
	int drawable;
	String type;
	int title;
	int chapter;

	public ObjMap(int drawable, String type, int title, int chapter)
	{
		this.drawable = drawable;
		this.type = type;
		this.title = title;
		this.chapter = chapter;
	}

	public int getDrawable()
	{
		return drawable;
	}

	public void setDrawable(int drawable)
	{
		this.drawable = drawable;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public int getTitle()
	{
		return title;
	}

	public void setTitle(int title)
	{
		this.title = title;
	}

	public int getChapter()
	{
		return chapter;
	}

	public void setChapter(int chapter)
	{
		this.chapter = chapter;
	}

}
