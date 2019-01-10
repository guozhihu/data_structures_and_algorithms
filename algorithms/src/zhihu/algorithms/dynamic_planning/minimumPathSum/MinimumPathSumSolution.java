package zhihu.algorithms.dynamic_planning.minimumPathSum;

/**
 * 最小路径和
 * 已知一个二维数组，其中存储了非负整数，找到从左上角到右下角的一条路径，使得路径上的和最小。（移动过程中只能向下或向右） 
 * 难度：Medium
 * @author zhihu
 *
 */
public class MinimumPathSumSolution {

	public static int minimumPathSum(int[][] grid) {
		if (0 == grid.length) {
			return 0;
		}
		int rowLength = grid.length;
		int columnLength = grid[0].length;
		// dp[i][j]为到达位置(i,j)时的最优解(最小值)
		int[][] dp = new int[rowLength][columnLength];
		dp[0][0] = grid[0][0];

		for (int i = 1; i < columnLength; i++) {
			dp[0][i] = dp[0][i - 1] + grid[0][i];
		}

		for (int i = 1; i < rowLength; i++) {
			for (int j = 0; j < columnLength; j++) {
				dp[i][j] = j == 0 ? dp[i - 1][j] + grid[i][j] : Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
			}
		}
		return dp[rowLength - 1][columnLength - 1];
	}

	public static void main(String[] args) {
		int[][] grid = { { 1, 3, 1 }, { 1, 5, 1 }, { 4, 2, 1 } };
		System.out.println(minimumPathSum(grid));
	}

}
