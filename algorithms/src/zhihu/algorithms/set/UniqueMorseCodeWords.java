package zhihu.algorithms.set;

import java.util.TreeSet;

/**
 * Author: zhihu
 * Description: 独特的摩尔斯电码字
 * https://leetcode.com/problems/unique-morse-code-words/
 * Date: Create in 2019/2/22 15:04
 * 难度：Easy
 */
public class UniqueMorseCodeWords {
    
    /**
     * 算法描述：
     * 1.用codes字符串数组按字母表a-z的顺序依次存储对应的摩尔斯电码字
     * 2.遍历给定的words字符串数组，得到每个单词word
     * 3.遍历单词word的每个字母，找出每个字母在codes中对应的摩尔斯电码字，即找到该字母在codes的索引位置
     * 可知codes中的0-25的索引位置依次存储的是a-z对应的摩尔斯电码字，所以将遍历出的每个单词word.charAt(i)-'a'，
     * 便可得到在codes中对应的索引位置，即可得到该单词对应的摩尔斯电码字，然后将word中每个单词对应的摩尔斯电码字
     * 拼接起来，便可得到word对应的摩尔斯电码字翻译结果
     * 4.将每个word翻译后的摩尔斯电码字存储到TreeSet中，利用TreeSet的去重性质便可得到words字符串数组中所有
     * word的不同翻译结果的个数。
     * @param words
     * @return
     */
    public static int uniqueMorseRepresentations(String[] words) {
        // 26个英文字母的摩尔斯电码字
        String[] codes = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
        TreeSet<String> transformationsSet = new TreeSet<String>();
        for(String word : words) {
            StringBuilder res = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                res.append(codes[word.charAt(i) - 'a']);
            }
            transformationsSet.add(res.toString());
        }
        return transformationsSet.size();
    }
}
