/*
22. 括号生成
数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。



示例 1：

输入：n = 3
输出：["((()))","(()())","(())()","()(())","()()()"]
示例 2：

输入：n = 1
输出：["()"]


提示：

1 <= n <= 8
通过次数220,842提交次数287,750
 */
package BackTrack回溯;

import java.util.*;
public class Q22 {
    public List<String> generateParenthesis(int n) {

        LinkedList<String> ans = new LinkedList<>();
        String path = "";

        dfs(n, n, ans, path);
        return ans;
    }

    private void dfs(int left, int right, LinkedList<String> ans, String path){
        if (left == 0 && right == 0){
            ans.add(path);
            return;
        }

        if (left > 0){
            dfs(left - 1, right, ans, path + "("); // 生成了新的字符串，不需要回溯，直接dfs即可
        }
        if (right > 0 && left < right){
            dfs(left, right - 1, ans, path + ")");
        }
    }

    public static void main(String[] args){
        Q22 q = new Q22();
        List<String> l = q.generateParenthesis(3);
        System.out.println(l);
    }
}
