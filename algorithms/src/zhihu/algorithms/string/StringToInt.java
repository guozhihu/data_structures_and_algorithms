package zhihu.algorithms.string;

/**
 * Author: zhihu
 * Description: 把字符串转换成整数
 * 将一个字符串转换成一个整数(实现Integer.valueOf(string)的功能，但是string
 * 不符合数字要求时返回0)，要求不能使用字符串转换整数的库函数。 数值为0或者字
 * 符串不是一个合法的数值则返回0。
 * <p>
 * 输入描述:
 * 输入一个字符串,包括数字字母符号,可以为空
 * 输出描述:
 * <p>
 * 如果是合法的数值表达则返回该数字，否则返回0
 * Date: Create in 2019/4/11 14:09
 */
public class StringToInt {
    
    /**
     * 算法思路：
     * 假设字符串为str，首先从字符串的尾部开始遍历，即从str.length-1到0的递减顺序开始获取字符char,
     * 1.如果当前字符不是字符串的首位字符，做如下判断
     * 如果char在字符'0'~'9'之间，则为合法的数字字符，否则返回0；如果为合法字符，则应判断该字符
     * 是个位还是百位还是千位...；所以设置变量bit作为记录，bit默认为1，每经过一个合法字符，则自
     * 乘10
     * 2.如果当前字符为首位字符，做如下判断
     * 如果为数字字符或'+'字符，则该字符串为正数
     * 如果为'-'字符，则该字符串为负数
     * 如果为其他字符则返回0，表示当前字符串为非法字符串
     *
     * @param str
     * @return
     */
    public int StrToInt(String str) {
        int num = 0;
        int bit = 1; // 表示当前字符串中字符转化为数字后位于整数的哪个位，个位，百位，千位...
        for (int i = str.length() - 1; i >= 0; i--) {
            char c = str.charAt(i);
            if (i == 0) { // 判断该字符串的第一位字符是不是符号位或者是数字，如果为'+'或数字表示正数，如果为'-'表示负数
                if (c == '+' || c == '-') {
                    num = c == '+' ? num : 0 - num;
                } else if (isNum(c)) {
                    num += (c - '0') * bit;
                } else {
                    return 0;
                }
            } else if (!isNum(c) && i != 0) {
                return 0;
            } else {
                num += (c - '0') * bit;
                bit *= 10;
            }
        }
        return num;
    }
    
    // 判断该字符能够转化为数字
    public boolean isNum(char c) {
        return '0' <= c && '9' >= c;
    }
}
