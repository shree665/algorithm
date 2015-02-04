import java.util.Iterator;


public class ResizingArrayStack<Item> implements Iterable<Item> {

	@SuppressWarnings("unchecked")
	private Item[] a = (Item[]) new Object[1];
	private int n = 0;
	
	public boolean isEmpty() {
		return n == 0;
	}
	
	public int size() {
		return n;
	}
	
	@SuppressWarnings("unchecked")
	private void resize(int max) {
		//move stack to a new array of size max
		Item[] temp = (Item[]) new Object[max];
		for (int i = 0; i < n; i++) {
			temp[i] = a[i];
		}
		a = temp;
	}
	
	public void push(Item item) {
		//add item to the top of the stacks
		if (n == a.length) {
			resize(2 * a.length);
		}
		a[n++] = item;
	}
	
	public Item pop() {
		//remove item from the top of the stack
		Item item = a[--n];
		a[n] = null; //Doing this to avoid loitering
		if (n > 0 && n == a.length/4) {
			resize(a.length/2);
		}
		return item;
	}
	@Override
	public Iterator<Item> iterator() {
		return new ReverseArrayIterator();
	}
	
	private class ReverseArrayIterator implements Iterator<Item> {
		//Support LIFO iteration
		private int i = n;
		
		public boolean hasNext(){
			return i > 0;
		}
		
		public Item next() {
			return a[--i];
		}
		
		public void remove() {
			//do not remove anything using iterator
		}
	}

}
