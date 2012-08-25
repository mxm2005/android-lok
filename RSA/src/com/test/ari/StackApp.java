package com.test.ari;

/**
 * 栈的创建 入栈出栈
 * @author sunnyykn
 */
class StackX {
	private int maxSize;
	private long[] stackArray;
	private int top;

	public StackX(int s) {
		maxSize = s;
		stackArray = new long[maxSize];
		top = -1;
	}

	public void push(long j) // put item on top of stack
	{
		stackArray[++top] = j; // increment top,insert item
	}

	public long pop() // take item from top of stack
	{
		return stackArray[top--]; // access item,decrement top
	}

	public long peek() // peek at top of stack
	{
		return stackArray[top];
	}

	public boolean isEmpty() // true if stack is empty
	{
		return (top == -1);
	}

	public boolean isFull() // true if stack is full
	{
		return (top == maxSize - 1);
	}
}// end class StackX

class StackApp {
	public static void main(String[] args) {
		StackX theStack = new StackX(10);
		theStack.push(20);
		theStack.push(40);
		theStack.push(60);
		theStack.push(80);
		while (!theStack.isEmpty()) // until it's empty,delete item from stack
		{
			long value = theStack.pop();
			System.out.println(value);
		}
		System.out.println("");
	}
}