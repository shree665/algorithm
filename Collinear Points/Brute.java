import java.util.Arrays;


public class Brute {
	public static void main(String[] args) {	
		// input file from command line argument
	    In input = new In(args[0]); 
	    
        //setting provided scale
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        
       //reading first line of the file as the number of points in file
       int n = input.readInt();
        
        //creating array of point object
        Point[] myPoint = new Point[n];
        
        //reading x and y point and creating object in loop by reading rest of the line in file
        for (int i = 0; i < n; i++) {
        	if (input.hasNextLine()) {
        		int x = input.readInt();
                int y = input.readInt();
                Point point = new Point(x, y);
                myPoint[i] = point;
                point.draw();
			}
        }
        
        //sorting an array of myPoint
        Arrays.sort(myPoint);
        
        //looping through the myPoint using Brute force algorithm
        for (int a = 0; a < n; a++) {
            Point pointA = myPoint[a];
            for (int b = a+1; b < n; b++) {
                double slopeAB = pointA.slopeTo(myPoint[b]);
                for (int c = b+1; c < n; c++) {
                	double slopeAC = pointA.slopeTo(myPoint[c]);
                    if (slopeAB == slopeAC) {
                        for (int d = c+1; d < n; d++) {
                        	double slopeAD = pointA.slopeTo(myPoint[d]);
                            if (slopeAD == slopeAB) {
                                Point[] line = {pointA, myPoint[b], myPoint[c], myPoint[d]};
                                printLine(line);
                            }
                        }
                    }
                }
            }
        }
    }

	/**
	 * printing and drawing line between provided points
	 * 
	 * @param line
	 */
	private static void printLine(Point[] line) {
		
	   int z = line.length;
	   
	   //printing line
       for (int i = 0; i < z; i++) {
    	   //printing first element without arrow
           if (i == 0) {
        	   StdOut.print(line[i].toString());
           }
           else {
        	   StdOut.print(" -> "+line[i].toString());
           }
       }
       
       StdOut.println();
       line[0].drawTo(line[z-1]);
	}
}
