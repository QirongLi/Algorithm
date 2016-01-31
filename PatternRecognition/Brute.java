public class Brute {

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

		// Quick3way.sort(points);
		// Arrays.sort(points);

		for (int m = 0; m < N; m++) {
			for (int n = m + 1; n < N; n++) {
				for (int j = n + 1; j < N; j++) {
					for (int k = j + 1; k < N; k++) {
						Point p1 = points[m];
						Point p2 = points[n];
						Point p3 = points[j];
						Point p4 = points[k];
						if ((p1.slopeTo(p2) == p1.slopeTo(p3))
								&& (p1.slopeTo(p2) == p1.slopeTo(p4))) {
							StdOut.println(p1.toString() + " -> "
									+ p2.toString() + " -> " + p3.toString()
									+ " -> " + p4.toString());
							p1.drawTo(p4);
						}
					}
				}

			}
		}

		// display to screen all at once
		StdDraw.show(0);

		// reset the pen radius
		StdDraw.setPenRadius();
	}
}
