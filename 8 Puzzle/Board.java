
public class Board {
	
	private int[][] board;
	private int n;
	private int blankRow;
	private int blankColumn;
	private int manhattan = 0;
	
	/**
	 * construct a board from an N-by-N array of blocks
	 * (where blocks[i][j] = block in row i, column j)
	 * @param blocks
	 */
	public Board(int[][] blocks) {
		n = blocks.length;
		board = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int value = board[i][j] = blocks[i][j];
				if (value == 0) {
					blankRow = i;
					blankColumn = j;
				} else {
					int a = (value - 1)/n;
					int b = (value - 1)%n;
					manhattan += Math.abs(i - a) + Math.abs(j - b);
				}
			}
		}
	}
    
	//board dimension N
	public int dimension() {
		return n;
	}
	
	//number of blocks out of place
	public int hamming() {
		int hamming = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int value = board[i][j];
				if (value != 0 && value != i * n +j + i + 1) {
					hamming++;
				}
			}
		}
		
		return hamming;
		
	}
	
	//sum of Manhattan distances between blocks and goal
	public int manhattan() {
		return manhattan;
		
	}
	
	//is this board the goal board?
	public boolean isGoal() {
		return hamming() == 0;
		
	}
	
	//a board that is obtained by exchanging two adjacent blocks in the same row
	public Board twin() {
		Board twin;
        if (board[0][0] != 0 && board[0][1] != 0) {
            exch(board, 0, 0, 0, 1);
            twin = new Board(board);
            exch(board, 0, 0, 0, 1);
        }
        else {
            exch(board, 1, 0, 1, 1);
            twin = new Board(board);
            exch(board, 1, 0, 1, 1);
        }
        return twin;
		
	}
	
	private void exch(int[][] a, int x1, int y1, int x2, int y2) {
        int temp = a[x1][y1];
        a[x1][y1] = a[x2][y2];
        a[x2][y2] = temp;
    }
	
	//does this board equal board y?
	public boolean equals(Object y) {
		if (this == y) {
			return true;
		}
		
        if (y == null) {
        	return false;
        }
        
        if (this.getClass() != y.getClass()) {
        	return false;
        }
        
        Board that = (Board) y;
        
        if (that.dimension() != this.dimension()) {
        	return false;
        }
        
        for (int i = 0; i < n; i++) {
        	for (int j = 0; j < n; j++) {
        		if (board[i][j] != that.board[i][j]) {
        			return false; 
        		}
        	}
        }
        return true;
	}
	
	//all neighboring boards
	public Iterable<Board> neighbors() {
		Stack<Board> neighbors = new Stack<Board>();
        
        if (blankRow - 1 > -1) {
            addNeighbor(blankRow - 1, blankColumn, neighbors);
        }
        if (blankRow + 1 < n) { 
            addNeighbor(blankRow + 1, blankColumn, neighbors);
        }
        if (blankColumn - 1 > -1) {
            addNeighbor(blankRow, blankColumn - 1, neighbors);
        }
        if (blankColumn + 1 < n) {
            addNeighbor(blankRow, blankColumn + 1, neighbors);
        }
        
        return neighbors;
	}
	
	private void addNeighbor(int row, int column, Stack<Board> neighbors) {       
        exch(board, row, column, blankRow, blankColumn);
        Board neighbor = new Board(board);
        exch(board, row, column, blankRow, blankColumn);
        neighbors.push(neighbor);
    }
	
	//string representation of this board (in the output format specified below)
	public String toString() {
		StringBuilder s = new StringBuilder();
        s.append(n + "\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s.append(String.format("%2d ", board[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
	}
	
	//unit tests (not graded)
	public static void main(String[] args) {
		
	}
}
