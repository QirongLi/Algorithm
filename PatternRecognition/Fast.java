import java.util.Arrays;

public class Fast {
	public static void main(String[] args) {

		// rescale coordinates and turn on animation mode
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		StdDraw.show(0);
		StdDraw.setPenRadius(0.01); // make the points a bit larger

		// read in the input
		String filename = args[0];
		In in = new In(filename);
		int N = in.readInt();
		Point[] points = new Point[N];
		for (int i = 0; i < N; i++) {
			int x = in.readInt();
			int y = in.readInt();
			Point p = new Point(x, y);
			points[i] = p;
			p.draw();
		}

		fastMethod(points);

		// display to screen all at once
		StdDraw.show(0);

		// reset the pen radius
		StdDraw.setPenRadius();
	}

	private static void fastMethod(Point[] points) {

		int N = points.length;

		for (int m = 0; m < N; m++) {
			Point p = points[m];
			Point[] pointsCopy = new Point[N - 1];
			for (int i = 0; i < N; i++) {
				if (i < m)
					pointsCopy[i] = points[i];
				if (i > m)
					pointsCopy[i - 1] = points[i];
			}

			Arrays.sort(pointsCopy, p.SLOPE_ORDER);

			int count = 0;
			int index = 0;
			double tmpSlope = p.slopeTo(pointsCopy[0]);

			for (int k = 0; k < pointsCopy.length; k++) {
				if (tmpSlope == p.slopeTo(pointsCopy[k])) {
					count++;
					continue;
				} else {
					if (count >= 3) {
						if (p.compareTo(pointsCopy[index + count - 1]) == 1) {
							StdOut.print(p.toString());
							for (int j = index; j < index + count; j++) {
								StdOut.print(" -> " + pointsCopy[j].toString());
							}
							StdOut.println();
						}
						p.drawTo(pointsCopy[k - 1]);
					}
					count = 1;
					index = k;
					tmpSlope = p.slopeTo(pointsCopy[k]);
				}
			}

			// Deal with the situation that last 4 or more points in the array
			// have the some slope
			if (count >= 3) {
				if (p.compareTo(pointsCopy[index + count - 1]) == 1) { // Do not
																		// print
																		// duplicate
																		// line
																		// points
					StdOut.print(p.toString());
					for (int j = index; j < index + count; j++) {
						StdOut.print(" -> " + pointsCopy[j].toString());
					}
					StdOut.println();
				}
				p.drawTo(pointsCopy[pointsCopy.length - 1]);
			}
		}
	}
}
