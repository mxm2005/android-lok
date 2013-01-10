package com.wz.doctor.bean;

public class Record {
	private int id;
	private String name;
	private int lenght;
	private String date;

	public Record() {
	}
	
	public Record(int id, String name)	{
		this.id = id;
		this.name = name;
	}

	public Record(String name, int lenght, String date) {
		this.name = name;
		this.lenght = lenght;
		this.date = date;
	}

	public Record(int id, String name, int lenght, String date) {
		this.id = id;
		this.name = name;
		this.lenght = lenght;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLenght() {
		return lenght;
	}

	public void setLenght(int lenght) {
		this.lenght = lenght;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
