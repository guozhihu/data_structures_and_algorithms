package zhihu.algorithms.sort;

/**
 * Author: zhihu
 * Description: 选择排序(selection sort)
 * 是一种原地(in-plarrce)排序算法，适用于小文件。
 * 由于选择操作时基于键值的且交换操作只在需要时才执行，所以选择排序经常用于数值较大和键值较小的文件。
 * Date: Create in 2019/2/18 15:12
 */
public class SelectionSort {
    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        
        int length = arr.length;
        
        for (int i = 0; i < length - 1; i++) {
            // 假设minindex为最小值的位置
            int minindex = i;
            for (int j = i + 1; j < length; j++) {
                minindex = arr[j] < arr[minindex] ? j : minindex;
            }
            if (minindex != i) {
                swap(arr, i, minindex);
            }
        }
    }
    
    // 进行交换
    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
