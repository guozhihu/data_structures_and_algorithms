package zhihu.algorithms.dynamic_planning.walkGridWays;

/**
 * Author: zhihu
 * Description: 给定一个M*N的格子，从左下角走到右上角的走法总数（每次只能向右或向下移动一格）
 * Date: Create in 2019/3/25 22:19
 */
public class walkGridWays {
    
    /**
     * 算法思路：
     * 用dp[i][j]表示移动到坐标(i,j)的走法总数，其中i>=0,j<=n，设dp[m][n]代表从坐标（0,0）到坐标（m,n）的移动方法，则
     * dp[m][n]=dp[m-1][n]+dp[m][n-1].
     * 可知，
     * dp[i][j]=dp[i-1][j]+dp[i][j-1]   if i,j>0
     * dp[i][j]=dp[i][j-1]              if i=0
     * dp[i][j]=dp[i-1][j]              if j=0
     *
     * @param m
     * @param n
     * @return
     */
    
    // 采用动态规划算法
    public static int walkGridWays(int m, int n) {
        int[][] dp = new int[m+1][n+1];
        dp[0][0] = 0;
        for (int j = 1; j <= n; j++) {
            dp[0][j] = 1;
        }
        for (int i = 1; i <= m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
            }
        }
        return dp[m][n];
    }
    
    // 采用递归算法
    public static int walkGridWaysRecur(int m, int n) {
        if (m == 0 && n == 0)
            return 0;
        if (m == 0 || n == 0)
            return 1;
        return walkGridWaysRecur(m, n - 1) + walkGridWaysRecur(m - 1, n);
        
    }
    
    public static void main(String[] args) {
//        int m = 2;
//        int n = 3;

//        int m = 1;
//        int n = 1;
        
        int m = 3;
        int n = 4;
        System.out.println(walkGridWays(m, n));
        System.out.println(walkGridWaysRecur(m, n));
    }
}
