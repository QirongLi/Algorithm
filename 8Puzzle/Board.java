import java.util.Arrays;

public class Board {
	private int[][] board;
	private int N;
	private int hamming = 0;
	private int manhattan = 0;

	public int[][] getBoard() {
		return board;
	}

	public int getHamming() {
		return hamming;
	}

	public int getManhattan() {
		return manhattan;
	}

	public int getN() {
		return N;
	}

	// construct a board from an N-by-N array of blocks
	// (where blocks[i][j] = block in row i, column j)
	public Board(int[][] blocks) {
		N = blocks.length;
		board = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				board[i][j] = blocks[i][j];
			}
		}
	}

	// board dimension N
	public int dimension() {
		return N;
	}

	// number of blocks out of place
	public int hamming() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int target = i * N + j + 1;
				if (board[i][j] != target)
					hamming++;
			}
		}
		return hamming;
	}

	// sum of Manhattan distances between blocks and goal
	public int manhattan() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int value = board[i][j];
				int x = value / N;
				int y = value % N - 1;
				int diff = x + y - i - j;
				if (diff < 0)
					diff = -diff;
				manhattan += diff;
			}
		}
		return manhattan;
	}

	// is this board the goal board?
	public boolean isGoal() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int target = i * N + j + 1;
				if (board[i][j] != target)
					return false;
			}
		}
		return true;
	}

	// a board that is obtained by exchanging two adjacent blocks in the
	// same row
	public Board twin() {
		int[][] boardCopy = copyBoard();
		if (boardCopy[0][0] != 0 && boardCopy[0][1] != 0) {
			boardCopy[0][0] = board[0][1];
			boardCopy[0][1] = board[0][0];
		} else {
			boardCopy[1][0] = board[1][1];
			boardCopy[1][1] = board[1][0];
		}
		return new Board(boardCopy);
	}

	private int[][] copyBoard() {
		int[][] boardCopy = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				boardCopy[i][j] = board[i][j];
			}
		}
		return boardCopy;
	}

	// does this board equal y?
	public boolean equals(Object y) {
		return y instanceof Board && this.N == (((Board) y).getN())
				&& this.hamming == (((Board) y).getHamming())
				&& this.manhattan == (((Board) y).getManhattan())
				&& Arrays.deepEquals(this.board, (((Board) y).getBoard()));
	}

	// all neighboring boards
	public Iterable<Board> neighbors() {
		return null;
	}

	// string representation of this board (in the output format specified
	// below)
	public String toString() {
		return null;
	}

	// unit tests (not graded)
	public static void main(String[] args) {
	}
}