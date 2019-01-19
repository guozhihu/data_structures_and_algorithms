package zhihu.algorithms.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Author: zhihu
 * Description: 射击气球
 * 在一个二维空间中，有许多球形气球。 对于每个气球，输入的是每个气球的直径在x坐标轴的起点和终点。最多将有
 * 104个气球。现安排弓箭手向上射击气球，可以沿x轴从不同点垂直向上射击气球，射击的箭头可以无限远地向上射击，
 * 可以射击的箭头数量没有限制。问至少需要多少射击的箭头，将全部气球打爆？
 * <p>
 * 例：
 * <p>
 * 输入：
 * [[10,16]，[2,8]，[1,6]，[7,12]]
 * <p>
 * 输出：
 * 2
 * <p>
 * 说明：
 * 一种方法是拍摄一个箭头，例如在x = 6（爆破气球[2,8]和[1,6]）和另一个箭头在x = 11（爆破其他两个气球）。
 * Date: Create in 2019/1/19 16:02
 * 难度：Medium
 */
public class MinimumNumberOfArrowsToBurstBalloons {
    
    /**
     * 算法思路：
     * 如果没有气球，则返回0
     * 如果当前的气球数量大于0个，则根据如下过程求解
     * 加入每个气球的直径用(xstart, xend)表示，则向上射击的箭头的范围shootingRange只要符合xstart <= shootingRange <= xend,则
     * 箭头必然能射中该气球。
     * 先对气球进行排序，按照每个气球的第二个坐标点xend进行排序
     * 则第一支箭头的射击范围shootingRange必然是第一个气球的坐标点的xend位置
     * 从第二个气球开始遍历，如果当前气球的直径坐标的xstart > shootingRange,则必须再取来第二支箭头，此时的射击范围由这只气球决定。
     * 循环结束，停止。
     *
     * @param points
     * @return
     */
    public static int findMinArrowShots(int[][] points) {
        if (0 == points.length) {
            return 0;
        }
        Arrays.sort(points, Comparator.comparingInt(a -> a[1]));
        int arrowCount = 1; // 射击的箭头数量
        int shootingRange = points[0][1]; // 每次射击的箭头的最大射击范围，默认从第一个气球的射击范围开始
        
        // 从第二个气球开始遍历每个气球的直径坐标的xstart。
        for (int i = 1; i < points.length; i++) {
            // 如果当前气球的xstart超出箭头射击范围，则必须取来第二支箭头射击，此时的射击范围为当前气球的xend决定
            if (points[i][0] > shootingRange) {
                // 箭头数量+1
                arrowCount++;
                // 更换箭头的射击范围
                shootingRange = points[i][1];
            }
        }
        return arrowCount;
    }
    
    public static void main(String[] args) {
        // int[][] points = { { 10, 16 }, { 2, 8 }, { 1, 6 }, { 7, 12 } };
        // int[][] points = {{3, 9}, {7, 12}, {3, 8}, {6, 8}, {9, 10}, {2, 9}, {0, 9}, {3, 9}, {0, 6}, {2, 8}};
        int[][] points = {{1, 2}, {2, 3}, {3, 4}, {4, 5}};
        System.out.println(findMinArrowShots(points));
    }
}
