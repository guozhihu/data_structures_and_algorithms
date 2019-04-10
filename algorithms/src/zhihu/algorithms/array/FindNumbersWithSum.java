package zhihu.algorithms.array;

import java.util.ArrayList;

/**
 * Author: zhihu
 * Description: 和为S的两个数字
 * <p>
 * 输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，
 * 如果有多对数字的和等于S，输出两个数的乘积最小的。
 * Date: Create in 2019/4/10 15:51
 */
public class FindNumbersWithSum {
    
    /**
     * 数列满足递增，设两个头尾两个指针i和j，
     *  1、若array[i] + array[j] == sum，就是答案（相差越远乘积越小）
     *  2、若array[i] + array[j] > sum，array[j]肯定不是答案之一（前面已得出i前面的数已是不可能），j -= 1
     *  3、若array[i] + array[j] < sum，array[i]肯定不是答案之一（前面已得出j后面的数已是不可能），i += 1
     * <p>
     *  时间复杂度  O(n)
     * <p>
     * 其实主要思想是两个数的乘积要最小，那么这个时候我们就需要知道，当一个有序的序列，
     * 两个数相隔越远，最后得到的数最小。所以我们设定两个指针，分别从序列的两头出发。
     *
     * @param array
     * @param sum
     * @return
     */
    public static ArrayList<Integer> findNumbersWithSum(int[] array, int sum) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        int i = 0;
        int j = array.length - 1;
    
        while (i < j) {
            if (array[i] + array[j] == sum) {
                result.add(array[i]);
                result.add(array[j]);
                break;
            }
        
            if (array[j] > sum - array[i]) {
                j--;
            }
        
            if (array[j] < sum - array[i]) {
                i++;
            }
        }
        return result;
    }
    
}
