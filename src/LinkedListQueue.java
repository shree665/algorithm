
public class LinkedListQueue<Item> {
	private Node first;
	private Node last;
	private int n;
	
	private class Node {
		Item item;
		Node next;
	}
	
	public boolean isEmpty() {
		return first == null;
	}
	
	public int size() {
		return n;
	}
	
	public void enqueue(Item item) {
		//adding item to the end of the queue
		Node oldlast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		if (isEmpty()) {
			first = last;
		} else {
			oldlast.next = last;
		}
	}
	
	public Item dequeue() {
		//remove the item from the beginning
		Item item = first.item;
		first = first.next;
		n--;
		if (isEmpty()) {
			last = null;
		}
		return item;
	}
}
