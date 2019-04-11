package zhihu.algorithms.array;

/**
 * Author: zhihu
 * Description: 构建乘积数组
 * 给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],其中B中的
 * 元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。不能使用除法。
 * Date: Create in 2019/4/11 19:00
 */
public class BuildingProductArray {
    
    /**
     * 算法思路：
     * B0      1   A1  A2  A3  A4  An-1
     * B1      A0  1   A2  A3  A4  An-1
     * B2      A0  A1  1   A3  A4  An-1
     * B3      A0  A1  A2  1   A4  An-1
     * B4      A0  A1  A2  A3  1   An-1
     * Bn-1    A0  A1  A2  A3  A4  1
     * <p>
     * B[i]的值可以看做上图的矩阵中每行的乘积。
     * <p>
     * 下三角用连乘可以很容易求得，先算下三角中的连乘，即先计算出B[i]中的一部分，
     * 然后将上三角中的数也乘进去。这样一来就只需要两个循环就可以解决这个问题。
     * 时间复杂度是O(n);
     * <p>
     * 其实你只需要知道这个是形成一个矩阵，然后每一行是用来计算B[i],每一行的内
     * 容则是A[0]到A[n-1]。利用上三角和下三角进行计算。
     *
     * @param A
     * @return
     */
    public static int[] multiply(int[] A) {
        int length = A.length;
        int[] B = new int[length];
        B[0] = 1;
        // 先计算下三角中的连乘
        for (int i = 1; i < length; i++) {
            B[i] = B[i - 1] * A[i - 1];
        }
        
        int tmp = 1;
        // 计算上三角中的连乘，然后再乘上之前所得的下三角的连乘
        for (int j = length - 2; j >= 0; j--) {
            tmp *= A[j + 1];
            B[j] *= tmp;
        }
        
        return B;
    }
}
