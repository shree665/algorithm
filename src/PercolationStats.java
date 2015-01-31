
public class PercolationStats {
	
	//local variables
	 private final double[] x;
	    
    public PercolationStats(int n, int t) {
        if (n <= 0 || t <= 0) {
            throw new IllegalArgumentException("Please make sure enter valid inputs");
        }
        
        int allTiles = n * n;
        
        x = new double[t];
        
        for (int i = 0; i < t; i++) {
            Percolation p = new Percolation(n);
            
            int openTiles = 0;
            while (!p.percolates()) {
                int xIdx = StdRandom.uniform(n) + 1;
                int yIdx = StdRandom.uniform(n) + 1;
                if (p.isOpen(xIdx, yIdx)) {
                    continue;
                }
                p.open(xIdx, yIdx);
                openTiles++;
            }
            x[i] = (openTiles * 1.0) / allTiles;
        }
    }
    
    public double mean() {
        return StdStats.mean(x);
    }
    
    public double stddev() {
        return StdStats.stddev(x);
    }
    
    public double confidenceLo() {
        return mean() - (1.96 * stddev()) / Math.sqrt(x.length);
    }
    
    public double confidenceHi() {
        return mean() + (1.96 * stddev()) / Math.sqrt(x.length);
    }
    
    public static void main(String[] args) {
        //int N = Integer.parseInt(args[0]);
       // int T = Integer.parseInt(args[1]);
        
        PercolationStats ps = new PercolationStats(100, 100);
        StdOut.println("mean = " + ps.mean());
        StdOut.println("stddev = " + ps.stddev());
        StdOut.println("95% confidence interval = " + ps.confidenceLo() + ", " 
                               + ps.confidenceHi());
    }

}
