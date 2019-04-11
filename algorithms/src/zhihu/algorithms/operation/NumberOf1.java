package zhihu.algorithms.operation;

/**
 * Author: zhihu
 * Description: 二进制中1的个数
 * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
 * Date: Create in 2019/3/6 20:40
 */
public class NumberOf1 {
    
    /**
     * 算法思路：
     * 循环让(n-1) & n。如果n的二进制表示中有k个1，那么这个方法只需要循环k次即可。
     * 其原理是不断清除n的二进制表示中最右边的1，同时累加计数器，直至n为0。因为从二
     * 进制的角度讲，n相当于在n-1的最低位加上1。举个例子，8（1000）= 7（0111）+ 1（0001），
     * 所以8 & 7 = （1000）&（0111）= 0（0000），清除了8最右边的1（其实就是最高位的1，
     * 因为8的二进制中只有一个1）。再比如7（0111）= 6（0110）+ 1（0001），所以
     * 7 & 6 = （0111）&（0110）= 6（0110），清除了7的二进制表示中最右边的1（也就是最低位的1）。
     *
     * @param n
     * @return
     */
    public static int numberOf1(int n) {
        // 记录数字中1的位数
        int result = 0;
        // 数字的二进制表示中有多少个1就进行多少次操作
        while (0 != n) {
            result++;
            // 从最右边的1开始，每一次操作都使n的最右的一个1变成了0，
            // 即使是符号位也会进行操作。
            n &= (n - 1);
        }
        // 返回求得的结果
        return result;
    }
    
    public static void main(String[] args) {
        System.out.println(numberOf1(7));
    }
}
