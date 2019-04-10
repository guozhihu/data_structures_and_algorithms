package zhihu.algorithms.string;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: zhihu
 * Description: 字符流中第一个不重复的字符
 * 请实现一个函数用来找出字符流中第一个只出现一次的字符。例如，当从字符流中只读
 * 出前两个字符"go"时，第一个只出现一次的字符是"g"。当从该字符流中读出前六个字
 * 符“google"时，第一个只出现一次的字符是"l"。
 * <p>
 * 输出描述:
 * 如果当前字符流没有存在出现一次的字符，返回#字符。
 * Date: Create in 2019/4/10 21:56
 */
public class FirstAppearingOnce {
    
    StringBuilder sb = new StringBuilder();
    Map<Character, Integer> map = new HashMap<Character, Integer>();
    
    //Insert one char from stringstream
    public void insert(char ch) {
        sb.append(ch);
        if (map.containsKey(ch)) {
            map.put(ch, -1);
        } else {
            map.put(ch, 1);
        }
    }
    
    //return the first appearence once char in current stringstream
    public char firstAppearingOnce() {
        String str = sb.toString();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (map.get(c) == 1) {
                return c;
            }
        }
        return '#';
    }
}
