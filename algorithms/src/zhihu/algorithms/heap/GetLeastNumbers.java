package zhihu.algorithms.heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Author: zhihu
 * Description: 最小的k个数
 * 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。
 * Date: Create in 2019/3/1 22:54
 */
public class GetLeastNumbers {
    
    /**
     * 算法思路：
     * 创建一个大小为K的大根堆，在添加k个元素之后，如果大根堆堆顶元素的值大于待插入的值，那么需要将大顶堆的堆顶元素去除。再
     * 插入待插入的元素
     * @param input
     * @param k
     * @return
     */
    public static ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        if (k <= 0 || k > input.length)
            return new ArrayList<>();
        //创建大根堆
        PriorityQueue<Integer> maxheap = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for (Integer s : input) {
            maxheap.add(s);
            if (maxheap.size() > k) {
                maxheap.poll();
            }
        }
        ArrayList<Integer> arrayList = new ArrayList<>(maxheap);
        return arrayList;
    }
}
