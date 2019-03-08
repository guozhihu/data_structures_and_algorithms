package zhihu.algorithms.heap;

import java.util.*;

/**
 * Author: zhihu
 * Description: 前K个高频元素
 * https://leetcode.com/problems/top-k-frequent-elements/
 * 给定一个非空的整数数组，返回其中出现频率前k高的元素。
 * 示例 1:
 * <p>
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 * <p>
 * 输入: nums = [1], k = 1
 * 输出: [1]
 * 说明：
 * <p>
 * 你可以假设给定的k总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
 * 你的算法的时间复杂度必须优于 O(n log n) , n是数组的大小。
 * Date: Create in 2019/2/23 23:43
 * 难度：Medium
 */
public class TopKFrequentElements {
    
    /**
     * 算法思路：
     * 1.将数组元素存储到TreeMap中，key值为数组中的元素，value为数组中每个元素出现的频数
     * 2.将TreeMap中的元素存储到最小堆中，当最小堆中元素个数小于k个，直接存入TreeMap中的元素，
     * 当最小堆中元素个数大于等于k个后，比较TreeMap中元素出现的频数与堆顶元素出现的频数的大小，
     * 如果大于堆顶元素，从堆中删除堆顶元素，然后再存入TreeMap中出来的元素
     * @param nums
     * @param k
     * @return
     */
    public static List<Integer> topKFrequent(int[] nums, int k) {
        
        // 用map存储数组中每个元素出现的频数
        TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        
        // 使用最小堆存储前k个频数最大元素
        Queue<Integer> priorityQueue = new PriorityQueue<Integer>((a, b) -> map.get(a) - map.get(b));
        for (Integer key : map.keySet()) {
            if (k > 0) {
                priorityQueue.add(key);
                k--;
            } else if (map.get(key) > map.get(priorityQueue.peek())) {
                priorityQueue.remove();
                priorityQueue.add(key);
            }
        }
        
        List<Integer> resList = new ArrayList<Integer>();
        
        while (!priorityQueue.isEmpty()) {
            resList.add(priorityQueue.remove());
        }
        
        return resList;
    }
    
    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        topKFrequent(nums, k);
        for (int num : topKFrequent(nums, k)) {
            System.out.print(num + " ");
        }
    }
}
