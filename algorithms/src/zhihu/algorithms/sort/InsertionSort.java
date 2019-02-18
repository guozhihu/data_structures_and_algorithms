package zhihu.algorithms.sort;

/**
 * Author: zhihu
 * Description: 插入排序
 * Date: Create in 2019/2/18 16:02
 */
public class InsertionSort {
    
    public static void insertionSort(int[] arr) {
        /**
         * 算法思想：假设前面的序列是有序序列，现在将后面的元素插入到该有序序列中
         */
        for (int i = 1; i < arr.length; i++) {
            // 取出第i个位置的值并与前面的有序序列的各个元素进行比较，将其插入适当的位置
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }
    
    private static void swap(int[] arr, int j, int i) {
//		int tmp = arr[i];
//		arr[i] = arr[j];
//		arr[j] = tmp;
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}
