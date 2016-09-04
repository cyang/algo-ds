public class UniquePathsGrid {
	// Given an mxn grid find out how many unique paths can be made from the top
	// left corner cell to the bottom right cell if you can only move down or
	// right
	public static void main(String[] args) {
		int m = 3;
		int n = 7;
		int[][] map = new int[m][n];

		System.out.println(uniquePaths(m, n, map));
	}

	public static int uniquePaths(int m, int n, int[][] map) {
		if (m == 1 && n == 1) {
			return 0;
		}

		if (map[m - 1][n - 1] != 0) {
			return map[m - 1][n - 1];
		}

		if (m > 1 && n > 1) {
			map[m - 1][n - 1] = uniquePaths(m - 1, n, map) + uniquePaths(m, n - 1, map);
		} else {
			map[m - 1][n - 1] = 1;
		}
		return map[m - 1][n - 1];
	}
}
