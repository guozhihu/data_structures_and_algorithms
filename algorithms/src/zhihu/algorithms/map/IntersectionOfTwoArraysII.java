package zhihu.algorithms.map;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Author: zhihu
 * Description: 两个数组的交叉点
 * https://leetcode.com/problems/intersection-of-two-arrays-ii/
 * Date: Create in 2019/2/22 15:58
 * 难度：Easy
 */
public class IntersectionOfTwoArraysII {
    
    /**
     * 解题思路：
     * 1.将其中一个数组nums1添加到treeMap的key中，value为数组元素中重复的个数，初始的元素个数为1
     * 2.遍历另外一个数组，查看treeMap中的键是否包含该数组中的元素，如果有，将该元素放入list中并且
     * 3.让treeMap中该元素对应的value值减1，如果减1后value为0，将该元素从treeMap中移除
     * 4.将list还原为数组并返回即可
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] intersect(int[] nums1, int[] nums2) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<Integer, Integer>();
        for (int num : nums1) {
            if (!treeMap.containsKey(num)) {
                treeMap.put(num, 1);
            } else {
                treeMap.put(num, treeMap.get(num) + 1);
            }
        }
        
        List<Integer> list = new ArrayList<Integer>();
        for (int num : nums2) {
            if (treeMap.containsKey(num)) {
                list.add(num);
                treeMap.put(num, treeMap.get(num) - 1);
                if (treeMap.get(num) == 0) {
                    treeMap.remove(num);
                }
            }
        }
        
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        
        return res;
    }
}
