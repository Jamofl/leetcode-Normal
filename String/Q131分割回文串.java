/*
131. 分割回文串
给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。

返回 s 所有可能的分割方案。

示例:

输入: "aab"
输出:
[
  ["aa","b"],
  ["a","a","b"]
]
通过次数61,560提交次数87,630
 */
package String;
import java.util.*;
public class Q131分割回文串 {
    boolean[][] dp;
    public List<List<String>> partition(String s) {
        int n = s.length();
        dp = new boolean[n][n]; // 表明 s(i,j)是否是回文字符串
        List<List<String>> ans = new LinkedList<>();
        LinkedList<String> path = new LinkedList<>();

        // O(n2)
        for (int j = 0; j <= n - 1; j ++){
            for (int i = 0; i <= j; i ++){
                if (j == i)
                    dp[i][j] = true;
                else if (j == i + 1)
                    dp[i][j] = (s.charAt(i) == s.charAt(j));
                else
                    dp[i][j] = (s.charAt(i) == s.charAt(j)) && (dp[i + 1][j - 1]);
            }
        }
        dfs(ans, path, s, 0, n);
        return ans;
    }

    public void dfs(List<List<String>> ans, LinkedList<String> path, String s,int start, int n){
        if (start == n)
            ans.add(new LinkedList<>(path));

        int i = start;
        while (i < n){
            if (dp[start][i]){  // 如过 s(start, i)是回文
                path.add(s.substring(start, i + 1));
                dfs(ans, path, s, i + 1, n);
                path.removeLast();
            }
            i ++;
        }
    }
}
