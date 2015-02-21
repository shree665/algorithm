import java.util.Arrays;


public class Fast {
	
	public static void main(String[] args) {
		//input file name from command line argument
		In input = new In(args[0]); 
	    
		//reading first line of the file as the number of myPoint in file
		int n = input.readInt();
       
		//we need more than 4 points
		if (n < 4){  
           return;  
		} 
        
	    //creating array of point object
	    Point[] myPointArray = new Point[n];
	    
	    //setting provided scale
	    StdDraw.setXscale(0, 32768);
	    StdDraw.setYscale(0, 32768);
	    
	    //reading x and y point and creating object in loop by reading rest of the line in file
	    for (int i = 0; i < n; i++) {
	    	if (input.hasNextLine()) {
	    		int x = input.readInt();
	            int y = input.readInt();
	            Point point = new Point(x, y);
	            myPointArray[i] = point;
	            point.draw();
			}
	    }
	    
	    //sorting an array of myPoint using quick short
	    Quick3way.sort(myPointArray);
	  
		for (int i = 0; i < n; i++) {
			Point originPoint = myPointArray[i];
			Point[] myOtherPoints = new Point[n-1];
			for (int j = i+1; j < myPointArray.length; j++) {
				myOtherPoints[j] = myPointArray[j];
			}
			//Arrays.sort(myPointArray, i + 1, n, myPointArray[i].SLOPE_ORDER);
			Arrays.sort(myOtherPoints, originPoint.SLOPE_ORDER);
			
			int count = 0;
			int index = 0;
			
			double tempSlope = originPoint.slopeTo(myOtherPoints[0]);
			
			for (int j = 0; j < myOtherPoints.length; j++) {
				double originToOtherSlope = originPoint.slopeTo(myOtherPoints[j]);
				if (Double.compare(originToOtherSlope,  tempSlope) == 0) {
					count++;
					continue;
				} 
				else {
					if (count >= 3) {
						if (myOtherPoints[index].compareTo(originPoint) >= 0) {
							StdOut.print(originPoint + " -> ");
							for (int k = index; k < j-1; k++) {
								StdOut.print(myOtherPoints[k] + " -> ");
							}
							StdOut.println(myOtherPoints[j-1]);
							originPoint.drawTo(myOtherPoints[j-1]);
						}
					}
					count = 1;
					index = j;
					tempSlope = originPoint.slopeTo(myOtherPoints[j]);
				}
			}
			
			if (count >= 3) {
				if (myOtherPoints[index].compareTo(originPoint) >= 0) {
					StdOut.print(originPoint + " -> ");
					for (int a = index; i < myOtherPoints.length - 1; a++) {
						StdOut.print(myOtherPoints[a] + " -> ");
					}
					StdOut.println(myOtherPoints[myOtherPoints.length-1]);
					originPoint.drawTo(myOtherPoints[myOtherPoints.length-1]);
				}
			}
		}
	    
	}
}
