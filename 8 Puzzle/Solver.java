
public class Solver {
	
	private State finalState;
    
    private class State implements Comparable<State> {
        private Board board;
        private State previousState;
        private int costToSolve;
        private int steps;
        
        public State(Board board, State prev) {
            this.board = board;
            this.previousState = prev;
            
            if (prev == null) {
            	this.steps = 0;
            } else {
            	this.steps = prev.steps + 1;
            }
            
            this.costToSolve = board.manhattan() + this.steps;
        }
        
        public int compareTo(State that) {
            if (this.costToSolve == that.costToSolve) {
            	return 0;
            } else if (this.costToSolve < that.costToSolve) {
            	return -1;
            }
            else {
            	return 1;
            }
        }
        
        public boolean isFinal() {
            return this.board.isGoal();
        }
    }
    
    /*
     * find a solution to the initial board (using the A* algorithm)
     */
    public Solver(Board initial) {
    	
    	//checking for the null values
    	if (initial == null) {
			throw new NullPointerException("Can't build board with null values.");
		}
    	
        this.finalState = findSolution(initial);
    }
    
    private State findSolution(Board initial) {
        MinPQ<State> initialPQ = new MinPQ<State>();
        MinPQ<State> twinPQ = new MinPQ<State>();
        
        State initialState = new State(initial, null);
        State twinState = new State(initial.twin(), null);
        
        while (!initialState.isFinal() && !twinState.isFinal()) {
                       
            Iterable<Board> initNeighbs = initialState.board.neighbors();
            Iterable<Board> twinNeighbs = twinState.board.neighbors();
            
            for (Board b: initNeighbs) {
                if (initialState.previousState == null || !b.equals(initialState.previousState.board)) {
                	initialPQ.insert(new State(b, initialState));
                }
            }
            
            for (Board b: twinNeighbs) {
                if (twinState.previousState == null || !b.equals(twinState.previousState.board)) {
                	twinPQ.insert(new State(b, twinState));
                }
            }
            
            initialState = initialPQ.delMin();
            twinState = twinPQ.delMin();
        }
        if (initialState.isFinal()) {
        	return initialState;
        }
        else {
        	return null;
        }
    }
    
    /*
     * is the initial board solvable?
     */
    public boolean isSolvable() {
        return (this.finalState != null);
    }
    
    /*
     * minimum number of moves to solve initial board; -1 if no solution
     */
    public int moves() {
        if (!isSolvable()) {
        	return -1;
        }
        else return finalState.steps;
    }
    
    /*
     * sequence of boards in a shortest solution; null if no solution
     */
    public Iterable<Board> solution() {
        if (!isSolvable()) return null;
        
        Queue<Board> q = new Queue<Board>();
        restorePath(q, this.finalState);
        return q;
    }
    
    /*
     * recursively restore path to solve puzzle
     */
    private void restorePath(Queue<Board> q, State s) {
        if (s == null) {
        	return;
        }
        
        // recursive call for previous state
        restorePath(q, s.previousState); 
        
        q.enqueue(s.board);
    }
    
    /*
     * solve a slider puzzle
     */
    public static void main(String[] args) {  
        
        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                blocks[i][j] = in.readInt();
            }
        }
        
        //initial board
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution()) {
                StdOut.println(board);
            }
        }
    }
}
