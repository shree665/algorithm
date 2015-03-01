
public class Board {
	
	private int[][] board;
	private int n;
	private int blankRow;
	private int blankColumn;
	private int manhatten = 0;
	
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
					manhatten += Math.abs(i - a) + Math.abs(j - b);
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
		return manhatten;
		
	}
	
	//is this board the goal board?
	public boolean isGoal() {
		return hamming() == 0;
		
	}
	
	//a board that is obtained by exchanging two adjacent blocks in the same row
	public Board twin() {
		return null;
		
	}
	
	//does this board equal y?
	public boolean equals(Object y) {
		return false;
		
	}
	
	//all neighboring boards
	public Iterable<Board> neighbors() {
		return null;
		
	}
	
	//string representation of this board (in the output format specified below)
	public String toString() {
		return null;
		
	}
	
	//unit tests (not graded)
	public static void main(String[] args) {
		
	}
}
