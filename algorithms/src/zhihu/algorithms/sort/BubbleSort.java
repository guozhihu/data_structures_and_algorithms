package zhihu.algorithms.sort;

/**
 * Author: zhihu
 * Description: 冒泡排序算法
 * Date: Create in 2019/2/18 15:06
 */
public class BubbleSort {
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        
        /**
         * 算法思想： 第一次从0~N找出最小的数值
         *           第二次从0~N-1找出最小的数值
         *           第三次从0~N-2找出最小的数值
         *           依次下去...
         */
        // 假设已经排完序了
        boolean issorted = true;
        for (int e = arr.length - 1; e > 0; e--) {
            // 每次都假设排完序
            issorted = true;
            for (int i = 0; i < e; i++) {
                if (arr[i] > arr[i + 1]) {
                    // 假设失败
                    issorted = false;
                    swap(arr, i, i + 1);
                }
            }
            // 假设成真，不用再排序
            if (issorted) {
                return;
            }
        }
    }
    
    private static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}
