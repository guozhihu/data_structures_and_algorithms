package zhihu.algorithms.binary_tree;

/**
 * Author: zhihu
 * Description: 折纸问题
 * 请把一段纸条竖着放在桌子上，然后从纸条的下边向上方对折1次，压出折痕后展开。
 * 此时折痕是凹下去的，即折痕突起的方向指向纸条的背面。如果从纸条的下边向上方
 * 连续对折2次，压出折痕后展开，此时有三条折痕，从上到下依次是下折痕,下折痕和
 * 上折痕。给定一个输入参数N，代表纸条都从下边向上方连续对折N次，请从上到下打
 * 印所有折痕的方向。例如 :
 * <p>
 * N = 1时,打印 : 　down
 * N = 2时,打印 : 　down down up
 * Date: Create in 2019/3/5 20:43
 */
public class PaperFolding {
    
    /**
     * 解析
     * 可以用一张纸条自己折一下，会发现:
     *
     * 第一次折的时候，只有一条折痕，往里凸的，记为1down。
     * 第二次折的时候，发现1down的上方为往里凸，记为2down，而下方为往外凸，记为2up。
     * 第三次折的时候，发现2dowm的上方往里凸，记为3down，2down的下方往外凸，记为3up； 而2up的上方往里凸，记为3down，2up的下方往外凸，记为3up；
     * 从上面折痕可以发现新的折痕的上面总是为down，下面总是为up，所以我们可以构造出一颗二叉树，具体看下图
     *           1下             第一次折的时候是下(序号记为1)
     *          /   \
     *       2下     2上         第二次折的时候1的上面为下，记为2下，下面为上，记为2上
     *      /   \   /   \
     *   3下   3上 3下  3上      第三次折的时候，就会发现每一次折出一个新的节点，它的上方总是下，下方总是上，所以我们要打印的是这颗特定树的中序遍历。
     * @param N
     */

    // 打印所有折痕方向的函数
    public static void printAllFolds(int N) {
        // 根节点的是down(第一次折的时候是往下的(往里凸))
        printProcess(1, N, true);
    }
    
    private static void printProcess(int i, int N, boolean down) {
        if (i > N) { // 终止条件，相当于节点为null
            return ;
        }
        // 相当于往左子树跑(因为左子树永远是down(下))
        printProcess(i + 1, N, true);
        // 访问中间节点
        System.out.println(down ? "down" : "up");
        //往右子树跑
        printProcess(i + 1, N, false);
    }
    
    public static void main(String[] args) {
        printAllFolds(10);
    }
}
