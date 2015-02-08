import java.util.Iterator;
import java.util.NoSuchElementException;


public class RandomizedQueue<Item> implements Iterable<Item> {
	//local variables
	private Item[] queue;
	private int n = 0;
	
	//construct an empty randomized queue
	public RandomizedQueue() {
		 queue = (Item[]) new Object[1];
	 }
	 
	//is the queue empty?
	 public boolean isEmpty() {
		return n == 0;
	 }
	 
	//return the number of items on the queue
	 public int size() {
		return n;
	 }
	 
	 //add the item
	 public void enqueue(Item item) {
		checkItem(item);
		if(n == queue.length) {
			resize(2 * queue.length);
		}
		queue[n++] = item;
	 }
	 
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
	 
	//delete and return a random item
	public Item dequeue() {
		 
		checkBoundaries();
	
		 int position = StdRandom.uniform(n);
		 Item item = queue[position];
		 
		 //swapping the randomly dequeue item to the last item of an array because we don't
		 //want to end up with sparse array
		 if (position != n - 1) {
			queue[position] = queue[n-1];
			queue[n-1] = null;  //to avoid loitering
		 }
		 
		 n--;
		 
		// shrink array if necessary when the length of an array is one quater
		if (n > 0 && n == queue.length/4) resize(queue.length/2);
		 
		return item;
	 }
	 
	private void checkBoundaries() {
		 if(isEmpty()){
	        throw new NoSuchElementException("The queue is empty");  
	     }
	}

	//return (but do not delete) a random item
	public Item sample() {
		
		checkBoundaries();

		// to make it random we pick a random element 
		int randomIndex = StdRandom.uniform(n);
		Item sampleItem = queue[randomIndex];
		return sampleItem;
	 }
	
	 //return an independent iterator over items in random order
	 public Iterator<Item> iterator() {
		return new arrayIterator();
	 }
	 
	//private class to iterate over the RandomQueue data type
	private class arrayIterator implements Iterator<Item> {
        
		//node to track the current position
		private int currentIndex;

        public arrayIterator() {
            currentIndex = 0; 
        }

        public boolean hasNext() {
        	return currentIndex > n;
        }
        
        public void remove(){ 
        	throw new UnsupportedOperationException("Remove operation is not supported");
        }

        public Item next() {
        	if (!hasNext()) {
				throw new NoSuchElementException("There are not any elements in the queue");
			}
            Item item = queue[currentIndex++];
            return item;
        }
	}
	 

	//unit testing class to test randomizedQueue
	 public static void main(String[] args) {
		 StdOut.println("**********RandomizedQueue**********");
	     RandomizedQueue<String> queue = new RandomizedQueue<String>();
	     StdOut.println("Enter element to push, or \"-\" to pop, or \"ss\" to sample");
	     while (!StdIn.isEmpty()) {
	         String s = StdIn.readString();
	         if (s.equals("-")) {
	            StdOut.println(queue.dequeue());
	         } else if (s.equals("ss")) {
	        	 StdOut.println("******sample:" + queue.sample()+"*******");
	         } else {
	            queue.enqueue(s);
	         }
	         StdOut.println("*****(" + queue.size() + " left on queue)*****");
	      }
	   }
}
