package zhihu.algorithms.operation;

/**
 * Author: zhihu
 * Description: 求1+2+3+...+n
 * 求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条
 * 件判断语句（A?B:C）
 * Date: Create in 2019/3/2 7:51
 */
public class SumSolution {
    
    // 调用Java库函数
    // 1+2+3+...+n = (1+n)/2*n = (n²+n)/2
    public static int sumSolution(int n) {
        return (int) (Math.pow(n, 2) + n) >> 1;
    }
    
}
