package com.test.ari;

/**
 * 优先队列的实现
 * @author sunnyykn
 */
class PriorityQ {
	// array in sorted order,from max at 0 to min at size - 1
	private int maxSize;
	private long[] queArray;
	private int nItems;

	public PriorityQ(int s) {
		maxSize = s;
		queArray = new long[maxSize];
		nItems = 0;
	}

	public void insert(long item) {
		int j;
		if (nItems == 0)
			queArray[nItems++] = item;
		else {
			for (j = nItems - 1; j >= 0; j--) {
				if (item > queArray[j])
					queArray[j + 1] = queArray[j];
				else
					break;
			}
			queArray[j + 1] = item;
			nItems++;
		}
	}

	public long remove() {
		return queArray[--nItems];
	}

	public long peekMin() {
		return queArray[nItems - 1];
	}

	public boolean isEmpty() {
		return (nItems == 0);
	}

	public boolean isFull() {
		return (nItems == maxSize);
	}
}

class PriorityQApp {
	public static void main(String[] args) {
		PriorityQ thePQ = new PriorityQ(5);
		thePQ.insert(30);
		thePQ.insert(50);
		thePQ.insert(10);
		thePQ.insert(40);
		thePQ.insert(20);
		while (!thePQ.isEmpty()) {
			long item = thePQ.remove();
			System.out.print(item + " ");
		}
		System.out.println("");
	}
}