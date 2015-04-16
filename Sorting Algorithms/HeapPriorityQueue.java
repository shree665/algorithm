
public class HeapPriorityQueue<Key extends Comparable<Key>> {
	
	//heap-ordered complete binary tree
	private Key[] priorityQueue;
	private int n = 0;
	
	@SuppressWarnings("unchecked")
	public HeapPriorityQueue(int maxN) {
		priorityQueue = (Key[]) new Comparable[maxN+1];
	}
	
	public boolean isEmpty() {
		return n == 0;
	}
	
	public int size() {
		return n;
	}
	
	public void insert(Key v) {
		priorityQueue[++n] = v;
		swim(n);
	}
	
	private void swim(int i) {
		while(i > 1 && less(i/2, i)) {
			exch(i/2, i);
			i = i/2;
		}
		
	}

	private void exch(int i, int j) {
		Key k = priorityQueue[i];
		priorityQueue[i] = priorityQueue[j];
		priorityQueue[j] = k;
		
	}

	private boolean less(int i, int j) {
		return priorityQueue[i].compareTo(priorityQueue[j]) < 0;
	}

	public Key deleteMax() {
		//retrieve maximum key from top
		Key max = priorityQueue[1];
		
		//exchange with last item
		exch(1, n--);
		
		//avoiding loitering
		priorityQueue[n+1] = null;
		
		//restoring heap property
		sink(1);
		
		return max;
	}

	private void sink(int i) {
		while(2 * i <= n) {
			int j = 2 * i;
			if (j < n && less(j, j+1)) {
				j++;
			}
			
			if (!less(i,j)) {
				break;
			}
			exch(i,j);
			
			i = j;
		}
	}
}
