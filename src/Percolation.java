
public class Percolation {
	
	//local variables
	private int size;
	private WeightedQuickUnionUF weightedQuickUnion;
	private boolean[][] openTile;
	private int openCount;
	
	public Percolation(int n){
		this.size = n;
		openCount = 0;
		this.weightedQuickUnion = new WeightedQuickUnionUF(size * size);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				openTile[i][j] = false;
			}
			
		}
	}
	
	public void open(int i, int j){
		if (isOpen(i,j) == false) {
			openTile[i][j] = true;
			openCount += 1;
		}
	}
	
	public boolean isOpen(int i, int j){
		return openTile[i][j];
	}
	
	public boolean isFull(int i, int j){
		return true;
	}
	
	public boolean percolates(){
		return true;
	}
	
	public static void main (String[] args){
		
	}
}
