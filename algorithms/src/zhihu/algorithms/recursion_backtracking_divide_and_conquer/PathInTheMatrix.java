package zhihu.algorithms.recursion_backtracking_divide_and_conquer;

/**
 * Author: zhihu
 * Description: 矩阵中的路径
 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
 * 路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向
 * 下移动一个格子。如果一条路径经过了矩阵中的某一个格子，则之后不能再次进入这
 * 个格子。 例如
 * a b c e
 * s f c s
 * a d e e
 * 这样的3 X 4 矩阵中包含一条字符串
 * "bcced"的路径，但是矩阵中不包含"abcb"路径，因为字符串的第一个字符b占据了
 * 矩阵中的第一行第二个格子之后，路径不能再次进入该格子。
 * Date: Create in 2019/3/26 22:04
 */
public class PathInTheMatrix {
    
    /**
     * 算法思路：
     * 这里采用回溯算法
     * 从当前位置(i,j)(i,j从(0,0)开始)出发匹配字符串，并设置当前位置为已匹配状态，
     *    如果匹配成功，从当前位置分别从上下左右四个方向继续匹配下一个字符，直到匹配完字符串，
     * 匹配过程中如果发现不匹配，则回到上一次的位置，并设置该位置为未访问状态，还需注意当前访问的位置是否越界。
     *
     * @param matrix
     * @param rows
     * @param cols
     * @param str
     * @return
     */
    public static boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        if (null == matrix || matrix.length != rows * cols || null == str) {
            return false;
        }
        // 创建一个布尔数组
        boolean[] visited = new boolean[matrix.length];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // 以矩阵中的每个点作为起点尝试走出str对应的路径
                if (hasPathCore(matrix, rows, cols, i, j, str, 0, visited)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    // 当前在矩阵的(i,j)位置上
    // index -> 匹配到了str中的第几个字符
    private static boolean hasPathCore(char[] matrix, int rows, int cols, int i, int j, char[] str, int index, boolean[] visited) {
        if (index == str.length) {
            return true;
        }
        // 越界或字符不匹配或该位置已在路径上
        if (!match(matrix, rows, cols, i, j, str[index]) || visited[i * cols + j] == true) {
            return false;
        }
        visited[i * cols + j] = true;
        
        // 如果当前位置匹配成功，则继续从相邻格子匹配下一个字符
        boolean res = hasPathCore(matrix, rows, cols, i + 1, j, str, index + 1, visited)
            || hasPathCore(matrix, rows, cols, i - 1, j, str, index + 1, visited)
            || hasPathCore(matrix, rows, cols, i, j + 1, str, index + 1, visited)
            || hasPathCore(matrix, rows, cols, i, j - 1, str, index + 1, visited);
        
        if (res) {
            return true;
        } else {
            //如果相邻格子的字符都没有匹配到下一个字符，则需要回到前一个格子，从而需要把位置的状态重新设定为未访问
            visited[i * cols + j] = false;
        }
        return false;
    }
    
    // 判断当前位置是否合法，即判断是否满足下列两个要求
    //  1.矩阵matrix中的(i,j)位置是否越界
    //  2.矩阵matrix中的(i,j)位置是否是c字符
    private static boolean match(char[] matrix, int rows, int cols, int i, int j, char c) {
        // 判断是否越界
        if (i < 0 || i > rows - 1 || j < 0 || j > cols - 1) {
            return false;
        }
        return matrix[i * cols + j] == c;
    }
    
    public static void main(String[] args) {
        char[] matrix = {'a', 'b', 'c', 'e', 's', 'f', 'c', 's', 'a', 'd', 'e', 'e'};
        int rows = 3;
        int cols = 4;
        char[] str = {'b', 'c', 'c', 'e', 'd'};
        char[] str1 = {'a', 'b', 'c', 'b'};
        System.out.println(hasPath(matrix, rows, cols, str1));
    }
}
