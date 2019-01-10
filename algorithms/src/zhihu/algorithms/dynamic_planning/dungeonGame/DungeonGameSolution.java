package zhihu.algorithms.dynamic_planning.dungeonGame;

/**
 * 地牢游戏
 * 已知一个二维数组，左上角代表骑士的位置，右下角代表公主的位置，二维数组中存储整数，整数可以给骑士增加生命值，负数
 * 会减少骑士的生命值，问骑士初始时至少是多少生命值，才可保证骑士在行走的过程中至少保持生命值为1。（骑士只能向下或
 * 向右行走）
 * 难度：Hard
 * @author zhihu
 *
 */
public class DungeonGameSolution {

	/**
	 * 算法思路：
	 * 从右下向左上递推
	 * dp[i][j]代表若骑士要到达第i行第j列，至少有多少血量，才能在行走的过程中至少保持生命值为1。
	 * @param dungeon
	 * @return
	 */
	public static int dungeonGame(int[][] dungeon) {
		if (dungeon.length == 0) {
			return 0;
		}
		int rowLength = dungeon.length;
		int columnLength = dungeon[0].length;
		int[][] dp = new int[rowLength][columnLength];
		dp[rowLength - 1][columnLength - 1] = Math.max(1, 1 - dungeon[rowLength - 1][columnLength - 1]);
		for (int i = columnLength - 2; i >= 0; i--) {
			dp[rowLength - 1][i] = Math.max(1, dp[rowLength - 1][i + 1] - dungeon[rowLength - 1][i]);
		}
		for (int i = rowLength - 2; i >= 0; i--) {
			dp[i][columnLength - 1] = Math.max(1, dp[i + 1][columnLength - 1] - dungeon[i][columnLength - 1]);
			for (int j = columnLength - 2; j >= 0; j--) {
				int dpMin = Math.min(dp[i + 1][j], dp[i][j + 1]);
				dp[i][j] = Math.max(1, dpMin - dungeon[i][j]);
			}
		}
		return dp[0][0];
	}

	public static void main(String[] args) {
		int[][] dungeon = { { -2, -3, 3 }, { -5, -10, 1 }, { 10, 30, -5 } };
		System.out.println(dungeonGame(dungeon));
	}

}
