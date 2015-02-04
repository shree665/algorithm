import java.util.Iterator;


public class Bag<Item> {
	
	private Node first;
	
	private class Node {
		Item item;
		Node next;
	}
	
	public void add(Item item) {
		//it is same as push in stack
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
	}
	
	public Iterator<Item> iterator() {
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<Item> {
		private Node current = first;

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public Item next() {
			Item item = current.item;
			current = current.next;
			return item;
		}

		@Override
		public void remove() {
			//no remove operation
		}
	}
}
