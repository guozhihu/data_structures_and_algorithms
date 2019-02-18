package zhihu.algorithms.sort;

/**
 * Author: zhihu
 * Description: 快速排序
 * Date: Create in 2019/2/18 17:40
 */
public class QuickSort {
    
    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }
    
    private static void quickSort(int[] arr, int l, int r) {
        if (l < r) {
            /**
             **  加上这段代码就是随机快排，时间复杂度的期望值为（O(nlogn)），去掉这行代码就是经典快排，时间复杂度为(O(n²))
             */
            swap(arr, l + (int) (Math.random() * (r - l + 1)), r);
            /**
             *
             */
            int[] p = partition(arr, l, r);
            quickSort(arr, l, p[0] - 1);
            quickSort(arr, p[1] + 1, r);
        }
    }
    
    private static int[] partition(int[] arr, int l, int r) {
        int less = l - 1;
        int more = r;
        while (l < more) {
            if (arr[l] < arr[r]) {
                swap(arr, ++less, l++);
            } else if (arr[l] == arr[r]) {
                l++;
            } else {
                swap(arr, l, --more);
            }
        }
        swap(arr, more, r);
        return new int[]{less + 1, more};
    }
    
    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    
}
