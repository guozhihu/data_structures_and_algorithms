package zhihu.algorithms.greedy;

/**
 * Author: zhihu
 * Description: 跳跃游戏II
 * 给定一个非负整数数组，您最初定位在数组的第一个索引处。
 * <p>
 * 数组中的每个元素表示该位置的最大跳转长度。
 * <p>
 * 您的目标是以最小跳跃次数到达最后一个索引。
 * <p>
 * 例：
 * <p>
 * 输入：[2,3,1,1,4]
 * 输出：2
 * 说明：到达最后一个索引的最小跳转次数为2。
 *      从索引0到1跳1步，然后从最后一个索引跳3步。
 * 注意：
 * <p>
 * 您可以假设您始终可以到达最后一个索引。
 * Date: Create in 2019/1/18 15:15
 * 难度：Medium
 */
public class JumpGameII {
    
    /**
     * 算法思路：
     * 第一种情况：如果当前数组元素为空或者只有一个数组元素，则说明不用跳跃，直接返回0即可
     * 第二种情况：如果当前数组元素有2个到2个以上，做如下操作
     * 假设有一个数组nums[n] (0 <= i < n)， 则i位置能够跳跃的最长位置max_location=i+nums[i]
     * 我们假设jump是当前开始跳跃的位置，jump从1开始，current_max_location为当前可跳跃的最长位置,jump_min_times为最少跳跃的次数
     * 从jump开始遍历整个数组，若jump超过current_max_location,则jump_min_times加1，current_max_location=max_location
     * 遍历过程中，如果jump + nums[jump] > max_location,即找到更大的可到达位置，则更新max_location。
     * @param nums
     * @return
     */
    public static int jump(int[] nums) {
        
        // 如果当前数组元素为空或者只有一个数组元素，则返回0
        if (nums.length < 2) {
            return 0;
        }
        
        // 记录当前能够跳跃的最长位置，初始值为数组第一个位置的可最长跳跃位置
        int current_max_index = nums[0];
        // 获得从jump到current_max_location之间某个jump位置的可跳跃最长位置
        int max_index = nums[0];
        // 记录从0位置到数组最后一个索引所需要跳跃的最少次数
        int jump_min_times = 1;
        
        int jump = 1;
        
        while (jump < nums.length) {
            // 如果jump已超过当前最大位置，则jump_min_times++，更新current_max_index。
            if (jump > current_max_index) {
                jump_min_times++;
                current_max_index = max_index;
            }
            // 遍历过程中找到更大的可到达的最远位置
            max_index = jump + nums[jump] > max_index ? jump + nums[jump] : max_index;
            jump++;
        }
        return jump_min_times;
    }
    
    public static void main(String[] args) {
        System.out.println(jump(new int[]{2, 3, 1, 1, 4}));
    }
}
