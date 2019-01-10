package zhihu.algorithms.dynamic_planning.triangle;

import java.util.ArrayList;
import java.util.List;

/**
 * * 三角形
 * * 给定一个二维数组,其保存了一个数字三角形,求从数字三角形顶端到底端
 * * 各数字和最小的路径之和,每次可以向下走相邻的两个位置
 * * 难度：Medium
 *
 * @author zhihu
 */
public class TriangleSolution {
    
    /**
     * * 算法思路：
     * * 1.设置一个二维数组，最优值三角形dp[][],并初始化数组元素为0。dp[i][j]代表了从底向上递推时,走到一个三角形第i行第j列的最优解
     * * 2.从三角形的底面向三角形上方进行进行动态规划：
     * * 	a.动态规划边界条件：底面上的最优值即为数字三角形的最后一层。
     * * 	b.利用i循环,从倒数第二层递推至第一层,对于每层的各列,进行动态规划递推：
     * *		第i行，第j列的最优解为dp[i][j],可到达(i,j)的两个位置的最优解dp[i+1][j]、dp[i+1][j+1], 则dp[i][j] = min(dp[i+1][j], dp[i+1][j+1]) + triangle[i][j]
     * * 3.返回dp[0][0]
     *
     * @param triangle
     * @return
     */
    public static int triangle(List<List<Integer>> triangle) {
        if (0 == triangle.size()) {
            return 0;
        }
        
        int[][] dp = new int[triangle.size()][];
        dp[triangle.size() - 1] = new int[triangle.get(triangle.size() - 1).size()];
        
        for (int i = 0; i < triangle.get(triangle.size() - 1).size(); i++) {
            dp[triangle.size() - 1][i] = triangle.get(triangle.size() - 1).get(i);
        }
       
        for (int i = triangle.size() - 2; i >= 0; i--) {
            dp[i] = new int[triangle.get(i).size()];
            for (int j = 0; j < triangle.get(i).size(); j++) {
                dp[i][j] =  Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0][0];
    }
    
    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<List<Integer>>();
        List<Integer> tri1 = new ArrayList<Integer>();
        tri1.add(2);
        
        List<Integer> tri2 = new ArrayList<Integer>();
        tri2.add(3);
        tri2.add(4);
        
        List<Integer> tri3 = new ArrayList<Integer>();
        tri3.add(6);
        tri3.add(5);
        tri3.add(7);
        
        List<Integer> tri4 = new ArrayList<Integer>();
        tri4.add(4);
        tri4.add(1);
        tri4.add(8);
        tri4.add(3);
        
        triangle.add(tri1);
        triangle.add(tri2);
        triangle.add(tri3);
        triangle.add(tri4);
        
        System.out.println(triangle(triangle));
    }
    
}
