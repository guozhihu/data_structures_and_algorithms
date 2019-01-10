package zhihu.algorithms.dynamic_planning.climbingStairs;

/** 
 ** 爬楼梯
 ** 在爬楼梯时，每次可以向上走1阶台阶或2阶台阶，现在有n阶楼梯，请问共有多少种走法可以登顶？ 
 ** 难度：Easy
 * @author zhihu
 *
 */
public class ClimbingStairsSolution {

	// 暴力搜索,回溯法(利用递归,效率很低)
	public static int climbingStairsRecur(int n) {
		if (n == 1 || n == 2) {
			return n;
		}
		// 走了一阶台阶之后剩余台阶的走法 + 走了两阶台阶之后剩余台阶的走法
		return climbingStairsRecur(n - 1) + climbingStairsRecur(n - 2);
	}

	// 利用动态规划（推荐）
	public static int climbingStairs(int n) {
		// 设置递推数组dp[0...n],dp[i]代表到达第i阶有多少种走法，初始化数组元素为0;
		int[] dp = new int[n + 3];
		// 可知,到达第1阶台阶有1种走法，到达第2阶台阶有2种走法
		dp[1] = 1;
		dp[2] = 2;
		// 利用i循环地推从第3阶到第n阶的结果
		// 到达第i阶的走法数量 = 到达第i-1阶的走法数量 + 到达第i-2阶的走法数量
		for (int i = 3; i <= n; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}
		return dp[n];
	}

	public static void main(String[] args) {
		System.out.println(climbingStairsRecur(15));
		System.out.println(climbingStairs(15));
	}

}
