package zhihu.algorithms.dynamic_planning.longestIncreasingSubsequence;

import java.util.Stack;

/**
 ** 最长上升子序列
 ** 已知一个未排序数组，求这个数组最长上升子序列的长度。
 ** 例如:[1,3,2,3,1,4],
 ** 其中有很多上升子序列,如[1,3]、[1,2,3]、[1,2,3,4]等,其中最长的上升子序列长度为4。
 ** 分别考虑O(n²)与O(n*logn)两种复杂度算法
 ** 难度:Medium,Hard
 * @author zhihu
 *
 */
public class LongestIncreasingSubsequenceSolution {

	/**
	 ** 解法一(时间复杂度为O(n²))
	 ** 算法思路：
	 ** 设置动态规划数组dp[],第i个状态dp[i]代表以第i个元素结尾的最长上升子序列的长度
	 ** 动态规划边界：dp[0] = 1;
	 ** 初始化最长上升子序列的长度LIS=1;
	 ** 从1到n-1,循环i,计算dp[i]:
	 **		从0~(i-1),循环j,若nums[i] > nums[j],说明nums[i]可放置在nums[j]的后面,组成最长上升子序列
	 **     若dp[i] < dp[j] + 1
	 **     	dp[i] = dp[j] + 1
	 ** LIS为dp[0],dp[1],...,dp[i],...,dp[n-1]中最大的。
	 * @param nums
	 * @return
	 */
	public static int LIS1(int[] nums) {
		if (0 == nums.length) {
			return 0;
		}
		int[] dp = new int[nums.length];
		dp[0] = 1;
		int LIS = 1;
		for (int i = 1; i < nums.length; i++) {
			dp[i] = 1;
			for (int j = 0; j <= i; j++) {
				dp[i] = (nums[i] > nums[j]) && (dp[j] + 1 > dp[i]) ? dp[j] + 1 : dp[i];
			}
			LIS = dp[i] > LIS ? dp[i] : LIS;
		}
		return LIS;
	}

	/**
	 ** 解法二
	 ** 算法思路：
	 ** 1.设置一个栈,将nums[0]push到栈中
	 ** 2.从1~(n-1)遍历nums数组:
	 ** 	若nums[i] > 栈顶,将nums[i]push至栈中。
	 **		否则从栈底遍历至栈顶,若遍历时,栈中元素大于等于nums[i],使用nums[i]替换该元素,并跳出循环。
	 ** 3.返回栈的大小
	 * @param nums
	 * @return
	 */
	public static int LIS2(int[] nums) {
		if (0 == nums.length) {
			return 0;
		}
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(nums[0]);
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] > stack.peek()) {
				stack.push(nums[i]);
			} else {
				for (int j = 0; j < stack.size(); j++) {
					if (stack.get(j) >= nums[i]) {
						stack.setElementAt(nums[i], j);
						break;
					}
				}
			}
		}
		return stack.size();
	}

	/**
	 * 解法三：
	 * 算法思路和解法二一致,在其基础上进行优化,使该算法的时间复杂度变为O(n*logn)
	 * @param nums
	 * @return
	 */
	public static int LIS3(int[] nums) {
		if (0 == nums.length) {
			return 0;
		}
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(nums[0]);
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] > stack.peek()) {
				stack.push(nums[i]);
			} else {
				stack.setElementAt(nums[i], binarySearch(stack, nums[i]));
			}
		}
		return stack.size();
	}

	// 二分搜索的时间复杂度为O(logn)
	private static int binarySearch(Stack<Integer> stack, int target) {
		int l = 0;
		int r = stack.size() - 1;
		while (l < r) {
			int mid = l + ((r - l) >> 2);
			if (stack.get(mid) == target) {
				return mid;
			} else if (stack.get(mid) > target) {
				if (mid == 0 || stack.get(mid - 1) < target) {
					return mid;
				}
				r = mid - 1;
			} else {
				if (mid == stack.size() - 1 || stack.get(mid + 1) > target) {
					return mid + 1;
				}
				l = mid + 1;
			}
		}
		return 0;
	}

	public static void main(String[] args) {
		int[] nums = { 1, 3, 2, 3, 1, 4, 0, 0, 3, 4, 1, 5, 6, 8, 8, 9 };
		System.out.println(LIS1(nums));
		System.out.println(LIS2(nums));
		System.out.println(LIS3(nums));
	}

}
