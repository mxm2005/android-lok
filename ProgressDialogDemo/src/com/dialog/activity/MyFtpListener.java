package com.dialog.activity;

import it.sauronsoftware.ftp4j.FTPDataTransferListener;

public class MyFtpListener implements FTPDataTransferListener {
	private FTPOptType optType;

	public static MyFtpListener instance(FTPOptType optType) {
		return new MyFtpListener(optType);
	}

	private MyFtpListener(FTPOptType optType) {
		this.optType = optType;
	}

	public void started() {
		System.out.println(optType.getOptname() + "：FTP启动喽。。。。。。");
	}

	public void transferred(int length) {
		System.out.println(optType.getOptname() + "：FTP传输喽。。。。。。");

	}

	public void completed() {
		System.out.println(optType.getOptname() + "：FTP完成喽。。。。。。");
	}

	public void aborted() {
		System.out.println(optType.getOptname() + "：FTP中止喽。。。。。。");
	}

	public void failed() {
		System.out.println(optType.getOptname() + "：FTP挂掉喽。。。。。。");
	}

	@Override
	public void started(long totalSize) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void transferred(long length) {
		// TODO Auto-generated method stub
		
	}
}
