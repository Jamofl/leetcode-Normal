/*
132. 分割回文串 II
给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。

返回符合要求的最少分割次数。

示例:

输入: "aab"
输出: 1
解释: 进行一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
通过次数19,667提交次数43,892
 */
package String;
import java.util.*;
public class Q132分割回文串2 {

    boolean[][] dp;
    int n;
    int ans = Integer.MAX_VALUE;
    int[] cache;
    public int minCut(String s) {
        n = s.length();
        dp = new boolean[n][n]; // dp[i][j]存储的是子字符串(i, j)是否是回文串
        cache = new int[n];     // cache[i]存储的是子字符串(i : n - 1)的字符串需要分割的次数
        Arrays.fill(cache, -1); // -1 未访问过
        checkPalindrome(s);
        dfs(s, 0, n);
        return cache[0];
    }

    public int dfs(String s, int start, int n){
        if (start == n)
            return -1;

        else if (cache[start] != -1){ // 如果之前调用过cache[i], 直接返回
            return cache[start];
        }

        // 最好加上这个special case，
        // 因为 对于本身就就是回文串的，例如aba, 可直接返回0；
        // 若不加，则在下面的normal case进行判定，  1 + -1 = 0
        else if (dp[start][n - 1]){ // 如果(start, n - 1)本身就是回文串
            cache[start] = 0;
            return 0;
        }

        int j = start;
        while (j < n){
            if (dp[start][j]){  // s(start, j)为回文串， 1 + ans (s（j, n))
                ans = Math.min(ans, 1 + dfs(s, j + 1, n));
            }
            j ++;
        }
        cache[start] = ans;
        return ans;
    }

    public void checkPalindrome(String s){
        for (int j = 0; j < n; j ++){
            for (int i = 0; i <= j; i ++){
                if (j == i)
                    dp[i][j] = true;
                else if (j == i + 1)
                    dp[i][j] = (s.charAt(i) == s.charAt(j));
                else
                    dp[i][j] = (s.charAt(i) == s.charAt(j)) && (dp[i + 1][j - 1]);
            }
        }
    }

    // 方法2: 先使用dp算法， 在O（N2）的复杂度里对字符串的所有子串进行check，判断是否为回文串
    // 然后在O(N2)的复杂度里，对ans[i]进行判定。ans[i]表示从（i, n - 1）需要分割的次数，即最终返回的状态
    // 判定规则为，遍历 （i + 1 ~ n - 1）的所有位置，若s(i, j - 1)为回文，则 ans[i] = 1 + ans[j].取最小值即可
    // boolean[][] dp;
    // int n;

    public int minCut2(String s) {
        n = s.length();
        dp = new boolean[n][n];  // 表明子串（i，j）是否是回文串
        int[] ans = new int[n];  // 表明从(i, n - 1)的字符串需要分割的次数，也就是最终返回的答案
        Arrays.fill(ans, n);
        ans[n - 1] = 0; // 第（n - 1, n - 1）不需要分割，为 0
        checkPalindrome2(s); // 填充dp数组  O(N2)

        for (int i = n - 1; i >= 0; i --){
            if (dp[i][n - 1]){
                ans[i] = 0;
                continue;
            }
            for (int j = n - 1; j > i; j --){
                if (dp[i][j - 1]) // 如若 (i, j - 1)是回文，则 ans[i] = min(ans[i], 1 + ans[j])
                    ans[i] = Math.min(ans[i], 1 + ans[j]);
            }
        }
        return ans[0];
    }

    public void checkPalindrome2(String s){
        for (int j = 0; j < n; j ++){
            for (int i = 0; i <= j; i ++){
                if (j == i)
                    dp[i][j] = true;
                else if (j == i + 1)
                    dp[i][j] = (s.charAt(i) == s.charAt(j));
                else
                    dp[i][j] = (s.charAt(i) == s.charAt(j)) && (dp[i + 1][j - 1]);
            }
        }
    }

    public static void main(String[] args){
        Q132分割回文串2 q = new Q132分割回文串2();
        int r = q.minCut("ada");
    }
}
