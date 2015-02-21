
public class WeightedQuickUnion {
	private int[] id; //parent link
	private int[] sz; //size of component for roots
	private int count; //number of components
	
	public WeightedQuickUnion(int n){
		count = n;
		id = new int[n];
		for (int i = 0; i < n; i++) {
			id[i] = i;
		}
		sz = new int[n];
		for (int i = 0; i < n; i++) {
			sz[i] = 1;
		}
	}
	
	public int count(){
		return count;
	}
	
	public boolean connected(int p, int q){
		return find(p) == find(q);
	}
	
	public int find(int p){ 
		//follow links to find a root
		while (p != id[p]) {
			p = id[p];
		}
		return p;
	}
	
	public void union(int p, int q){
		//give p and q the same root
		int i = find(p);
		int j = find(q);
		
		//nothing to do if p and q are already in the same component
		if (i == j) {
			return;
		} 
		
		if (sz[i] < sz[j]) {
			id[i] = j;
			sz[j] += sz[i];
		} else {
			id[j] = i;
			sz[i] += sz[j];
		}
		
		count--;
	}
	
	public static void main(String[] args){
		int n = StdIn.readInt();
		WeightedQuickUnion qf = new WeightedQuickUnion(n);
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
