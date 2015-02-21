
public class QuickUnion {
	private int[] id;
	private int count;
	
	public QuickUnion(int n){
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
		} else {
			id[i] = j;
		}
		
		count--;
	}
	
	public static void main(String[] args){
		int n = StdIn.readInt();
		QuickUnion qf = new QuickUnion(n);
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
