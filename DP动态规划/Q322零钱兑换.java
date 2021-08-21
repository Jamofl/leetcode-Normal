package DP动态规划; /**
 * 322. 零钱兑换
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 *
 * 你可以认为每种硬币的数量是无限的。
 *
 *
 *
 * 示例 1：
 *
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 * 示例 2：
 *
 * 输入：coins = [2], amount = 3
 * 输出：-1
 * 示例 3：
 *
 * 输入：coins = [1], amount = 0
 * 输出：0
 * 示例 4：
 *
 * 输入：coins = [1], amount = 1
 * 输出：1
 * 示例 5：
 *
 * 输入：coins = [1], amount = 2
 * 输出：2
 */
import java.util.*;
public class Q322零钱兑换 {


    // solution 1: 自顶向下 递归法动态规划 up - bottom dp
    int[] result;
    private int coinChangeHelper(int[] coins, int amount){
        if (amount == 0)
            return 0;

        else if (result[amount] != 0)
            return result[amount];

        int minimum = Integer.MAX_VALUE;
        for (int i : coins) {
            if (i > amount)
                continue;
            int ans = coinChangeHelper(coins, amount - i);// 必须要对返回的值进行判断，若小于等于0，则无效。
            if (ans >= 0 && minimum > ans)
                minimum = ans + 1;
        }
        minimum = (minimum == Integer.MAX_VALUE) ? -1 : minimum;
        result[amount] = minimum;
        return minimum;
    }

    public int coinChange(int[] coins, int amount) {
        result = new int[amount + 1];
        return coinChangeHelper(coins, amount);
    }



    // 迭代法动态规划 自底向上
    // solution 2: bottom - up dp
    public int coinChange2(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i ++){
            for (int j = 0; j < coins.length; j ++){
                if(i - coins[j] < 0)
                    continue;
                dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }



    public static void main(String[] args){
        Q322零钱兑换 q = new Q322零钱兑换();
        int re = q.coinChange(new int[]{1,2,5}, 11);
        System.out.println(re);
    }
}
