package zhihu.algorithms.string;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: zhihu
 * Description: 返回当前字符串中第一个不重复的字符的索引位置
 * 在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,
 * 并返回它的位置, 如果没有则返回 -1（需要区分大小写）.
 * Date: Create in 2019/3/4 23:38
 */
public class FirstNotRepeatingChar {
    
    /**
     * 算法思路：
     * 1.遍历str，用map存储str中每个字符在str中的索引位置，如果遍历str过程中，
     * 出现重复的字符，则将该字符出现的索引位置设置为-1，表示废弃掉该字符，因
     * 为该字符不可能是只出现一次的字符。
     * 2.遍历map，由于map中存储了每个字符的索引位置，所以在遍历map时，只要该字
     * 符的索引位置大于等于0，就进行索引值的大小比较。
     * 3.返回最小的索引值即可。
     *
     * @param str
     * @return
     */
    public static int FirstNotRepeatingChar(String str) {
        if (null == str || str.length() < 1) {
            return -1;
        }
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, -1);
            } else {
                map.put(c, i);
            }
        }
        int minIndex = Integer.MAX_VALUE;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            minIndex = entry.getValue() >= 0 && minIndex > entry.getValue() ? entry.getValue() : minIndex;
        }
        return minIndex;
    }
    
    public static void main(String[] args) {
        String str = "";
        System.out.println(FirstNotRepeatingChar(str));
    }
}
