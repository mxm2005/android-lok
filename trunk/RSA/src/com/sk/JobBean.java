package com.sk;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JobBean {
	private String paerationname;
	private String username;
	private String operationtime;
	private String uereip;
	private String operationdetil;
	private String operationresult;
	
	private Map<String,String> map;
	
	public static List<ValueBean> getList(){
		List<ValueBean> list = new ArrayList<ValueBean>();
		list.add(new ValueBean("getPaerationname", "操作名称 "));
		list.add(new ValueBean("getOperationresult", "操作结果"));
		list.add(new ValueBean("getUsername", "用户名称"));
		list.add(new ValueBean("getOperationtime", "操作时间"));
		list.add(new ValueBean("getUereip", "用户IP"));
		list.add(new ValueBean("getOperationdetil", "操作详情"));
		
		return list;
	}
	public String getPaerationname() {
		return paerationname;
	}
	public void setPaerationname(String paerationname) {
		this.paerationname = paerationname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getOperationtime() {
		return operationtime;
	}
	public void setOperationtime(String operationtime) {
		this.operationtime = operationtime;
	}
	public String getUereip() {
		return uereip;
	}
	public void setUereip(String uereip) {
		this.uereip = uereip;
	}
	public String getOperationdetil() {
		return operationdetil;
	}
	public void setOperationdetil(String operationdetil) {
		this.operationdetil = operationdetil;
	}
	public String getOperationresult() {
		return operationresult;
	}
	public void setOperationresult(String operationresult) {
		this.operationresult = operationresult;
	}
}