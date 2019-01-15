package zhihu.algorithms.greedy;

import java.util.Stack;

/**
 * Author: zhihu
 * Description: 移除K个数字
 * 给定一个非负整数num表示为字符串，从num中删除k个数字，求获得最小的可能的新数字。
 * 注意：
 * num的长度小于10002并且≥k。
 * 给定的num不包含任何前导零。
 * 例1：
 * <p>
 * 输入：num =“1432219”，k = 3
 * 输出：“1219”
 * 说明：删除三个数字4,3和2以形成最小的新数字1219。
 * 例2：
 * <p>
 * 输入：num =“10200”，k = 1
 * 输出：“200”
 * 说明：删除前导1，数字为200.请注意，输出不得包含前导零。
 * 例3：
 * <p>
 * 输入：num =“10”，k = 2
 * 输出：“0”
 * 说明：从数字中删除所有数字，并且没有任何值为0。
 * Date: Create in 2019/1/15 15:09
 * 难度：Medium
 */
public class RemoveKDigits {
    
    /**
     * 算法思路：
     * 如果当前字符长度与要移除的个数K相等，则移除K个数字后的字符串必定为“0”，下面考虑K小于字符串长度的情况
     * 取出字符串的每一个字符转化为数字
     * 将取出的数字入栈，入栈前，如果栈元素个数大于0，要移除的个数k此时也大于0，且栈顶元素的数值大于当前要插入栈中的数值，则将栈顶元素踢出栈，继续迭代栈，每一次栈顶出栈，k自减1
     * 执行完上面的操作后，将数字入栈
     * 当字符串中的所有数字入栈后，如果k>0,则从栈顶开始踢出数字，k--，直至k为0后结束该操作
     * 最后从栈底开始遍历，拼装字符串，踢出前导零后开始拼装数字
     * @param num
     * @param k
     * @return
     */
    public static String removeKdigits(String num, int k) {
        // 如果字符串长度==移除的个数k，则直接返回“0”字符串
        if (num.length() == k) {
            return "0";
        }
        
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < num.length(); i++) {
            int number = num.charAt(i) - '0';
            // 入栈前迭代栈顶元素，判断栈顶元素是否大于当前的字符数值，如果是，剔除该栈顶元素
            while (stack.size() > 0 && k > 0 && number < stack.peek()) {
                stack.pop();
                k--;
            }
            stack.push(number);
        }
        // num字符串的每个字符都入栈后，k仍然大于0，则从栈顶开始踢出元素，直至k为0
        while (k-- > 0 && stack.size() > 0) {
            stack.pop();
        }
        StringBuilder sb = new StringBuilder();
        
        // 剔除前导零
        boolean LeadingZeroFlag = true; // 前导0是否结束标志
        for (int i = 0; i < stack.size(); i++) {
            int numString = stack.get(i);
            // 判断该数字是不是符合前导零
            if (numString != 0) {
                LeadingZeroFlag = false;
            }
            if (!LeadingZeroFlag) {
                sb.append(numString);
            }
        }
        return sb.toString().length() > 0 ? sb.toString() : "0";
    }
    
    // 算法的部分优化
    /**
     * 算法思路：
     * 如果当前字符长度与要移除的个数K相等，则移除K个数字后的字符串必定为“0”，下面考虑K小于字符串长度的情况
     * 取出字符串的每一个字符转化为数字
     * 将取出的数字入栈，入栈前，如果栈元素个数大于0，要移除的个数k此时也大于0，且栈顶元素的数值大于当前要插入栈中的数值，则将栈顶元素踢出栈，继续迭代栈，每一次栈顶出栈，k自减1
     * 执行完上面的操作后，将数字入栈，入栈前，判断当前栈元素个数是否==0并且要入栈的数值是否为0，如果是则不入栈（这样在下面进行字符串拼装的时候就不用考虑会存在前导0的情况）
     * 当字符串中的所有数字入栈后，如果k>0,则从栈顶开始踢出数字，k--，直至k为0后结束该操作
     * 最后从栈底开始遍历，拼装字符串
     * @param num
     * @param k
     * @return
     */
    public static String removeKdigits_optimization(String num, int k) {
        // 如果字符串长度==移除的个数k，则直接返回“0”字符串
        if (num.length() == k) {
            return "0";
        }
        
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < num.length(); i++) {
            int number = num.charAt(i) - '0';
            // 入栈前迭代栈顶元素，判断栈顶元素是否大于当前的字符数值，如果是，剔除该栈顶元素
            while (stack.size() > 0 && k > 0 && number < stack.peek()) {
                stack.pop();
                k--;
            }
            // 在入栈过程中进行部分代码优化(!(0 == stack.size() && (0 == number))
            if (0 != stack.size() || 0 != number) {
                stack.push(number);
            }
        }
        // num字符串的每个字符都入栈后，k仍然大于0，则从栈顶开始踢出元素，直至k为0
        while (k-- > 0 && stack.size() > 0) {
            stack.pop();
        }
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < stack.size(); i++) {
                sb.append(stack.get(i));
        }
        return sb.toString().length() > 0 ? sb.toString() : "0";
    }
    
    public static void main(String[] args) {
        String num = "1432219";
        int k = 3;
        System.out.println(removeKdigits(num, k));
    }
}
