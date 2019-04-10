package zhihu.algorithms.array;

import java.util.ArrayList;

/**
 * Author: zhihu
 * Description: 和为S的连续正数序列
 * 小明很喜欢数学,有一天他在做数学作业时,要求计算出9~16的和,他马上就写出了正确答案是100。
 * 但是他并不满足于此,他在想究竟有多少种连续的正数序列的和为100(至少包括两个数)。没多久,
 * 他就得到另一组连续正数和为100的序列:18,19,20,21,22。现在把问题交给你,你能不能也很快
 * 的找出所有和为S的连续正数序列? Good Luck!
 * <p>
 * 输出描述:
 * 输出所有和为S的连续正数序列。序列内按照从小至大的顺序，序列间按照开始数字从小到大的顺序
 * Date: Create in 2019/4/10 16:55
 */
public class FindContinuousSequence {
    
    /**
     * 算法思路：
     * 由数学知识可知，输出所有和为S的连续正数序列，实际上就是求出和为S公差为1的等差数列。
     * 假设有a1,a2,a3,...,an的n个连续正整数，则由等差数列可得这n个正整数之和
     * S = ((a1 + an) / 2) * n 等价于 (a1 + an) * n = S * 2 等价于 (a1 + an) * (an - a1 + 1) = S * 2;
     * 故可定义两个变量a1和an,a1的初值为1，an的初值为2(若sum<=2，a1和an不存在)。
     * 若a1到an序列和为S,则将a1...an添加到结合中返回，
     * 若a1到an序列和小于S,则an++;
     * 若a1到an序列和大于于S，则将a1++。
     * 当a1等于(S+1)/2时停止。
     *
     * @param sum
     * @return
     */
    public static ArrayList<ArrayList<Integer>> findContinuousSequence(int sum) {
        // 用于存放最终结果
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (sum <= 2) {
            return result;
        }
        
        // 用于存放和为sum的整数数列
        ArrayList<Integer> sequenceNum = null;
        int a1 = 1;
        int an = 2;
        // a1不能超过的最大边界值
        int maxBound = sum % 2 == 0 ? sum / 2 : sum / 2 + 1;
        while (a1 < maxBound) {
            long nsum = (a1 + an) * (an - a1 + 1);
            if (nsum == sum * 2) { // 此时a1到an的连续正整数序列之和为sum
                sequenceNum = new ArrayList<Integer>();
                // 添加a1到an的序列
                for (int sequence = a1; sequence <= an; sequence++) {
                    sequenceNum.add(sequence);
                }
                // 将合法的a1到an的序列添加到结果中
                result.add(sequenceNum);
                a1++;
                an++;
            } else if (nsum > sum * 2) { // 此时a1到an的正整数序列之和大于sum，则a1++
                a1++;
            } else { // 此时a1到an的正整数序列之和小于sum，则an++
                an++;
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> result = findContinuousSequence(100);
        for (ArrayList<Integer> sequenceList : result) {
            for (Integer sequence : sequenceList) {
                System.out.print(sequence + "\t");
            }
            System.out.println();
        }
    }
}
