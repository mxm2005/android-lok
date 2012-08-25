package com.test;

public class Test {
	static int[] bits = new int[] { 1, 2, 3, 4, 5 };

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		sort("", bits);
	}

	private static void sort(String prefix, int[] a) {
		if (a.length == 1) {
			System.out.println(prefix + a[0]);
		}

		for (int i = 0; i < a.length; i++) {
			sort(prefix + a[i], copy(a, i));
		}
	}

	private static int[] copy(int[] a, int index) {
		int[] b = new int[a.length - 1];
		System.arraycopy(a, 0, b, 0, index);
		System.arraycopy(a, index + 1, b, index, a.length - index - 1);
		return b;
	}
}
