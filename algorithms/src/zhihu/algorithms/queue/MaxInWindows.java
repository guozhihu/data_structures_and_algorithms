package zhihu.algorithms.queue;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Author: zhihu
 * Description: 滑动窗口的最大值
 * 给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。
 * 例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，那么
 * 一共存在6个滑动窗口，他们的最大值分别为{4,4,6,6,6,5}；针对
 * 数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个：
 * {[2,3,4],2,6,2,5,1}， {2,[3,4,2],6,2,5,1}，
 * {2,3,[4,2,6],2,5,1}， {2,3,4,[2,6,2],5,1}，
 * {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。
 * Date: Create in 2019/4/7 17:56
 */
public class MaxInWindows {
    
    /**
     * 算法思路1：暴力法
     * 1.遍历求出每个滑动窗口
     * 2.在每个滑动窗口中求出最大值，记录下来
     * 3.注意边界情况的处理，如：输入的数组为null，或者为空；滑动窗口的大小为零，或者大于数组长度等
     *
     * @param num
     * @param size
     * @return
     */
    public static ArrayList<Integer> maxInWindows(int[] num, int size) {
        ArrayList<Integer> windowMax = new ArrayList<Integer>();
        
        int numLength = num.length;
        if (num == null || numLength == 0 || size == 0 || numLength < size) {
            return windowMax;
        }
        
        if (numLength == size) {
            windowMax.add(getMaxNum(num, 0, numLength - 1));
            return windowMax;
        }
        
        for (int i = 0; i < numLength - size + 1; i++) {
            windowMax.add(getMaxNum(num, i, i + size - 1));
        }
        return windowMax;
    }
    
    private static int getMaxNum(int[] num, int index, int end) {
        int max = num[index];
        for (int i = index + 1; i <= end; i++) {
            max = num[i] > max ? num[i] : max;
        }
        return max;
    }
    
    /**
     * 算法思路2：
     * 1. 滑动窗口应当是队列，但为了得到滑动窗口的最大值，队列序可以从两端删除元素，因此使用双端队列。
     * 2. 对新来的元素k，将其与双端队列中的元素相比较, 前面比k小的，直接移出队列（因为不再可能成为后面滑动窗口的最大值了!
     * 3. 前面比k大的X，比较两者下标，判断X是否已不在窗口之内，不在了，直接移出队列。队列的第一个元素是当前滑动窗口中的最大值
     *
     * @param num
     * @param size
     * @return
     */
    public static ArrayList<Integer> maxInWindows_(int[] num, int size) {
        ArrayList<Integer> windowMax = new ArrayList<Integer>();
        
        int numLength = num.length;
        if (num == null || numLength == 0 || size == 0 || numLength < size) {
            return windowMax;
        }
        
        Deque<Integer> deque = new LinkedList<Integer>();
        
        // 窗口还没有被填满时，找最大值的索引
        for (int i = 0; i < size; i++) {
            // 如果索引对应的值比之前存储的索引值对应的值大或者相等，就删除之前存储的值
            while (!deque.isEmpty() && num[deque.getLast()] <= num[i]) {
                deque.removeLast();
            }
            deque.addLast(i);
        }
        
        // 窗口已经被填满了
        for (int i = size; i < numLength; i++) {
            // 第一个窗口的最大值保存
            windowMax.add(num[deque.getFirst()]);
            
            // 如果索引对应的值比之前存储的索引值对应的值大或者相等，就删除之前存储的值
            while (!deque.isEmpty() && num[i] >= num[deque.getLast()]) {
                deque.removeLast();
            }
            
            // 删除已经滑出窗口的数据对应的下标
            if (!deque.isEmpty() && deque.getFirst() <= (i - size)) {
                deque.removeFirst();
            }
            
            // 可能的最大的下标索引入队
            deque.addLast(i);
        }
        
        // 最后一个窗口最大值入队
        windowMax.add(num[deque.getFirst()]);
        
        return windowMax;
    }
    
    public static void main(String[] args) {
        int[] numArr = {2, 3, 4, 2, 6, 2, 5, 1};
        // List<Integer> list = maxInWindows(numArr, 3);
        List<Integer> list = maxInWindows_(numArr, 3);
        for (Integer num : list) {
            System.out.print(num + "\t");
        }
    }
}
