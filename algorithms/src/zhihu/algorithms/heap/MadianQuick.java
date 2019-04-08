package zhihu.algorithms.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Author: zhihu
 * Description: 随时找到数据流的中位数
 * 有一个源源不断地吐出整数的数据流，假设你有足够的空间来保存吐出的数。
 * 请设计一个名叫MedianHolder的结构， MedianHolder可以随时取得之前吐
 * 出所有数的中位数。
 * 【要求】
 * 1．如果MedianHolder已经保存了吐出的N个数，那么任意时刻
 * 将一个新数加入到MedianHolder的过程，其时间复杂度是 O(logN)。
 * 2．取得已经吐出的N个数整体的中位数的过程，时间复杂度为 O(1)。
 * Date: Create in 2019/3/4 15:44
 */
public class MadianQuick {
    
    /**
     * 算法思路：
     * 1.创建一个最大堆和一个最小堆，最大堆保存的数据都小于最小堆保存的数据，这样即使最大堆、最小堆两边内部的数据没有排序，
     * 也可以根据最大堆最大的数及最小堆最小的数得到中位数。
     * 2.当插入一个新数据时
     *    如果当前最大堆为空，将要插入的数据插入最大堆中
     *    如果最大堆非空并且要插入的数据小于最大堆的堆顶元素，则插入最大堆中，反之插入最小堆中
     *    每插入一个新的数据，都要判断最大堆中的元素个数和最小堆中的元素个数是否不均匀
     *        如果最大堆中的元素个数 == 最小堆中的元素个数 + 2，则弹出最大堆的堆顶元素并将其插入最小堆中
     *        如果最小堆中的元素个数 == 最大堆中的元素个数 + 2，则弹出最小堆的堆顶元素并将其插入最大堆中
     * 3.计算中位数
     * 如果最大堆的元素个数 == 最小堆的元素个数，则返回最大堆的堆顶元素与最小堆的堆顶元素的和 / 2即可
     * 如果最大堆的元素个数 > 最小堆的元素个数，则返回最大堆的堆顶元素即可
     * 如果最小堆的元素个数 > 最大堆的元素个数，则返回最小堆的堆顶元素即可
     */
    public static class MedianHolder {
        // 最大堆
//        private PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o2 - o1;
//            }
//        });
        private PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>((o1, o2) -> o2 - o1);
        // 最小堆,默认就是最小堆
        private PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        
        private void modifyTwoHeapsSize() {
            if (this.maxHeap.size() == this.minHeap.size() + 2) {
                this.minHeap.add(this.maxHeap.poll());
            }
            if (this.minHeap.size() == this.maxHeap.size() + 2) {
                this.maxHeap.add(this.minHeap.poll());
            }
        }
        
        public void addNumber(int num) {
            if (this.maxHeap.isEmpty()) {
                this.maxHeap.add(num);
                return;
            }
            if (this.maxHeap.peek() >= num) {
                this.maxHeap.add(num);
            } else {
                if (this.minHeap.isEmpty()) {
                    this.minHeap.add(num);
                    return;
                }
                if (this.minHeap.peek() > num) {
                    this.maxHeap.add(num);
                } else {
                    this.minHeap.add(num);
                }
            }
            modifyTwoHeapsSize();
        }
        
        public Integer getMedian() {
            int maxHeapSize = this.maxHeap.size();
            int minHeapSize = this.minHeap.size();
            if (maxHeapSize + minHeapSize == 0) {
                return null;
            }
            Integer maxHeapHead = this.maxHeap.peek();
            Integer minHeapHead = this.minHeap.peek();
            if (((maxHeapSize + minHeapSize) & 1) == 0) {
                return (maxHeapHead + minHeapHead) / 2;
            }
            return maxHeapSize > minHeapSize ? maxHeapHead : minHeapHead;
        }
        
    }
    
    // for test
    public static int[] getRandomArray(int maxLen, int maxValue) {
        int[] res = new int[(int) (Math.random() * maxLen) + 1];
        for (int i = 0; i != res.length; i++) {
            res[i] = (int) (Math.random() * maxValue);
        }
        return res;
    }
    
    // for test, this method is ineffective but absolutely right
    public static int getMedianOfArray(int[] arr) {
        int[] newArr = Arrays.copyOf(arr, arr.length);
        Arrays.sort(newArr);
        int mid = (newArr.length - 1) / 2;
        if ((newArr.length & 1) == 0) {
            return (newArr[mid] + newArr[mid + 1]) / 2;
        } else {
            return newArr[mid];
        }
    }
    
    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        boolean err = false;
        int testTimes = 200000;
        for (int i = 0; i != testTimes; i++) {
            int len = 30;
            int maxValue = 1000;
            int[] arr = getRandomArray(len, maxValue);
            MedianHolder medianHold = new MedianHolder();
            for (int j = 0; j != arr.length; j++) {
                medianHold.addNumber(arr[j]);
            }
            if (medianHold.getMedian() != getMedianOfArray(arr)) {
                err = true;
                printArray(arr);
                break;
            }
        }
        System.out.println(err ? "Oops..what a fuck!" : "today is a beautiful day^_^");
        
    }
}
