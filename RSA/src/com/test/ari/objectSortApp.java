package com.test.ari;

/**
 * 对象排序
 * @author sunnyykn
 */
class Person {
	private String lastName;
	private String firstName;
	private int age;

	// ------------------------------------
	public Person(String last, String first, int a) // constructor
	{
		lastName = last;
		firstName = first;
		age = a;
	}

	// ---------------------------------------
	public void displayPerson() {
		System.out.print("  Last name:" + lastName);
		System.out.print(",First name:" + firstName);
		System.out.println(",Age:" + age);
	}

	// -------------------------------------------
	public String getLast() {
		return lastName;
	}
}// end class Person
// ////////////////////////////////////////////////////////////

class ArrayInOb {
	private Person[] a;
	private int nElems;

	// ----------------------------------------
	public ArrayInOb(int max) {
		a = new Person[max];
		nElems = 0;
	}

	// -------------------------------------------
	public void insert(String last, String first, int age) {
		a[nElems] = new Person(last, first, age);
		nElems++;
	}

	// -------------------------------------------
	public void display() {
		for (int i = 0; i < nElems; i++) {
			a[i].displayPerson();
		}
		System.out.println("");
	}

	// ----------------------------------------------
	public void insertionSort() {
		int in, out;
		for (out = 1; out < nElems; out++) {
			Person temp = a[out];
			in = out;
			while (in > 0 && a[in - 1].getLast().compareTo(temp.getLast()) > 0) {
				a[in] = a[in - 1];
				--in;
			}
			a[in] = temp;
		}// end for
	}// end insertionSort
}// end class ArrayInOb
// //////////////////////////////////////////////////////////

class objectSortApp {
	public static void main(String[] args) {
		int maxSize = 100;
		ArrayInOb arr;
		arr = new ArrayInOb(maxSize);
		arr.insert("Evans", "Patty", 24);
		arr.insert("Smith", "Doc", 59);
		arr.insert("Smith", "Lorraine", 37);
		arr.insert("Smith", "Paul", 37);
		arr.insert("Yee", "Tom", 43);
		arr.insert("Hashimoto", "Sato", 21);
		arr.insert("Stimson", "Henry", 29);
		arr.insert("Velasquez", "Jose", 72);
		arr.insert("Vang", "Minh", 22);
		arr.insert("Creswell", "Lucinda", 18);
		System.out.println("Before sorting:");
		arr.display();
		arr.insertionSort();
		System.out.println("After sorting:");
		arr.display();
	}// end main()
}// end class ObjectSortApp