package com.sk.thread;
/**
 * synchronized只是锁定本身方法，像m1方法中的变量b还是可以被其他方法访问到的
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
	
	public void m2() throws Exception {//synchronized要一起锁定哦
		b = 2000;//如果在这里改变的话，那m1里印出来的b是被改变后的哦~
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
