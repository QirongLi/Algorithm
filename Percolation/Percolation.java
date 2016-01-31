public class Percolation {

	private WeightedQuickUnionUF uf;
	private boolean[][] opened;
	private int end_point;
	private int size;
	private static int FULL = 0;

	public Percolation(int N) {
		// create N-by-N grid, with all sites blocked
		uf = new WeightedQuickUnionUF(N * N + 2);
		opened = new boolean[N][N];
		end_point = N * N + 1;
		size = N;
	}

	public void open(int i, int j) {
		// open site (row i, column j) if it is not open already
		if (isOpen(i, j))
			return;

		int point = convertToIndex(i, j);
		if (i > 0 && i < size + 1 && j > 0 && j < size + 1)
			opened[i - 1][j - 1] = true;

		if (i == 1)
			uf.union(point, FULL);
		if (i == size)
			uf.union(point, end_point);

		if (isOpen(i - 1, j) && i > 1)
			uf.union(point, convertToIndex(i - 1, j));

		if (isOpen(i, j - 1) && j > 1)
			uf.union(point, convertToIndex(i, j - 1));

		if (isOpen(i, j + 1) && j < size)
			uf.union(point, convertToIndex(i, j + 1));

		if (isOpen(i + 1, j) && i < size)
			uf.union(point, convertToIndex(i + 1, j));

	}

	public boolean isOpen(int i, int j) {
		if (i > 0 && i < size + 1 && j > 0 && j < size + 1)
			return opened[i - 1][j - 1];
		return false;
	}

	public boolean isFull(int i, int j) {
		int point = convertToIndex(i, j);
		return uf.connected(FULL, point);
	}

	public boolean percolates() {
		// does the system percolate?
		return uf.connected(FULL, end_point);
	}

	private int convertToIndex(int i, int j) {
		return (i - 1) * size + j;
	}

	public static void main(String[] args) {
		// test client (optional)
	}
}
