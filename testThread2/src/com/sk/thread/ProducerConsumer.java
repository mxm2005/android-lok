package com.sk.thread;
/**
 * 生产者，消费者问题
 * @author Administrator
 *
 */
public class ProducerConsumer {
	public static void main(String[] args) {
		SyncStack ss = new SyncStack();
		Producer p = new Producer(ss);
		Consumer c = new Consumer(ss);
		new Thread(p).start();
		new Thread(c).start();
	}
}

class WoTou {
	int id;
	WoTou(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "wotou : " + id;
	}
}

class SyncStack {
	int index = 0;
	WoTou[]arrWT = new WoTou[6];
	public synchronized void push(WoTou wt) {
		while (index == arrWT.length) {
			try {
				this.wait();//当前在 SyncStack类中访问push方法的线程 wait，调用wait方法的前提是push方法必须在synchronized锁定的前提下
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.notifyAll();//跟wait对应，叫醒被wait的对象
		arrWT[index] = wt;
		index++;
	}
	
	public synchronized WoTou pop() {
		while (index == 0) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.notifyAll();
		index--;
		return arrWT[index];
	}
}

class Producer implements Runnable {
	SyncStack ss = null;
	
	Producer(SyncStack ss) {
		this.ss = ss;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 20; i++) {
			WoTou wt = new WoTou(i);
			ss.push(wt);
			System.out.println("生产：" + wt);
			try {
				Thread.sleep((int)(Math.random() * 2));
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
	}
	
}

class Consumer implements Runnable {
	SyncStack ss = null;
	
	Consumer(SyncStack ss) {
		this.ss = ss;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 20; i++) {
			WoTou wt = ss.pop();
			System.out.println("消费了：" + wt);
			try {
				Thread.sleep((int)(Math.random() * 1000));
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
	}
	
}
