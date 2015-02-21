
public class LinkedListStack<Item> {
	private Node first;
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
	
	public void push( Item item) {
		//add item to the top of the list
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
		n++;
	}
	
	
	public Item pop() {
		//remove item from top of stack
		Item item = first.item;
		first = first.next;
		n--;
		return item;
	}
}
