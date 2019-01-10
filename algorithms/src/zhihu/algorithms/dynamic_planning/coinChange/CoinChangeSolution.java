package zhihu.algorithms.dynamic_planning.coinChange;

/**
 ** 找零钱 
 ** 已知不同面值的钞票,求如何用最少数量的钞票组成某个金额,求可
 ** 以使用的最少钞票数量,如果任意数量的已知面值钞票都无法组成该
 ** 金额,则返回-1。
 ** 例如：
 ** 钞票面值:[1,2,5];金额:11 = 5 + 5 + 1;需要3张。
 ** 钞票面值:[2];金额:3;无法组成,返回-1。
 ** 钞票面值:[1,2,5,7,10];金额:14 = 7 + 7;需要2张。
 ** 难度：Medium
 * @author zhihu
 *
 */
public class CoinChangeSolution {

	public static int coinChange(int[] coins, int amount) {
		// dp[i]代表金额i的最优解(即最小使用张数),dp[]数组中存储的是金额1至m的最优解(最少使用钞票的数量)
		int[] dp = new int[amount + 1];

		// 初始化dp[]数组
		for (int i = 0; i <= amount; i++) {
			dp[i] = -1;
		}

		// 金额0的最优解为0
		dp[0] = 0;

		// 递推,循环各个面值,找到dp[i]最优解
		for (int m = 1; m <= amount; m++) {
			for (int j = 0; j < coins.length; j++) {
				// 金额为m的钞票 = (m-coins[j]) + coins[j],而dp[coins[j]] ==
				// 1,所以如果dp[m-coins[j]]有最优解,那么dp[m]的值为所有(dp[m-coins[j]] + 1)中的最小值
				if (m >= coins[j] && dp[m - coins[j]] != -1) {
					dp[m] = dp[m] == -1 ? dp[m - coins[j]] + 1 : Math.min(dp[m - coins[j]] + 1, dp[m]);
				}
			}
		}
		return dp[amount];
	}

	public static void main(String[] args) {
		int[] coins = { 1, 2, 5, 7, 10 };
		for (int i = 1; i <= 14; i++) {
			System.out.println("dp[" + i + "]: " + coinChange(coins, i));
		}
	}

}
