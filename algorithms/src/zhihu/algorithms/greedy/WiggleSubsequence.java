package zhihu.algorithms.greedy;

/**
 * Author: zhihu
 * Description:摆动子序列
 * 一个连续数字的序列，如果两个相邻数字之差恰好正负交替（或负正交替），则这样的数字序列称为摆动
 * 序列。具有少于两个数字的序列直接为摆动序列。
 * 例如：[1,7,4,9,2,5]是摆动序列，因为相邻两数之差为（6，-3,5，-7,3），正负交替；相比之下，
 * [1,4,7,2,5]和[1,7,4,5,5]不是摆动序列，第一个因为它的前两个差都是正的而第二个是因为它最后的
 * 两数之差是零。
 * 给定一个整数序列，返回作为摆动序列的最长子序列的长度。 通过从原始序列中删除一些元素（最终也为
 * 零）来获得子序列，使剩余元素保持其原始顺序。
 * <p>
 * Example 1:
 * Input: [1,7,4,9,2,5]
 * Output: 6
 * Explanation: The entire sequence is a wiggle sequence.
 * <p>
 * Example 2:
 * Input: [1,17,5,10,13,15,10,5,16,8]
 * Output: 7
 * Explanation: There are several subsequences that achieve this length. One is [1,17,10,13,10,16,8].
 * <p>
 * Example 3:
 * Input: [1,2,3,4,5,6,7,8,9]
 * Output: 2
 * <p>
 * Follow up:
 * Can you do it in O(n) time?
 * <p>
 * Date: Create in 2019/1/14 15:19
 * 难度：Medium
 */
public class WiggleSubsequence {
    /**
     * 算法思路：
     * 假设序列中的任意两个数num1, num2,且num2在num1之后，如果num2=num1,则认为num2处于平稳状态(用0表示)；如果num2>num1,则认为num2处于上升状态(用1表示)；如果num2<num1,则认为num2处于下降状态(用-1表示)
     * 假设有一个序列（长度>2）：num1, num2, num3, ...
     * 这里我们默认序列的第一个元素的当前状态（currentStatu）和前一个数的状态（preStatu）为平稳状态，即num1的currentStatu=0和preStatu=0。
     * 从第2个元素开始，即num2的currentStatu = num2 > num1? 1 : -1 else 0,preStatu=num1的preStatu;num3的currentStatu = num3 > num2? 1 : -1 else 0 ,preStatu=num2的currentStatu。
     * 如果中间存在某一段序列为全部为平稳状态，则该中间段序列的preStatu=这段中间序列的前一个数的currentStatu。
     * 那么给定一个随机整数序列（长度>2），从第二个整数开始，如果当前数字num的currentStatu != preStatu && currentStatu != 0，说明两数之差构成正负交替（或负正交替）
     * 举个例子：
     * 整数序列nums[1, 4, 2, 2]：nums[0]的currentStatu1=0,preStatu1=0，nums[1]的currentStatu2=1,preStatu2=currentStatu1=0，nums[2]的currentStatu3=-1,preStatu3=curentStatu2=1，nums[3]的currentStatu4=0,preStatu4=preStatu3=1
     * 所以可以看出当currentStatu != preStatu && currentStatu != 0时，子序列长度加1。
     * @param nums
     * @return
     */
    public static int wiggleMaxLength(int[] nums) {
        // 具有少于两个数字的序列直接为摆动序列
        if (nums.length < 2) {
            return nums.length;
        }
        // 当前数值的前一个状态，第一个整数为平稳状态
        int preStatus = 0;
        // 当前数值的当前状态，第一个元素为平稳状态
        int currentStatu = 0;
        int maxLength = 1; // 最大子序列长度
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) { // 当前数值的当前状态为下降状态
                currentStatu = -1;
            } else if (nums[i] > nums[i - 1]) { // 当前数值的当前状态为上升状态
                currentStatu = 1;
            } else { // 当前数值的当前状态为平稳状态
                currentStatu = 0;
            }
        
            maxLength = preStatus != currentStatu && currentStatu != 0 ? maxLength + 1 : maxLength;
            preStatus = currentStatu == 0 ? preStatus : currentStatu;
        }
        return maxLength;
    }
    
    public static void main(String[] args) {
        int[] nums = { 1, 17, 5, 10, 13, 15, 10, 5, 16, 8 };
        System.out.println(wiggleMaxLength(nums));
    }
}
