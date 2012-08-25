package com.test.ari;

/**
 * 队列的创建 入队出队
 * @author sunnyykn
 */
class Queue {
	private int maxSize;
	private long[] queArray;
	private int front;
	private int rear;
	private int nItems;

	public Queue(int s) {
		maxSize = s;
		queArray = new long[maxSize];
		front = 0;
		rear = -1;
		nItems = 0;
	}

	public void insert(long que) {
		if (rear == maxSize - 1)
			rear = -1;
		queArray[++rear] = que;
		nItems++;
	}

	public long remove() {
		long temp = queArray[front++];
		if (front == maxSize)
			front = 0;
		nItems--;
		return temp;
	}

	public long peekFront() {
		return queArray[front];
	}

	public boolean isEmpty() {
		return (nItems == 0);
	}

	public boolean isFull() {
		return (nItems == maxSize);
	}

	public int size() {
		return nItems;
	}
}// end class Queue

class QueueApp {
	public static void main(String[] args) {
		Queue theQueue = new Queue(8);
		theQueue.insert(10);
		theQueue.insert(20);
		theQueue.insert(30);
		theQueue.insert(40);
		theQueue.remove();
		theQueue.remove();
		theQueue.insert(50);
		theQueue.insert(60);
		theQueue.insert(70);
		theQueue.insert(80);
		while (!theQueue.isEmpty()) {
			long n = theQueue.remove();
			System.out.print(n);
			System.out.print(" ");
		}
		System.out.println("");
	}
}// end class QueueApp