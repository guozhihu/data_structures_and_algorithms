package zhihu.algorithms.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Author: zhihu
 * Description: 调整数组顺序使奇数位于偶数前面
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于
 * 数组的前半部分，所有的偶数位于数组的后半部分，并保证奇数和奇数，偶数和偶数
 * 之间的相对位置不变。
 * Date: Create in 2019/4/11 23:27
 */
public class ReOrderArray {
    
    public static void reOrderArray(int[] array) {
        if (null == array || array.length < 2) {
            return;
        }
        
        int start = 0; // 数组头部指针
        int end = array.length - 1; // 数组尾部指针
        LinkedList<Integer> oddList = new LinkedList<Integer>();
        List<Integer> evenList = new ArrayList<Integer>();
        
        ///////////////////括起来的这部分代码可省略/////////////
        while ((start <= end) && (array[start] % 2 != 0)) {
            start++;
        }
        while ((start <= end) && (array[end] % 2 == 0)) {
            end--;
        }
        ///////////////////括起来的这部分代码可省略/////////////
        
        int odd = start - 1; // 奇数指针
        int even = end + 1; // 偶数指针
        
        while (start <= end) {
            // 数组从头部往尾部遍历的方向，如果当前start位置的数为偶数，按正序顺序暂存到偶数链表中
            while ((start <= end) && (array[start] % 2 == 0)) {
                evenList.add(array[start++]);
            }
            // 数组从尾部往头部遍历的方向，如果当前end位置的数为奇数，按逆序顺序暂存到奇数链表中
            while ((start <= end) && (array[end] % 2 != 0)) {
                oddList.addFirst(array[end--]);
            }
            while ((start <= end) && (array[start] % 2 != 0)) {
                array[++odd] = array[start++];
            }
            while ((start <= end) && (array[end] % 2 == 0)) {
                array[--even] = array[end--];
            }
        }
        for (int oddNum : oddList) {
            array[++odd] = oddNum;
        }
        for (int evenNum : evenList) {
            array[++odd] = evenNum;
        }
    }
    
    public static void main(String[] args) {
        int array[] = {1, 3, 4, 6, 5, 7, 9, 11, 12, 13, 14};
        reOrderArray(array);
        System.out.println(Arrays.toString(array));
    }
}
