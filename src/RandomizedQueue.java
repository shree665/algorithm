import java.util.Iterator;


public class RandomizedQueue<Item> implements Iterable<Item> {
	//local variables
	private Item[] queue;
	private int n = 0;
	
	// construct an empty randomized queue
	 @SuppressWarnings("unchecked")
	public RandomizedQueue() {
		 queue = (Item[]) new Object[1];
	 }
	 
	// is the queue empty?
	 public boolean isEmpty() {
		return n == 0;
		 
	 }
	 
	// return the number of items on the queue
	 public int size() {
		return n;
	 }
	 
	 // add the item
	 public void enqueue(Item item) {
		checkItem(item);
		if(n == queue.length) {
			resize(2 * queue.length);
		}
		queue[n++] = item;
	 }
	 
	 @SuppressWarnings("unchecked")
	private void resize(int max) {
		//move stack to a new array of size max
		Item[] temp = (Item[]) new Object[max];
		for (int i = 0; i < n; i++) {
			temp[i] = queue[i];
		}
		queue = temp;
	}

	private void checkItem(Item item) {
		if (item == null) {
			throw new NullPointerException("can't put null vlaue in the queue");
		}
	}
	 
	// delete and return a random item
	 public Item dequeue() {
		 
		checkBoundaries();
		
		 int position;
		 
		return null;
		 
	 }
	 private void checkBoundaries() {
		 if(isEmpty()){
	        throw new RuntimeException("THE QUEUE IS EMPTY");  
	     }
		 
	}

	public Item sample() {
		return null;
		 // return (but do not delete) a random item
	 }
	 public Iterator<Item> iterator() {
		return null;
		 // return an independent iterator over items in random order
	 }
	 public static void main(String[] args) {
		 // unit testing
	 }


}
