public class UniquePathsGrid {
	// Given an mxn grid find out how many unique paths can be made from the top
	// left corner cell to the bottom right cell if you can only move down or right

	public static void main(String[] args) {
		int m = 3;
		int n = 7;
		System.out.println(uniquePaths(m, n));
	}

	public static int uniquePaths(int m, int n) {
		if (m == 1 && n == 1) {
			return 0;
		}
		if (m > 1 && n > 1) {
			return uniquePaths(m - 1, n) + uniquePaths(m, n - 1);
		} else {
			return 1;
		}
	}
}
