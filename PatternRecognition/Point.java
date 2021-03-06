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
	public final Comparator<Point> SLOPE_ORDER; // YOUR DEFINITION HERE

	private final int x; // x coordinate
	private final int y; // y coordinate

	// create the point (x, y)
	public Point(int x, int y) {
		/* DO NOT MODIFY */
		this.x = x;
		this.y = y;
		SLOPE_ORDER = new SlopeOrder();
	}

	private class SlopeOrder implements Comparator<Point> {

		public int compare(Point o1, Point o2) {

			if (slopeTo(o1) > slopeTo(o2))
				return 1;
			if (slopeTo(o1) < slopeTo(o2))
				return -1;

			return 0;
		}

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

	// slope between this point and that point
	public double slopeTo(Point that) {
		double deltaX = that.x - x;
		double deltaY = that.y - y;
		if (deltaX == 0 && deltaY == 0) {
			return Double.NEGATIVE_INFINITY;
		} else if (deltaX == 0) {
			return Double.POSITIVE_INFINITY;
		} else if (deltaY == 0) {
			return +0.0;
		} else
			return deltaY / deltaX;
	}

	// is this point lexicographically smaller than that one?
	// comparing y-coordinates and breaking ties by x-coordinates
	public int compareTo(Point that) {

		if (that.y > y) {
			return 1;
		} else if (that.y == y) {
			if (that.x > x)
				return 1;
			else if (that.x < x) {
				return -1;
			} else {
				return 0;
			}
		}

		return -1;
	}

	// return string representation of this point
	public String toString() {
		/* DO NOT MODIFY */
		return "(" + x + ", " + y + ")";
	}

}
