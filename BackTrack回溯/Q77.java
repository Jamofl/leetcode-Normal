/*
77. 组合
给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。

示例:

输入: n = 4, k = 2
输出:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
通过次数134,974提交次数176,390
 */
package BackTrack回溯;
import java.util.*;

public class Q77 {

    public List<List<Integer>> ans;
    public int n;
    public List<List<Integer>> combine(int n, int k) {
        this.n = n;
        this.ans = new ArrayList<List<Integer>>();
        dfs(new LinkedList<Integer>(),1, k);
        return ans;
    }

    public void dfs(LinkedList<Integer> path, int startIndex, int k){
        if (path.size() == k){
            ans.add(new LinkedList(path));
            return;
        }
        for (int j = startIndex; j <= n; j ++){
            if (j > n - k + 1 + path.size())  // 剪枝，当前的j必须满足： path.size() + （n - j + 1）>= k
                return;
            path.addLast(j);
            dfs(path, j + 1, k);
            path.removeLast();
        }
    }

}
