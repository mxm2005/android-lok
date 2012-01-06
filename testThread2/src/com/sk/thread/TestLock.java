package com.sk.thread;
/**
 * synchronizedֻ����������������m1�����еı���b���ǿ��Ա������������ʵ���
 * @author Administrator
 *
 */
public class TestLock implements Runnable {
	int b = 100;
	
	public synchronized void m1() throws Exception {
		b = 1000;
		Thread.sleep(5000);
		System.out.println("b=" + b);
	}
	
	public void m2() throws Exception {//synchronizedҪһ������Ŷ
		b = 2000;//���������ı�Ļ�����m1��ӡ������b�Ǳ��ı���Ŷ~
		System.out.println(b);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			m1();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		TestLock tl = new TestLock();
		Thread t = new Thread(tl);
		t.start();
		Thread.sleep(1000);
		tl.m2();
	}

}
