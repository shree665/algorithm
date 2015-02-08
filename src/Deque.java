import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * A double-ended queue or dequeue (pronounced "deck") is a generalization of
 * a queue that supports inserting and removing items from either the front or the 
 * back of the data structure
 */
public class Deque<Item> implements Iterable<Item> {
	
	//local variables
	private int n;
	private Node first, last;
	
	private class Node {
		Item item;
		Node previous;
		Node next;
	}

	// construct an empty dequeue
	public Deque() {
		first = last = null;
		n = 0;
	}
	
	// is the dequeue empty?
	public boolean isEmpty() {
		return n == 0;
	}
	
	// return the number of items on the deque
	public int size() {
		return n;
	}
	
	// insert the item at the front
	public void addFirst(Item item) {
		//adding item to the end of the queue
		checkItem(item);
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.previous = null;
		if (isEmpty()) {
			last = first;
			first.next = null;	
		} else {
			first.next = oldfirst;
			oldfirst.previous = first;
		}
		
		n++;
	}
	
	private void checkItem(Item item) {
		if (item == null) {
			throw new NullPointerException("The value that you trying to put is null");
		}
	}

	// insert the item at the end
	public void addLast(Item item) {
		//adding item to the end of the queue
		checkItem(item);
		Node oldlast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		if (isEmpty()) {
			first = last;
			last.previous = null;
		} else {
			last.previous = oldlast;
			oldlast.next = last;
		}
		
		n++;
	}
	
	// delete and return the item at the front
	public Item removeFirst() {
		//removing item from beginning
		anyItem();
		Item item = first.item;
		first = first.next;
		n--;
		if (isEmpty()) {
			first = last = null;
		} else {
			first.previous = null;
		}
		return item;
	}
	
	private void anyItem() {
		if (isEmpty()) {
			throw new NoSuchElementException("Linked List is empty");
		}
	}

	// delete and return the item at the end
	public Item removeLast() {
		anyItem();
		Item item = last.item;
		last = last.previous;
		n--;
		if (isEmpty()) {
			first = last = null;
		} else {
			last.next = null;
		}
		return item;
	}
	
	// return an iterator over items in order from front to end
	public Iterator<Item> iterator() {
		return new ListIterator();
		
	}
	
	//private class to iterate over the deque datatype
	private class ListIterator implements Iterator<Item> {
        
		//node to track the current position
		private Node current;

        public ListIterator() {
            current = first; 
        }

        public boolean hasNext() {
        	return current != null;
        }
        
        public void remove(){ 
        	throw new UnsupportedOperationException("Remove operation is not supported");
        }

        public Item next() {
        	if (!hasNext()) {
				throw new NoSuchElementException("There are not any elements in the queue");
			}
            Item item = current.item;
            current = current.next; 
            return item;
        }
	}
	
	//for unit test purpose
	public static void main(String[] args) {
		StdOut.println("########## Deque ##########");
		Deque<String> q = new Deque<String>();
		StdOut.println("Enter element to push, or \"-\" to pop from first, or \"#\" to pop from last to sample");
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-") && !item.equals("#")){
            	q.addLast(item);
              //q.addFirst(item);
            }
            else if (item.equals("-")){
            	StdOut.println(q.removeFirst() + " ");
            }
            else if(item.equals("#")){
            	StdOut.println(q.removeLast() + " ");
            }
            StdOut.println("(" + q.size() + " left on queue)");
        }
        StdOut.println("(" + q.size() + " left on queue)");
	}

}
