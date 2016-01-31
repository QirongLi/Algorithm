public class PercolationStats {

	private Percolation per;
	private double[] results;
	private int times;

	public PercolationStats(int N, int T) {
		if (N <= 0 || T <= 0) {
			throw new IllegalArgumentException(
					"Both N and T should be greater than 0.");
		}
		times = T;
		results = new double[times];
		
		for (int i = 0; i < times; i++) {
			per = new Percolation(N);
			int r = 0;
			while (!per.percolates()) {
				int row = StdRandom.uniform(1, N + 1);
				int col = StdRandom.uniform(1, N + 1);
				if (!per.isOpen(row, col)) {
					per.open(row, col);
					r++;
				}
			}

			double result = (double) r / (N * N);
			results[i] = result;
		}
	}

	public double mean() {
		return StdStats.mean(results);
	}

	public double stddev() {
		return StdStats.stddev(results);
	}

	public double confidenceLo() {
		return mean() - (1.96 * stddev() / Math.sqrt(times));
	}

	public double confidenceHi() {
		return mean() + (1.96 * stddev() / Math.sqrt(times));
	}

	public static void main(String[] args) {
		int N = Integer.valueOf(args[0]);
		int T = Integer.valueOf(args[1]);
		PercolationStats ps = new PercolationStats(N, T);
		StdOut.println("mean = " + ps.mean());
		StdOut.println("stddev = " + ps.stddev());
		StdOut.println("95% confidence interval = " + ps.confidenceLo()
				+ ", " + ps.confidenceHi());
	}
}
