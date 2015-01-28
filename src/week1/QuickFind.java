package week1;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

public class QuickFind {
	private int[] id;
	private int count;
	
	public QuickFind(int n){
		count = n;
		id = new int[n];
		for (int i = 0; i < n; i++) {
			id[i] = i;
		}
	}
	
	public int count(){
		return count;
	}
	
	public boolean connected(int p, int q){
		return find(p) == find(q);
	}
	
	public int find(int p){ 
		return id[p];
	}
	
	public void union(int p, int q){
		//put p and q into the same component
		int pID = find(p);
		int qID = find(q);
		
		if (pID == qID) {
			return;
		}
		
		for (int i = 0; i < id.length; i++) {
			if (id[i] == pID) {
				id[i] = qID;
			}
			count--;
		}
	}
	
	public static void main(String[] args){
		int n = StdIn.readInt();
		QuickFind qf = new QuickFind(n);
		while(!StdIn.isEmpty()){
			int p = StdIn.readInt();
			int q = StdIn.readInt();
			if(qf.connected(p, q)){
				continue;
			}
			qf.union(p, q);
			StdOut.println(p + " "+ q);
		}
		StdOut.println(qf.count() + " components");
	}
}
