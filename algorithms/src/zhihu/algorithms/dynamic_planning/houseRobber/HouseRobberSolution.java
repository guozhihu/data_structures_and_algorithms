package zhihu.algorithms.dynamic_planning.houseRobber;

/**
 ** 打家劫舍 
 ** 在一条直线上，有n个房屋，每个房屋中有数量不等的财宝，有一个盗贼从房屋中盗取财宝，由于
 ** 房屋中有报警器，如果同时从相邻的两个房屋中盗取财宝就会触发报警器。问在不触发报警器的前
 ** 提下，最多可获取多少财宝？ 
 ** 难度：Easy 
 * @author zhihu
 *
 */
public class HouseRobberSolution {

	public static int houseRobber(int[] houseNums) {
		// 没有房间
		if (0 == houseNums.length) {
			return 0;
		}
		// 只有一个房间
		if (1 == houseNums.length) {
			return houseNums[0];
		}
		int[] dp = new int[houseNums.length];
		// 前1个房间的最优解
		dp[0] = houseNums[0];
		// 前2个房间的最优解
		dp[1] = Math.max(houseNums[1], houseNums[0]);

		// 前i个房间的最优解
		for (int i = 2; i < houseNums.length; i++) {
			/**
			 ** 从第i个房间（i >= 3），这里有两个选择，要么考虑第i个房间，要么不考虑第i个房间
			 ** 若考虑第i个房间：则最优解为第i个房间  + 前（i-2）个房间的最优解--->dp[i-2] + houseNums[i]
			 ** 若不考虑第i个房间：则最优解为前（i-1）个房间的最优解--->dp[i-1]
			 */
			dp[i] = Math.max(dp[i - 2] + houseNums[i], dp[i - 1]);
		}
		return dp[houseNums.length - 1];
	}

	public static void main(String[] args) {
		int[] houseNums = new int[] { 5, 2, 6, 3, 1, 7 };
		System.out.println(houseRobber(houseNums));
	}

}
