/**
 * Subset.java that takes a command-line integer k; reads in a sequence of N strings from 
 * standard input using StdIn.readString(); and prints out exactly k of them, uniformly at 
 * random. Each item from the sequence can be printed out at most once. You may assume that 0 ≤ k ≤ N, 
 * where N is the number of string on standard input
 *
 */
public class Subset {
	public static void main(String[] args) {
		RandomizedQueue<String> q = new RandomizedQueue<String>();  
        int k = Integer.valueOf(args[0]);  
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();  
            q.enqueue(item);  
        }  
        
        while (k > 0) {  
            StdOut.println(q.dequeue());  
            k--;
        }
	}
}
