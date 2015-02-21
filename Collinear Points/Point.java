/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new SlopeComparator();       // YOUR DEFINITION HERE

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * slope between this point and that point
     * The slopeTo() method should return the slope between the invoking point (x0, y0) and 
     * the argument point (x1, y1), which is given by the formula (y1 − y0) / (x1 − x0). 
     * Treat the slope of a horizontal line segment as positive zero; treat the slope of a 
     * vertical line segment as positive infinity; treat the slope of a degenerate line segment 
     * (between a point and itself) as negative infinity
     * @param that point
     * @return double
     */
    public double slopeTo(Point that) {
        /* YOUR CODE HERE */
    	int dx = that.x - this.x;  
        int dy = that.y - this.y; 
    	if(dx == 0 && dy == 0) {
    		return Double.NEGATIVE_INFINITY;
    	}
    	else if(dy == 0) {
    		return 0.0;
    	}
        else if(dx == 0) {
        	return Double.POSITIVE_INFINITY;
        } else {
        	return ((double) dy/dx);
        }
    }

    /**
     * The compareTo() method should compare points by their y-coordinates, 
     * breaking ties by their x-coordinates. Formally, the invoking point (x0, y0) 
     * is less than the argument point (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     * is this point lexicographically smaller than that one?
     * 
     * @param Point
     * @return int
     */
    public int compareTo(Point that) {
        /* YOUR CODE HERE */
    	if(this.y > that.y) {
    		return 1;
    	}
        else if(this.y == that.y) {
        	if(this.x > that.x) {
        		return 1;
        	}
        	else if(this.x == that.x) {
        		return 0;
        	}
        	else return -1;
        }
        return -1;
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }
    
    /**
     * The SLOPE_ORDER comparator compares points by the slopes they make with the invoking point 
     * (x0, y0). Formally, the point (x1, y1) is less than the point (x2, y2) if and only if the 
     * slope (y1 − y0) / (x1 − x0) is less than the slope (y2 − y0) / (x2 − x0). Treat horizontal, 
     * vertical, and degenerate line segments as in the slopeTo() method
     *
     */
    private class SlopeComparator implements Comparator<Point> {
		@Override
		public int compare(Point o1, Point o2) {
			double slopeA = slopeTo(o1);
			double slopeB = slopeTo(o2);
			if (slopeA > slopeB) {
				return 1;
			} else if (slopeA < slopeB) {
				return -1;
			} else {
				return 0;
			}
		}
    }

    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
    }
}