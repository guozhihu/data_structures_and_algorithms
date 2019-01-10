package zhihu.algorithms.dynamic_planning.maximumSubarray;

/**
 ** 最大子段和 
 ** 给定一个数组,求这个数组的连续子数组中,最大的那一段的和。
 ** 如数组[-2,1,-3,4,-1,2,1,-5,4],连续子数组,如:[-2,1],[1,-3,4,-1],[4,-1,2,1],...,[-2,1,-3,4,-1,2,1,-5,4],和最大的是[4,-1,2,1],为6。
 ** 难度：Easy 
 * @author zhihu
 *
 */
public class MaximumSubarraySolution {

	public static int maximumSubarray(int[] arr) {
		/**
		 ** 算法思路：
		 ** 将求n个数的数组的最大子段和，转换为分别求出以第1个、第2个、...、第i个、...、第n个数字结尾的最大子段和，
		 ** 再找出这n个结果中最大的，即为结果。
		 */
		int[] dp = new int[arr.length];
		// 可知，以第1个数字结尾的子段和最大为数组第1个元素的值
		dp[0] = arr[0];
		int max_res = dp[0];
		for (int i = 1; i < arr.length; i++) {
			// dp[i]表示以第i个数字结尾的子段和最大的结果,如果以第n-1个数字结尾的最大子段和(即dp[i-1])的结果大于0，则以dp[i]的结果最大值就是dp[i-1]+arr[i],否则为arr[i]
			// dp[i] = Math.max(dp[i - 1] + arr[i], arr[i]);
			dp[i] = dp[i - 1] > 0 ? dp[i - 1] + arr[i] : arr[i];
			max_res = dp[i] > max_res ? dp[i] : max_res;
		}
		return max_res;
	}

	public static void main(String[] args) {
		int[] arr = new int[] { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
		System.out.println(maximumSubarray(arr));
	}

}
