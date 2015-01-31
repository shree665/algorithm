
public class Percolation {
	
	//local variables
	private int size;
	private WeightedQuickUnionUF weightedQuickUnion;
	private boolean[][] tiles;
	
	/**
	 * Constructor to create n by n closed tiles
	 * 
	 * @param n - number of tiles
	 */
	public Percolation(int n){
		this.size = n;
		tiles = new boolean[n][n];
		this.weightedQuickUnion = new WeightedQuickUnionUF(size * size + 2);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				tiles[i][j] = false;
			}
			
		}
	}
	
	/**
	 * Open the tile at coordinates i,j if the tile is not open already
	 * 
	 * @param i - row index
	 * @param j - column index
	 * @return nothing
	 */
	public void open(int i, int j){
		
		checkCoordinateRange(i,j);
		
		tiles[i-1][j-1] = true;
		
		if (i == 1) {
			weightedQuickUnion.union(coordicnatesToId(i-1, j-1), size * size);
        }
		
		//connecting tiles with it's neighboring tiles
		int leftSideOfYCoordinate = j - 1; //connecting to the left of Y co-ordinate
        int rightSideOfYCoordinate = j + 1; //connecting to the right of Y co-ordinate
        int uperSideOfXCoordinate = i - 1; //connecting to the up side of X co-ordinate
        int LowerSideOfXCoordinate = i + 1; // Connecting to the down side of X co-ordinate
        
        //opening left of Y
        if (leftSideOfYCoordinate > 0 && isOpen(i, leftSideOfYCoordinate)) {
        	weightedQuickUnion.union(coordicnatesToId(i - 1, j - 1), coordicnatesToId(i - 1, leftSideOfYCoordinate - 1));
        }
        
        //opening right of Y
        if (rightSideOfYCoordinate <= size && isOpen(i, rightSideOfYCoordinate)) {
        	weightedQuickUnion.union(coordicnatesToId(i - 1, j - 1), coordicnatesToId(i - 1, rightSideOfYCoordinate - 1));
        }
        
        //Opening top of X
        if (uperSideOfXCoordinate > 0 && isOpen(uperSideOfXCoordinate, j)) {
        	weightedQuickUnion.union(coordicnatesToId(i - 1, j - 1), coordicnatesToId(uperSideOfXCoordinate - 1, j - 1));
        }
        
        //opening bottom of X
        if (LowerSideOfXCoordinate <= size && isOpen(LowerSideOfXCoordinate, j)) {
        	weightedQuickUnion.union(coordicnatesToId(i -1, j - 1), coordicnatesToId(LowerSideOfXCoordinate - 1, j - 1));
        }
        
        // All the open tiles in the last row (i == n) will be connected to n * n + 1
        if (i == size) {
        	weightedQuickUnion.union(coordicnatesToId(i-1, j-1), size * size + 1);
        }
	}
	
	
	private int coordicnatesToId(int i, int j) {
        return i * size + j;
    }
	
	private void checkCoordinateRange(int i, int j) {
		if (i < 1 || i > size) {
	          throw new IndexOutOfBoundsException("row index i is out of bounds.");
	    }
		
		if (j < 1 || j > size) {
			throw new IndexOutOfBoundsException("column index j is out of bounds.");
		}
	}

	
	/**
	 * Is site of row i and column j is open?
	 * 
	 * @param i - index of row
	 * @param j - index of column
	 * @return true if the tile at [i][j] open, otherwise return false
	 */
	public boolean isOpen(int i, int j){
		checkCoordinateRange(i, j);
        return tiles[i-1][j-1];
	}
	
	/**
	 * Check if the tile of row i and column j is full?
	 * 
	 * @param i - row index
	 * @param j - column index
	 * @return true if the tile is full
	 */
	public boolean isFull(int i, int j){
		checkCoordinateRange(i, j);
        return isOpen(i, j);
	}
	
	/**
	 * @return true if the system percolates
	 */
	public boolean percolates(){
		int coordinateId = size * size;
        return weightedQuickUnion.connected(coordinateId, coordinateId + 1);
	}

}
