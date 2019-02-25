package zhihu.algorithms.stack;

import java.util.Stack;

/**
 * Author: zhihu
 * Description: 有效的括号
 * https://leetcode.com/problems/valid-parentheses/submissions/
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "()"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: "()[]{}"
 * 输出: true
 * 示例 3:
 * <p>
 * 输入: "(]"
 * 输出: false
 * 示例 4:
 * <p>
 * 输入: "([)]"
 * 输出: false
 * 示例 5:
 * <p>
 * 输入: "{[]}"
 * 输出: true
 * Date: Create in 2019/2/25 11:03
 * 难度：Easy
 */
public class ValidParentheses {
    
    /**
     * 算法思路：
     * 遍历字符串s中的每个字符c
     *    如果c为左半边括号(指'('或者'{'或者'['中的一种)，将其压入栈stack中
     *    如果c为右半边括号(指')'或者'}'或者']'中的一种)
     *       判断栈stack中元素是否为空，为空表示字符串第一个元素为右半边括号中的一种，没有相应的左半边括号，返回false
     *       如果栈stack中元素不为空，则弹出栈顶元素，如果弹出的栈顶元素topChar与c不匹配，返回false
     * 如果字符串为null或者只存在右半边括号，即s.isEmpty()为true，返回false;
     * @param s
     * @return
     */
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char topChar = stack.pop();
                if (c == ')' && topChar != '(' || c == '}' && topChar != '{' || c == ']' && topChar != '[') {
                    return false;
                }
            }
        }
        // 如果字符串中只存在右半边括号，返回false;
        return stack.isEmpty();
    }
    
    public static void main(String[] args) {
        System.out.println(isValid("()"));
        System.out.println(isValid("()[]{}"));
        System.out.println(isValid("(]"));
        System.out.println(isValid("([)]"));
        System.out.println(isValid("{[]}"));
    }
}
