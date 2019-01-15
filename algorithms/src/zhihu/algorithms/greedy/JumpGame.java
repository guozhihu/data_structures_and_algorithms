package zhihu.algorithms.greedy;

/**
 * Author: zhihu
 * Description: 跳跃游戏
 * 给定一个非负整数数组，您最初定位在数组的第一个索引处。
 *
 * 数组中的每个元素表示该位置的最大跳转长度。
 *
 * 确定您是否能够从第一个索引到达最后一个索引。
 *
 * 例1：
 *
 * 输入：[2,3,1,1,4]
 * 输出：true
 * 说明：从索引0跳转1步到1，然后从最后一个索引跳3步。
 * 例2：
 *
 * 输入：[3,2,1,0,4]
 * 输出：false
 * 说明：无论如何，您总是会到达索引3。 它的最大值
 *               跳转长度为0，这使得无法到达最后一个索引。
 * <p>
 * Date: Create in 2019/1/15 19:04
 * 难度：Medium
 */
public class JumpGame {
    
    /**
     * 算法思路：
     * 如果当前数组的元素个数为1，则必定能到达数组最后一个索引，下面考虑数组元素个数大于1的情况即可
     * 假设有一个数组nums[n] (0 <= i < n)， 则i位置能够跳跃的最长位置max_location=i+nums[i]
     * 我们假设jump是当前开始跳跃的位置，current_max_location为当前可跳跃的最长位置
     * 现在从0位置开始跳跃，初始化jump=0;此时current_max_location=nums[0],max_location=0+nums[0];
     * 每次都从jump到current_max_location遍历，
     *     遍历每个jump位置的max_location(=jump+nums[jump]),得到从jump到current_max_location之间的最大的max_location
     *     情况1：遍历过程中如果存在max_location能够到达数组的最后一个索引位置，说明能够跳跃到数组的最后一个索引，直接return true
     *     情况2：遍历过程中不存在比当前可跳跃的最长位置(即current_max_location)长，则不可能跳跃到数组最后一个索引位置，直接return false
     *     情况3：遍历到current_max_location的时候，存在max_location>current_max_location但是max_location无法到达数组最后一个索引位置，则改变当前的current_max_location值，current_max_location=max_location
     * @param nums
     * @return
     */
    public static boolean canJump(int[] nums) {
       
        // 从0位置开始跳跃
        int jump = 0;
        
        // 记录当前能够跳跃的最长位置，初始值为数组第一个位置的可最长跳跃位置
        int current_max_location = nums[0];
    
        // 获得从jump到current_max_location之间某个jump位置的可跳跃最长位置
        int max_location = nums[0];
        
        // 循环结束的条件是当前跳转的位置jump小于当前可跳转的最长位置，这里的current_max_location是会改变的，
        // 在从jump到current_max_location遍历完后能找到一个值比current_max_location大的话就会改变current_max_location
        while (jump < current_max_location) {
            // 如果目前找到的最长索引位置max_location能够到达数组的最后一个索引位置，则返回true
            if (max_location + 1 >= nums.length) {
                return true;
            }
            jump++;
            // 从jump位置到current_max_location位置寻找更大的可到达位置
            max_location = jump + nums[jump] > max_location ? jump + nums[jump] : max_location;
            
            // 如果从jump已经到达current_max_location，会发生如下3种情况
            // 情况1：如果不存在比current_max_location大的max_location，则不可能到达数组的最后一个索引，
            // 情况2：当前的max_location能够到达数组最后一个索引
            // 情况3：存在max_location>current_max_location但是max_location无法到达数组最后一个索引位置，
            // 重新设置当前的current_max_location=max_location，继续下一次的jump到current_max_location的遍历过程
            if (jump == current_max_location) {
                // 情况1
                if (current_max_location == max_location){
                    return false;
                }
                // 情况2
                if (max_location + 1 >= nums.length) {
                    return true;
                }
                // 情况3
                current_max_location = max_location;
            }
        }
        // 数组长度为1，必定能跳跃到最后一个索引位置
        return nums.length == 1 ;
    }
    
    public static void main(String[] args) {
        int[] nums = {2, 0, 0};
        System.out.println(canJump(nums));
    }
}
