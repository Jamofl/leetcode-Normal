package DP动态规划;
/*
剑指 Offer 10- II. 青蛙跳台阶问题
一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。

答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
示例 1：

输入：n = 2
输出：2

示例 2：

输入：n = 7
输出：21
示例 3：

输入：n = 0
输出：1
提示：

0 <= n <= 100
 */
public class Offer10ii青蛙跳台阶 {

    // solution 1 动态规划
    public int numWays(int n) {
        if (n == 0)
            return 1;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i ++){
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000007;
        }
        return dp[n];
    }

    // solution 2 dfs
    public int numWays2(int n) {
        if (n == 0 || n == 1)
            return 1;
        int[] memo = new int[n + 1];
        return climbHelper(n, memo);
    }

    public int climbHelper(int n, int[] memo){
        if (memo[n] != 0)
            return memo[n];
        if (n == 1)
            memo[1] = 1;
        else if (n == 0)
            memo[0] = 1;
        else
            memo[n] = (climbHelper(n - 1, memo) + climbHelper(n - 2, memo)) % 1000000007;
        return memo[n];
    }

}
