package com.test.ari;

/**
 * 2-3-4 树 Java数据结构与算法 
 * @author sunnyykn
 */
import java.io.*;

class DataItem {
	public long dData; // one data item

	public DataItem(long dd) {
		dData = dd;
	}

	public void displayItem() {
		System.out.print("/" + dData);
	}
}

class Node2 {
	private static final int ORDER = 4;
	private int numItems;
	private Node2 parent;
	private Node2 childArray[] = new Node2[ORDER];
	private DataItem itemArray[] = new DataItem[ORDER - 1];

	public void connectChild(int childNum, Node2 child) {
		childArray[childNum] = child;
		if (child != null) {
			child.parent = this;
		}
	}

	public Node2 disconnectChild(int childNum) {
		Node2 tempNode = childArray[childNum];
		childArray[childNum] = null;
		return tempNode;
	}

	public Node2 getChild(int childNum) {
		return childArray[childNum];
	}

	public Node2 getParent() {
		return parent;
	}

	public boolean isLeaf() {
		return (childArray[0] == null) ? true : false;
	}

	public int getNumItems() {
		return numItems;
	}

	public DataItem getItem(int index) {
		return itemArray[index];
	}

	public boolean isFull() {
		return (numItems == ORDER - 1) ? true : false;
	}

	public int findItem(long key) {
		for (int j = 0; j < ORDER - 1; j++) {
			if (itemArray[j] == null)
				break;
			else if (itemArray[j].dData == key)
				return j;
		}
		return -1;
	}

	public int insertItem(DataItem newItem) {
		numItems++;
		long newKey = newItem.dData;
		for (int j = ORDER - 2; j >= 0; j--) {
			if (itemArray[j] == null)
				continue;
			else {
				long itsKey = itemArray[j].dData;
				if (newKey < itsKey)
					itemArray[j + 1] = itemArray[j];
				else {
					itemArray[j + 1] = newItem;
					return j + 1;
				}
			}
		}
		itemArray[0] = newItem;
		return 0;
	}

	public DataItem removeItem() {
		DataItem temp = itemArray[numItems - 1];
		itemArray[numItems - 1] = null;
		numItems--;
		return temp;
	}

	public void displayNode() {
		for (int j = 0; j < numItems; j++)
			itemArray[j].displayItem();
		System.out.println("/");
	}
}// end class Node2

class Tree234 {
	private Node2 root = new Node2();

	public int find(long key) {
		Node2 curNode = root;
		int childNumber;
		while (true) {
			if ((childNumber = curNode.findItem(key)) != -1)
				return childNumber;
			else if (curNode.isLeaf())
				return -1;
			else
				curNode = getNextChild(curNode, key);
		}
	}

	public void insert(long dValue) {
		Node2 curNode = root;
		DataItem tempItem = new DataItem(dValue);
		while (true) {
			if (curNode.isFull()) {
				split(curNode);
				curNode = curNode.getParent();
				curNode = getNextChild(curNode, dValue);
			} else if (curNode.isLeaf()) {
				break;
			} else
				curNode = getNextChild(curNode, dValue);
		}
		curNode.insertItem(tempItem);
	}

	public void split(Node2 thisNode) {
		// assumes node is full
		DataItem itemB, itemC;
		Node2 parent, child2, child3;
		int itemIndex;
		itemC = thisNode.removeItem();
		itemB = thisNode.removeItem();
		child2 = thisNode.disconnectChild(2);
		child3 = thisNode.disconnectChild(3);
		Node2 newRight = new Node2();
		if (thisNode == root) {
			root = new Node2();
			parent = root;
			root.connectChild(0, thisNode);
		} else
			parent = thisNode.getParent();
		itemIndex = parent.insertItem(itemB);
		int n = parent.getNumItems();
		for (int j = n - 1; j > itemIndex; j--) {
			Node2 temp = parent.disconnectChild(j);
			parent.connectChild(j + 1, temp);
		}
		parent.connectChild(itemIndex + 1, newRight);
		newRight.insertItem(itemC);
		newRight.connectChild(0, child2);
		newRight.connectChild(1, child3);
	}

	public Node2 getNextChild(Node2 theNode, long theValue) {
		int j;
		int numItems = theNode.getNumItems();
		for (j = 0; j < numItems; j++) {
			if (theValue < theNode.getItem(j).dData)
				return theNode.getChild(j);
		}
		return theNode.getChild(j);
	}

	public void displayTree() {
		recDisplayTree(root, 0, 0);
	}

	private void recDisplayTree(Node2 thisNode, int level, int childNumber) {
		System.out.print("level = " + level + " child = " + childNumber + " ");
		thisNode.displayNode();
		int numItems = thisNode.getNumItems();
		for (int j = 0; j < numItems + 1; j++) {
			Node2 nextNode = thisNode.getChild(j);
			if (nextNode != null)
				recDisplayTree(nextNode, level + 1, j);
			else
				return;
		}
	}
}// end class Tree234

class Tree234App {
	public static void main(String[] args) throws IOException {
		long value;
		Tree234 theTree = new Tree234();
		theTree.insert(50);
		theTree.insert(40);
		theTree.insert(60);
		theTree.insert(30);
		theTree.insert(70);
		while (true) {
			System.out.print("Enter first letter of show, insert, or find :");
			char choice = getChar();
			switch (choice) {
			case 's':
				theTree.displayTree();
				break;
			case 'i':
				System.out.print("Enter value to insert:");
				value = getInt();
				theTree.insert(value);
				break;
			case 'f':
				System.out.print("Enter value to find:");
				value = getInt();
				int found = theTree.find(value);
				if (found != -1)
					System.out.println("Found " + value);
				else
					System.out.println("Could not find " + value);
				break;
			default:
				System.out.println("Invalid entry/n");
			}
		}
	}

	public static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}

	public static char getChar() throws IOException {
		String s = getString();
		return s.charAt(0);
	}

	public static int getInt() throws IOException {
		String s = getString();
		return Integer.parseInt(s);
	}
}