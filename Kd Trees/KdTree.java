public class KdTree {
	
	private Node root;
	private int n;
	private final RectHV CANVAS = new RectHV(0, 0, 1, 1);
	
	private static class Node {
		private Point2D point;
		private RectHV rectangle;
		private Node leftTreeNode;
		private Node rightTreeNode;
		public Node(Point2D point, RectHV rectangle) {
			this.point = point;
			this.rectangle = rectangle;
			this.leftTreeNode = null;
			this.rightTreeNode = null;
		}
	}
	
	// construct an empty set of points
	public KdTree() {
		 root = null;
		 n = 0;
	}
	
	// is the set empty? 
	public boolean isEmpty() {
		return n == 0;
	}
	
	// number of points in the set 
	public int size() {
		return n;
	}
	
	// add the point to the set (if it is not already in the set)
	public void insert(Point2D p) {
		root = insert(root, p, CANVAS.xmin(), CANVAS.ymin(), CANVAS.xmax(), CANVAS.ymax(), 0);
	}
	
	private Node insert(Node node, Point2D p, double xmin, double ymin, double xmax, double ymax, int origin) {
		if (node == null) {
			n++;
			Node newNode = new Node(p, new RectHV(xmin, ymin, xmax, ymax));
			return newNode;
		}
		
		int comp = compareTo(p, node.point, origin);
		double x0 = xmin;
		double x1 = xmax;
		double y0 = ymin;
		double y1 = ymax;
		
		if (comp < 0) {
			if (origin == 0) {
				x1 = node.point.x();
			} else {
				y1 = node.point.y();
			}
			node.leftTreeNode = insert(node.leftTreeNode, p, x0, y0, x1, y1, 1-origin);
		} else if (comp > 0) {
			if (origin == 0) {
				x0 = node.point.x();
			} else {
				y0 = node.point.y();
			}
			node.rightTreeNode = insert(node.rightTreeNode, p, x0, y0, x1, y1, 1-origin);
		}
		
		return node;
	}

	private int compareTo(Point2D a, Point2D b, int origin) {
		if (a.equals(b)) {
			return 0;
		} else {
			if (origin == 0) {
				//comparing horizontal line
				if (a.x() < b.x()) {
					return -1;
				} else {
					return 1;
				}
			} else {
				//comparing vertical line
				if (a.y() < b.y()) {
					return -1;
				} else {
					return 1;
				}
			}
		}
	}

	// does the set contain point p? 
	public boolean contains(Point2D p) {
		return get(root, p, 0);
	}
	
	private boolean get(Node node, Point2D p, int origin) {
		if (node == null) {
			return false;
		}
		int comp = compareTo(p, node.point, origin);
		if (comp < 0) {
			return get(node.leftTreeNode, p, 1-origin);
		}
		return true;
	}

	// draw all points to standard draw 
	public void draw() {
		StdDraw.setScale(0, 1);  
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius();
        CANVAS.draw();
        draw(root, 0);
	}
	
	/**
	 * Draw the black points. It also draw the red and blue lines between the given points.
	 * 
	 * @param node
	 * @param origin
	 */
	private void draw(Node node, int origin) {
		if (node == null) {
			return;
		}
        // draw point with black color and the point radius of .02
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(.02);
        StdDraw.point(node.point.x(), node.point.y());
        // draw line
        if (origin == 0) {
            // drawing line vertically i.e. red line
             StdDraw.setPenColor(StdDraw.RED);
             StdDraw.setPenRadius();
             StdDraw.line(node.point.x(), node.rectangle.ymin(), node.point.x(), node.rectangle.ymax());
        } else {
            // drawing line horizontally i.e. blue line
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.setPenRadius();
            StdDraw.line(node.rectangle.xmin(), node.point.y(), node.rectangle.xmax(), node.point.y());
        }
        draw(node.leftTreeNode, 1-origin);
        draw(node.rightTreeNode, 1-origin);
	}

	// all points that are inside the rectangle 
	public Iterable<Point2D> range(RectHV rect) {
		Queue<Point2D> points = new Queue<Point2D>();
        Queue<Node> queue = new Queue<Node>();
        if (root == null) {
        	return points;
        }
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            Node node = queue.dequeue();
            if (node == null) {
            	continue;
            }
            if (rect.contains(node.point)) {
            	points.enqueue(node.point);
            }
            if (node.leftTreeNode != null && rect.intersects(node.leftTreeNode.rectangle)) {
            	queue.enqueue(node.leftTreeNode);
            }
            if (node.rightTreeNode != null && rect.intersects(node.rightTreeNode.rectangle)) {
            	queue.enqueue(node.rightTreeNode);
            }
        }
        return points;
	}
	
	// a nearest neighbor in the set to point p; null if the set is empty 
	public Point2D nearest(Point2D p) {
		if (root == null) return null;
        Point2D rectanglePoint = null;
        double minDistance = Double.MAX_VALUE;
        Queue<Node> queue = new Queue<Node>();
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            Node node = queue.dequeue();
            double distance = p.distanceSquaredTo(node.point);
            if (distance < minDistance) {
                rectanglePoint = node.point;
                minDistance = distance; 
            }
            if (node.leftTreeNode != null && node.leftTreeNode.rectangle.distanceSquaredTo(p) < minDistance) 
                queue.enqueue(node.leftTreeNode);
            if (node.rightTreeNode != null && node.rightTreeNode.rectangle.distanceSquaredTo(p) < minDistance) 
                queue.enqueue(node.rightTreeNode);
        }
        return rectanglePoint;
	}

	public static void main(String[] args) {
		
	}
}
