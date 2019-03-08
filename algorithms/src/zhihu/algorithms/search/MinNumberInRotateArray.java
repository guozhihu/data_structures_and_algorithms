package zhihu.algorithms.search;

/**
 * Author: zhihu
 * Description: 旋转数组的最小数字
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个非减排序的数组的一个旋转，输出旋转数组的最小元素。 例如数
 * 组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
 * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
 * Date: Create in 2019/2/25 16:17
 */
public class MinNumberInRotateArray {
    
    /**
     * 算法思路：
     * 1.和二分查找法一样，我们用两个指针分别指向数组的第一个元素和最后一个元素。
     * 2.接着我们可以找到数组中间的元素
     * 如果该中间元素位于前面的递增子数组，那么它应该大于或者等于第一个指针指向的元素。
     * 此数组中最小的元素应该位于该中间元素的后面。我们可以把第一个指针指向该中间元素，
     * 这样可以缩小寻找的范围。如果中间元素位于后面的递增子数组，那么它应该小于或者等于
     * 第二个指针指向的元素。此时该数组中最小的元素应该位于该中间元素的前面。
     * 3.接下来我们再用更新之后的两个指针，重复做新一轮的查找。
     *
     * @param array
     * @return
     */
    public static int minNumberInRotateArray(int[] array) {
        
        if (array.length == 0) {
            return 0;
        }
        // 开始处理的第一个位置
        int left = 0;
        // 开始处理的最后一个位置
        int right = array.length - 1;
        // 设置初始值
        int mid = left;
        
        // 确保left在前一个排好序的部分，right在排好序的后一个部分
        while (array[left] >= array[right]) {
            // 当处理范围只有两个数据时，返回后一个结果
            // 因为array[left] >= array[right]总是成立，后一个结果对应的是最小的值
            if (right - left == 1) {
                return array[right];
            }
            
            // 取中间的位置
            mid = left + (right - left) / 2;
            
            // 如果中间位置对应的值在前一个排好序的部分，将left设置为新的处理位置
            if (array[mid] >= array[left]) {
                left = mid;
            }
            // 如果中间位置对应的值在后一个排好序的部分，将right设置为新的处理位置
            else if (array[mid] <= array[right]) {
                right = mid;
            }
        }
        
        // 返回最终的处理结果
        return array[mid];
    }
    
    public static void main(String[] args) {
        int[] arr = {3, 4, 5, 6, 1, 2};
        System.out.println(minNumberInRotateArray(arr));
    }
}
