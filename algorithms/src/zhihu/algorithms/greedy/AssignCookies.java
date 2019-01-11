package zhihu.algorithms.greedy;

import java.util.Arrays;

/**
 * Author: zhihu
 * Description: 分糖果
 * 已知一些孩子和一些糖果，每个孩子有需求因子g，每个糖果有大小s，当某个糖果的大小s >= 某个孩子
 * 的需求因子g时，代表该糖果可以满足该孩子；求使用这些糖果，最多能满足多少孩子？（注意，每个孩
 * 子最多只能用1个糖果满足）
 * <p>
 * 例如，需求因子数组g = [5, 10, 2, 9, 15, 9];糖果大小数组s = [6, 1, 20, 3, 8];最多可以满
 * 足3个孩子。
 * Date: Create in 2019/1/11 16:48
 */
public class AssignCookies {
    
    /**
     * 算法思路
     * 1.对需求因子数组g与糖果大小数组s进行从小到大的排序。
     * 2.按照从小到大的顺序将糖果尝试能否满足每个孩子，每个糖果只尝试一次；若成功，则换下一个孩子尝试；直到发现没更多的孩子
     * 或者没更多的糖果，循环结束
     * @param g
     * @param s
     * @return
     */
    public static int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int childIndex = 0;
        int cookieIndex = 0;
        while (childIndex < g.length && cookieIndex < s.length) {
            // 找出满足每个孩子的最小糖果
            childIndex = s[cookieIndex++] >= g[childIndex] ? childIndex + 1 : childIndex;
        }
        return childIndex;
    }
    
    public static void main(String[] args) {
        int[] g = {5, 10, 2, 9, 15, 9};
        int[] s = {6, 1, 20, 3, 8};
        System.out.println(findContentChildren(g, s));
    }
}
