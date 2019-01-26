package zhihu.algorithms.greedy;

import javafx.util.Pair;

import java.util.*;

/**
 * Author: zhihu
 * Description: 最优加油方法
 * 一条公路上，有一个起点与一个终点，之间有n个加油站，已知从这n个加油站到终点站的距离d与各个加油站
 * 可以加油的油量l，起点位置距离终点的位置L与起始时刻邮箱中汽油量P；假设使用1个单位的汽油量可以走1
 * 个单位的距离，邮箱没有上限，问最少加几次油，可以从起点到达终点？如果无法到达终点，返回-1。
 * Date: Create in 2019/1/19 17:38
 * 难度：Hard
 */
public class Expedition {
    
    /**
     * 算法思路：
     * 设置一个最大堆，用来存储经过的加油站的汽油量
     * 按照从起点至终点方向，遍历各个加油站之间与加油站到终点距离
     * 每次需要走两个加油站之间的距离d，如果发现汽油不够走距离d时，从最大堆中取出一个油量添加，直到可以足够走距离d
     * 如果把最大堆的汽油都添加仍然不够行进距离d，则无法到达终点。
     * 当前油量P减少d
     * 将当前加油站油量添加至最大堆
     */
    static class cmp implements Comparator<Pair<Integer, Integer>> {
        
        @Override
        public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
            return o2.getKey() - o1.getKey();
        }
    }
    
    static class descCmp implements Comparator<Integer> {
        
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }
    
    public static int get_minimum_stop(int L, int P, Vector<Pair<Integer, Integer>> stop) {
        PriorityQueue<Integer> Q = new PriorityQueue<Integer>(new descCmp()); // 存储油量的最大堆
        int refueling_times = 0; // 记录加油次数
        stop.add(new Pair<Integer, Integer>(0, 0)); // 将终点作为一个停靠点，添加至stop数组
        
        Collections.sort(stop, new cmp()); // 以停靠点至终点距离从大到小进行排序
        
        // 遍历各个停靠点
        for (int i = 0; i < stop.size(); i++) {
            int dis = L - stop.get(i).getKey(); // 当前要走的距离即为当前距终点的距离
            
            // 当剩余油量无法走到下一站时，加油
            while (!Q.isEmpty() && P < dis) {
                P += Q.peek(); // 向邮箱中加入最大油量
                Q.poll();  // 最大堆中减去最大加油量
                refueling_times++; // 加油次数加1
            }
            
            // 如果中途经过的所有站点的油量都无法行驶到终点，返回无法到达站点
            if (Q.isEmpty() && P < dis) {
                return -1;
            }
            P -= dis;
            L = stop.get(i).getKey(); // 更新L为当前停靠点至终点距离
            Q.add(stop.get(i).getValue()); // 将当前停靠点的汽油量添加至最大堆
        }
        return refueling_times;
    }
    
    public static void main(String[] args) {
        Vector<Pair<Integer, Integer>> stop = new Vector<Pair<Integer, Integer>>();
        int N;
        int L;
        int P;
        int distance;
        int fuel;
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        for (int i = 0; i < N; i++) {
            distance = scanner.nextInt();
            fuel = scanner.nextInt();
            stop.add(new Pair<Integer, Integer>(distance, fuel));
        }
        L = scanner.nextInt();
        P = scanner.nextInt();
        System.out.println(get_minimum_stop(L, P, stop));
        
    }
}
