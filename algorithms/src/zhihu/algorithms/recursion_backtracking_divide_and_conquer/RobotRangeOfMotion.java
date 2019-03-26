package zhihu.algorithms.recursion_backtracking_divide_and_conquer;

/**
 * Author: zhihu
 * Description:
 * 地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下
 * 四个方向移动一格，但是不能进入行坐标和列坐标的数位之和大于k的格子。 例如，当k为18时，机
 * 器人能够进入方格（35,37），因为3+5+3+7 = 18。但是，它不能进入方格（35,38），因为
 * 3+5+3+8 = 19。请问该机器人能够达到多少个格子？
 * Date: Create in 2019/3/26 21:04
 */
public class RobotRangeOfMotion {
    /**
     * 算法思路：
     * 这里采用回溯法，另外还需要计算给定整数上的各个位上数之和。
     * 使用一个访问数组记录是否已经经过该格子。
     * 机器人从(0,0)开始移动，当它准备进入(i,j)的格子时，通过检查坐标的数位来判断机器人是否能够进入。
     * (i,j)是否越界矩阵了
     * (i,j)是否已被统计过了
     * (i,j)的行坐标和列坐标的数位之和是否大于k
     * 如果机器人能进入(i,j)的格子，接着在判断它是否能进入四个相邻的格子(i,j-1),(i,j+1),(i-1,j),(i+1,j)。
     * 因此，可以用回溯法来解决这一问题。
     * @param threshold
     * @param rows
     * @param cols
     * @return
     */
    public static int movingCount(int threshold, int rows, int cols) {
        if (threshold < 0 || rows < 0 || cols < 0) {
            return 0;
        }
        boolean[] visited = new boolean[rows * cols];
        return walk(threshold, rows, cols, 0, 0, visited);
    }
    
    private static int walk(int threshold, int rows, int cols, int i, int j, boolean[] visited) {
        if (!isLegalPosition(rows, cols, i, j) || bitSum(i) + bitSum(j) > threshold || visited[i * cols + j] == true) {
            return 0;
        }
        int res = 1;
        visited[i * cols + j] = true;
        res +=
            walk(threshold, rows, cols, i + 1, j, visited) // 往下
                + walk(threshold, rows, cols, i - 1, j, visited) // 往上
                + walk(threshold, rows, cols, i, j + 1, visited) // 往右
                + walk(threshold, rows, cols, i, j - 1, visited); // 往左
        return res;
    }
    
    // 判断当前(i,j)是否越过矩阵
    private static boolean isLegalPosition(int rows, int cols, int i, int j) {
        return !(i < 0 || j < 0 || i > rows - 1 || j > cols - 1);
    }
    
    // 获得(i,j)的行坐标i或者列坐标j的数位之和
    public static int bitSum(int num) {
        int res = num % 10;
        while ((num /= 10) != 0) {
            res += num % 10;
        }
        return res;
    }
}
