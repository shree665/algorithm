import java.util.Stack;

public class PointSET {
	
	private SET<Point2D> set;
	
	// construct an empty set of points
	public PointSET() {
		 set = new SET<Point2D>();
	}
	
	// is the set empty?
	public boolean isEmpty() {
		return set.isEmpty(); 
	}
	
	// number of points in the set 
	public int size() {
		return set.size();
	}
	
	// add the point to the set (if it is not already in the set)
	public void insert(Point2D p) {
		set.add(p);
	}
	
	// does the set contain point p? 
	public boolean contains(Point2D p) {
		return set.contains(p);
	}
	
	// draw all points to standard draw 
	public void draw() {
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.setPenRadius(0.2);
		
		for (Point2D point2d : set) {
			StdDraw.point(point2d.x(), point2d.y());
		}
		
		StdDraw.show(0);
	}
	
	// all points that are inside the rectangle 
	public Iterable<Point2D> range(RectHV rect) {
		Stack<Point2D> stack = new Stack<Point2D>();
		
		for (Point2D p : set) {
			if (rect.contains(p)) {
				stack.add(p);
			}
		}
		return stack;
	}
	
	// a nearest neighbor in the set to point p; null if the set is empty 
	public Point2D nearest(Point2D p) {
		
		if (set.isEmpty()) {
			return null;
		}
		
		double mDis = Double.MAX_VALUE;
		Point2D nearestPoint = null;
		
		for (Point2D point2d : set) {
			double distance = p.distanceSquaredTo(point2d);
			if (distance < mDis) {
				mDis = distance;
				nearestPoint = point2d;
			}
		}
		return nearestPoint;
	}

	public static void main(String[] args) {
		String filename = args[0];
        In in = new In(filename);
        StdDraw.show(0);
        // initialize the data structures with N points from standard input
        PointSET brute = new PointSET();
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            brute.insert(p);
        }
        double x0 = 0.0, y0 = 0.0;      // initial endpoint of rectangle
        double x1 = 0.0, y1 = 0.0;      // current location of mouse
        boolean isDragging = false;     // is the user dragging a rectangle
        // draw the points
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(.01);
        brute.draw();
        while (true) {
            StdDraw.show(40);
            // user starts to drag a rectangle
            if (StdDraw.mousePressed() && !isDragging) {
                x0 = StdDraw.mouseX();
                y0 = StdDraw.mouseY();
                isDragging = true;
                continue;
            }
            // user is dragging a rectangle
            else if (StdDraw.mousePressed() && isDragging) {
                x1 = StdDraw.mouseX();
                y1 = StdDraw.mouseY();
                continue;
            }
            // mouse no longer pressed
            else if (!StdDraw.mousePressed() && isDragging) {
                isDragging = false;
            }
            RectHV rect = new RectHV(Math.min(x0, x1), Math.min(y0, y1),
                                     Math.max(x0, x1), Math.max(y0, y1));
            // draw the points
            StdDraw.clear();
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(.01);
            brute.draw();
            // draw the rectangle
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius();
            rect.draw();
            // draw the range search results for brute-force data structure in red
            StdDraw.setPenRadius(.03);
            StdDraw.setPenColor(StdDraw.RED);
            for (Point2D p : brute.range(rect))
                p.draw();
            // draw the range search results for kd-tree in blue
            StdDraw.show(40);
        }
	}
}
