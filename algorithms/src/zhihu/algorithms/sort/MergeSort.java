package zhihu.algorithms.sort;

/**
 * Author: zhihu
 * Description: 归并排序
 * Date: Create in 2019/3/4 21:28
 */
public class MergeSort {
    public static void mergeSort(int[] arr) {
        if (null == arr || arr.length < 2) {
            return;
        }
        mergeSort(arr, 0, arr.length - 1);
    }
    
    private static void mergeSort(int[] arr, int l, int r) {
        if (l == r) {
            return;
        }
        int mid = l + ((r - l) >> 1);// l和r中点的位置(l+r)/2
        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);
        merge(arr, l, mid, r);
    }
    
    private static void merge(int[] arr, int l, int mid, int r) {
        // 生成一个辅助数组，数组的长度为l到r的个数
        int[] help = new int[r - l + 1];
        int p1 = l;// p1代表整个左侧的最小值位置
        int p2 = mid + 1;// p2代表整个右侧的最小值位置
        int i = 0;
        // 如果p1大于mid的情况先发生，证明左边已经被右边的提前比完了，如果p2大于r的情况先发生，证明右边的已经被左边的提前比完了
        while (p1 <= mid && p2 <= r) {
            // 这里是一个外排，左边的值和右边的值进行比较，如果p1(左边)位置的值小于p2(右边)位置的值，将p1位置的值赋给help[i]，然后p1指向下一个位置；反之，p2位置的值赋给help[i]，然后p2指向下一个位置
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        // 两个必有且只有一个越界
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
    }
}
