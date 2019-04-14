package zhihu.algorithms.dynamic_planning.RectCover;

/**
 * Author: zhihu
 * Description: 矩形覆盖
 * 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。
 * 请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
 * Date: Create in 2019/3/5 23:17
 */
public class RectCoverSolution {
    public static int rectCover(int n) {
        if (n == 0) {
            return 0;
        }
        // dp[i]表示大矩形的长度n为i时有多少种小矩形的覆盖方法
        int[] dp = new int[n+2];
        // 当n为1时，小矩形覆盖大矩形的方法有1种
        dp[1] = 1;
        // 当n为2时，小矩形覆盖大矩形的方法有2种
        dp[2] = 2;
        // 当n >= 3时，小矩形覆盖大矩形的方法有dp[i-1] + dp[i-2]种
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }
}
