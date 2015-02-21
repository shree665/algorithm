/**
 * Class to stimulate the percolation for the client
 * 
 */
public class PercolationStats {
	
	//local variables
	private final double[] x;
	
	// perform T independent experiments on an N-by-N grid
    public PercolationStats(int n, int t) {
        if (n <= 0 || t <= 0) {
            throw new IllegalArgumentException("Please make sure to enter valid inputs");
        }
        
        int allTiles = n * n;
        
        x = new double[t];
        
      //Get statistics for T runs of percolation with size N.
        for (int i = 0; i < t; i++) {
            Percolation p = new Percolation(n);
            
            int openTiles = 0;
            while (!p.percolates()) {
                int xCoordinateId = StdRandom.uniform(n) + 1;
                int yCoordinateId = StdRandom.uniform(n) + 1;
                if (p.isOpen(xCoordinateId, yCoordinateId)) {
                    continue;
                }
                p.open(xCoordinateId, yCoordinateId);
                openTiles++;
            }
            x[i] = (openTiles * 1.0) / allTiles;
        }
    }
    
    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(x);
    }
    
    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(x);
    }
    
    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (1.96 * stddev()) / Math.sqrt(x.length);
    }
    
    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (1.96 * stddev()) / Math.sqrt(x.length);
    }
    
    /**
     * main class to test the percolation as described in the requirement
     * 
     * @param args
     */
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        
        PercolationStats ps = new PercolationStats(N, T);
        StdOut.println("mean = " + ps.mean());
        StdOut.println("stddev = " + ps.stddev());
        StdOut.println("95% confidence interval = " + ps.confidenceLo() + ", " 
                               + ps.confidenceHi());
    }

}
