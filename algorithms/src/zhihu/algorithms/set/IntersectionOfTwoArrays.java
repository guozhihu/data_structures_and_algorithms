package zhihu.algorithms.set;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * Author: zhihu
 * Description: 两个数组的交集
 * https://leetcode.com/problems/intersection-of-two-arrays/
 * Date: Create in 2019/2/22 15:40
 * 难度Easy
 */
public class IntersectionOfTwoArrays {
    
    /**
     * 算法思路：
     * 将其中一个数组nums1放入TreeSet集合中
     * 遍历另外一个数组nums2，判断TreeSet集合中是否存在该数组的元素
     * 如果存在，将此数组元素加入ArrayList中，然后从TreeSet集合中删除该数组元素
     * 将ArrayList中的数组还原为一个res数组中即可
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] intersection(int[] nums1, int[] nums2) {
        TreeSet<Integer> set = new TreeSet<Integer>();
        for (int num : nums1) {
            set.add(num);
        }
        List<Integer> list = new ArrayList<Integer>();
        for (int num : nums2) {
            if (set.contains(num))
                list.add(num);
            set.remove(num);
        }
        
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}
