package zhihu.algorithms.sort;

/**
 * Author: zhihu
 * Description: 堆排序算法
 * Date: Create in 2019/3/4 16:53
 */
public class HeapSort {
    
    /**
     * 算法思路：
     * 1.先将数组转换成一个最大堆（或最小堆，具体看你是降序排序还是升序排序，升序采用最大堆，降序采用最小堆），
     * 这样就获得最大的元素，即堆顶元素
     * 2.将堆顶元素与数组最后一个元素交换位置
     * 3.逐步缩小数组的范围，每次将数组范围减少1，即0~arr.length-1,0~arr.length-2,0~arr.length-3直到缩小为0~1
     *   每次数组缩小范围，都先将其转换成最大堆，然后将堆顶元素替换到arr.length-1,arr.length-2,...,1的位置
     *   依次下去，每次都会将最大的元素往后放，这样就排序了。
     * @param arr
     */
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 先将该数组转换成最大堆
        heapify(arr);
        // 之后将堆顶元素与最后一个元素交换，也就是将数组中的最大数放置数组末端,然后将数组个数减1，依照该流程执行下去
        int size = arr.length;
        swap(arr, 0, --size);
        while (size > 0) {
            shiftDown(arr, 0, size);
            swap(arr, 0, --size);
        }
    }
    
    // 利用heapify将数组表示的完全二叉树转换成最大堆,算法时间复杂度为O(logn)
    private static void heapify(int[] arr) {
        // 从该完全二叉树中找到倒数第一个非叶子节点的节点,即当前数组的最后一个元素的索引号-1的差再除以2;
        for (int i = (arr.length - 1 - 1) / 2; i >= 0; i--) {
            shiftDown(arr, i, arr.length);
        }
    }
    
    // size表示当前这颗二叉树的节点数，index表示要进行下降操作的节点
    private static void shiftDown(int[] arr, int index, int size) {
        int left = index * 2 + 1;
        while (left < size) {
            int largestIndex = left + 1 < size && arr[left + 1] > arr[left] ? left + 1 : left;
            largestIndex = arr[largestIndex] > arr[index] ? largestIndex : index;
            if (largestIndex == index) {
                break;
            }
            swap(arr, largestIndex, index);
            index = largestIndex;
            left = index * 2 + 1;
        }
    }
    
    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
