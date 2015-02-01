/**
 * This class tests a n by n tiles to see if there are enough open
 * tiles to connect the top of the tiles with the bottom of the
 * tiles. It uses an two dimensional array of boolean to represent
 * the tiles, and supplied WeightedQuickUnionUF object to keep track 
 * of the tiles between open tiles in the grid.
 *
 * The percolates() method returns true if a path can be found from a
 * tile at the top to the bottom in the tiles. This class is dependent on 
 * the WeightedQuickUnionUF.java. It can't be done without it.
 *
 */

public class Percolation {
	
	//local variables
	private int size; //dimensions of the n by n tiles used in the simulation
	private WeightedQuickUnionUF weightedQuickUnion; //supplied object and will be used to do union(), find() and count
	
	/**
	 * to keep track of the tiles are open or full. Starts with full/closed i.e. false
	 */
	private boolean[][] tiles;
	
	/**
	 * Constructor to initializes n by n two dimensional array of tiles which
	 * has all closed tiles. The WeightedQuickUnionUF object is also 
	 * initialized to a size that will contain one element for each tile 
	 * in the tiles (n * n), plus two more to represent "imaginary" tiles 
	 * at the top and bottom of the tiles that will make it easier to 
	 * test the tiles for percolation.
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
	 * Open the tile at coordinates i,j if the tile is not open already using
	 * union method of WeightedQuickUnionUF object
	 * 
	 * @param i - row index
	 * @param j - column index
	 * @return nothing
	 * @throws IndexOutOfBoundsException if the values for i and j are 
     *          off the tile
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
	 * @throws  IndexOutOfBoundsException if the values for i and j are 
     *          off the tile
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
	 * @throws  IndexOutOfBoundsException if the values for i and j are 
     *          off the tile
	 */
	public boolean isFull(int i, int j){
		checkCoordinateRange(i, j);
        return isOpen(i, j);
	}
	
	/**
	 * Checks to see if the imaginary site
     * at location 0 in the union-find object is in the same set as 
     * the imaginary site at location N*N+1 in the union-find object.
     * These two sites are imaginary; they are not actually represented
     * in the grid, although they are represented in the union-find
     * object. They are "located" at the top and the bottom of the 
     * grid, and each connects to all the sites above/below it.
     * 
     * checks to see if the imaginary tile at top in the union-find object is in the same set
     * as the imaginary tile at bottom in the union-find obect.
     * 
	 * @return true if the path between top to bottom exists
	 */
	public boolean percolates(){
		int coordinateId = size * size;
        return weightedQuickUnion.connected(coordinateId, coordinateId + 1);
	}
}
