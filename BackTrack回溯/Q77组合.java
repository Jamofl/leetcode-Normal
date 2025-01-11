package BackTrack回溯;

import java.util.*;

public class Q77组合 {
    /**
     * https://leetcode.cn/problems/combinations/description/?envType=study-plan-v2&envId=top-interview-150
     *
     * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
     * 你可以按 任何顺序 返回答案。
     *
     * 示例 1：
     * 输入：n = 4, k = 2
     * 输出：
     * [
     *   [2,4],
     *   [3,4],
     *   [2,3],
     *   [1,2],
     *   [1,3],
     *   [1,4],
     * ]
     * 示例 2：
     * 输入：n = 1, k = 1
     * 输出：[[1]]
     *
     * 提示：
     * ● 1 <= n <= 20
     * ● 1 <= k <= n
     */


    /**
     * 方法1  广度优先 bfs
     *
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> re = new ArrayList<>();
        bfs(re, new LinkedList(), k, 1, n);
        return re;
    }

    public void bfs(List<List<Integer>> re, LinkedList<Integer> path, int k, int index, int n) {
        if (path.size() == k) {
            re.add(new ArrayList(path));
            return;
        }
        // bfs 循环所有元素 每个元素都拿 拿完放回 然后再拿下一个元素
        for (int i = index; k <= path.size() + n - i + 1; i++) {
            path.addLast(i);
            bfs(re, path, k, i + 1, n);
            path.removeLast();
        }
    }


    /**
     * 方法2 深度优先 dfs
     */
    public void dfs(List<List<Integer>> re, LinkedList<Integer> path, int k, int index, int n) {
        if (path.size() == k) {
            re.add(new ArrayList(path));
            return;
        }
        if (path.size() + n - index + 1 >= k) {
            // dfs 需要手动写出所有的情况 即拿与不拿 通过下一层的dfs的index + 1进入到下一个元素
            path.addLast(index);
            dfs(re, path, k, index + 1, n);
            path.removeLast();
            dfs(re, path, k, index + 1, n);
        }
    }

}
