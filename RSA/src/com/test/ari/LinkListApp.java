package com.test.ari;

/**
 * 单链表的创建 头插法
 * @author sunnyykn
 */
class Link {
	public int iData; // data item key
	public double dData; // data item
	public Link next; // next link in list

	public Link(int id, double dd) // constructor
	{
		iData = id; // initalize data
		dData = dd; // ('next' is automatically set to null)
	}

	public void displayLink() // display ourself
	{
		System.out.print("{" + iData + "," + dData + "}");
	}
}// end class Link

class LinkList {
	private Link first; // ref to first link on list

	public LinkList() {
		first = null; // no items no list yet
	}

	public boolean isEmpty() // true if list is empty
	{
		return (first == null);
	}

	public void insertFirst(int id, double dd) // insert at start of list
	{
		Link newLink = new Link(id, dd); // make new link
		newLink.next = first; // newLink-->old first
		first = newLink; // first-->newLink
	}

	public Link deleteFirst() // delete first item
	{
		Link temp = first; // save reference to link
		first = first.next; // delete it:first-->old next
		return temp;
	}

	public void displayList() {
		System.out.print("List(first-->Last):");
		Link current = first; // start at beginning of list
		while (current != null) {
			current.displayLink();
			current = current.next;
		}
		System.out.println("");
	}
}// end class LinkList

class LinkListApp {
	public static void main(String[] args) {
		LinkList theList = new LinkList(); // make new list
		theList.insertFirst(22, 2.99);
		theList.insertFirst(44, 4.99);
		theList.insertFirst(66, 6.99);
		theList.insertFirst(88, 8.99);
		theList.displayList(); // display list
		while (!theList.isEmpty()) {
			Link aLink = theList.deleteFirst(); // delete link
			System.out.print("Deleted "); // display it
			aLink.displayLink();
			System.out.println("");
		}
		theList.displayList();
	}
}
