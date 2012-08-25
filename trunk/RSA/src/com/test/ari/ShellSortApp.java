package com.test.ari;

/**
 * 希尔排序实现
 * @author sunnyykn
 */
class ArraySh {
	private long[] theArray; // ref to array theArray
	private int nElems; // number of data items

	public ArraySh(int max) // constructor
	{
		theArray = new long[max]; // create the array
		nElems = 0; // no items yet
	}

	public void insert(long value) // put element into array
	{
		theArray[nElems] = value; // insert it
		nElems++; // increment size
	}

	public void display() // displays array contents
	{
		System.out.print("A = ");
		for (int j = 0; j < nElems; j++)
			System.out.print(theArray[j] + " ");
		System.out.println("");
	}

	public void shellSort() {
		int inner, outer;
		long temp;
		int h = 1;
		while (h <= nElems / 3)
			h = h * 3 + 1; // (1,4,13,40,121...)
		while (h > 0) {
			for (outer = h; outer < nElems; outer++) {
				temp = theArray[outer];
				inner = outer;
				while (inner > h - 1 && theArray[inner - h] >= temp) {
					theArray[inner] = theArray[inner - h];
					inner -= h;
				}
				theArray[inner] = temp;
			}
			h = (h - 1) / 3;
		}
	}
}

class ShellSortApp {
	public static void main(String[] args) {
		int maxSize = 100;
		ArraySh arr;
		arr = new ArraySh(maxSize);
		for (int j = 0; j < maxSize; j++) {
			long n = (int) (java.lang.Math.random() * 99);
			arr.insert(n);
		}
		arr.display();
		arr.shellSort();
		arr.display();
	}
}
