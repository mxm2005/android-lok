package com.sk.test;

public class Gues {
	private static long num = 100;
	private static long x1 = 10, x2 = 20;
	private static boolean flag = true;
//	static double s = Math.random();
	
	public synchronized static void main(String[] args) {
		final double s = Math.random();
//		x1 = num;
		new Thread() {
			public void run() {
				while (true) {
//					if (s > 100) {
//						num++;
//					} else {
//						num--;
//					}
					num++;
//					System.out.println("num: " + num);
					if (flag) {
//						x1 = x1;
						x1 = x2;
						flag = false;
//						System.out.println("过程1" + "x1: " + x1 + "\n" + "x2: " + x2);
					} else {
						x2 = num;
						flag = true;
//						System.out.println("过程2" + "x1: " + x1 + "\n" + "x2: " + x2);
					}
				}
			};
		}.start();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("x1: " + x1 + "\n" + "x2: " + x2);
	}
}
