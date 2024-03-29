package com.test.ari;

/**
 * 快速排序  优化（QuickSort）
 * @author sunnyykn
 */
class ArrayIns3 {
	private long[] theArray; // ref to array theArray
	private int nElems; // number of data items

	public ArrayIns3(int max) // constructor
	{
		theArray = new long[max]; // create the array
		nElems = 0; // no items yet
	}

	public void insert(long value) // put element into array
	{
		theArray[nElems] = value; // insert it
		nElems++; // increment size
	}

	public void display() {
		System.out.print("A = ");
		for (int j = 0; j < nElems; j++)
			System.out.print(theArray[j] + " ");
		System.out.println("");
	}

	public void quickSort() {
		recQuickSort(0, nElems - 1);
	}

	public void recQuickSort(int left, int right) {
		int size = right - left + 1;
		if (size < 10) // insertion sort if small
			insertionSort(left, right);
		else // quicksort if large
		{
			long median = medianOf3(left, right);
			int partition = partitionIt(left, right, median);
			recQuickSort(left, partition - 1);
			recQuickSort(partition + 1, right);
		}
	}

	public long medianOf3(int left, int right) {
		int center = (left + right) / 2;
		if (theArray[left] > theArray[center]) // order left & center
			swap(left, right);
		if (theArray[left] > theArray[right]) // order left & right
			swap(left, right);
		if (theArray[center] > theArray[right]) // order center & right
			swap(center, right);
		swap(center, right - 1);
		return theArray[right - 1];
	}

	public void swap(int dex1, int dex2) {
		long temp = theArray[dex1];
		theArray[dex1] = theArray[dex2];
		theArray[dex2] = temp;
	}

	public int partitionIt(int left, int right, long pivot) {
		int leftPtr = left;
		int rightPtr = right;
		while (true) {
			while (theArray[++leftPtr] < pivot)
				;
			while (theArray[--rightPtr] > pivot)
				;
			if (leftPtr >= rightPtr)
				break;
			else
				swap(leftPtr, rightPtr);
		}
		swap(leftPtr, right - 1);
		return leftPtr;
	}

	public void insertionSort(int left, int right) {
		int in, out;
		for (out = left + 1; out <= right; out++) {
			long temp = theArray[out];
			in = out;
			while (in > left && theArray[in - 1] >= temp) {
				theArray[in] = theArray[in - 1];
				--in;
			}
			theArray[in] = temp;
		}
	}
}

class QuickSortApp2 {
	public static void main(String[] args) {
		int maxSize = 50; // array size
		ArrayIns3 arr; // reference to array
		arr = new ArrayIns3(maxSize); // create the array
		for (int j = 0; j < maxSize; j++) // fill array with
		{ // random numbers
			long n = (int) (java.lang.Math.random() * 99);
			arr.insert(n);
		}
		arr.display(); // display items
		arr.quickSort(); // quicksort them
		arr.display(); // display them again
	}
}