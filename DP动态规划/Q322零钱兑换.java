package DP动态规划; /**
 * 322. 零钱兑换
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 * <p>
 * 你可以认为每种硬币的数量是无限的。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 * 示例 2：
 * <p>
 * 输入：coins = [2], amount = 3
 * 输出：-1
 * 示例 3：
 * <p>
 * 输入：coins = [1], amount = 0
 * 输出：0
 * 示例 4：
 * <p>
 * 输入：coins = [1], amount = 1
 * 输出：1
 * 示例 5：
 * <p>
 * 输入：coins = [1], amount = 2
 * 输出：2
 */

import java.util.*;

public class Q322零钱兑换 {


    /**
     * solution 1: 自顶向下up - bottom， 递归法+备忘录
     * 时间复杂度 O(AMOUNT*N) AMOUNT代表金额  N代表硬币数组长度 对每个金额，都需要枚举N枚硬币的组合 由于用了备忘录优化 所以没有重复计算
     */
    public int[] result;

    private int coinChangeHelper(int[] coins, int amount) {
        if (amount == 0)
            return 0;

        else if (result[amount] != 0)
            return result[amount];

        int minimum = amount + 1;
        for (int coin : coins) {
            if (coin <= amount) {
                int temp = coinChangeHelper(coins, amount - coin);// 必须要对返回的值进行判断，若小于等于0(即为-1的情况 说明无解)，则无效。
                if (temp >= 0) // 仅在有解的情况下才更新minimum的值
                    minimum = Math.min(minimum, temp);
            }
        }
        if (minimum == amount + 1) // 代表无解
            result[amount] = -1;
        else
            result[amount] = minimum + 1;
        return result[amount];
    }

    public int coinChange(int[] coins, int amount) {
        result = new int[amount + 1];
        return coinChangeHelper(coins, amount);
    }


    // 迭代法动态规划 自底向上
    // solution 2: bottom - up dp
    public int coinChange2(int[] coins, int amount) {
        int[] dp = new int[amount + 1]; // dp[i] 表示从凑成0 ~ amount的钱需要的硬币个数
        Arrays.fill(dp, amount + 1);// 最多需要amount + 1个硬币 如果超出amount + 1 说明凑不成
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                // 说明当前金额已经小于硬币金额 无法凑出 而无法凑出就是用amount + 1b表示的，即默认初始化的时候所有的金额都是无法凑出的，所以这里无需再赋值一次
                if (i - coins[j] < 0) {
                    continue;
                }
                dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    /**
     * 完整的分类讨论解法
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange3(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        int[] dp = new int[amount + 1];
        for (int i = 1; i <= amount; i++) {
            dp[i] = amount + 1;
        }
        for (int i = 1; i <= amount; i++) {
            for (int num : coins) {
                // case1 如果当前金额和硬币金额相等 返回1 (可以归为case3 符合通项公式)
                if (i == num) {
                    dp[i] = Math.min(dp[i], 1);
                }
                // case2 如果当前金额<硬币金额 返回无解  (可以归为初始化case 因为初始化就是默认所有金额都无解)
                else if (i < num) {
                    dp[i] = Math.min(dp[i], amount + 1);
                }
                // case3 正常情况 (前面两种情况都可以不用特判 最后看下来只有case3这一个分支)
                else {
                    dp[i] = Math.min(dp[i], 1 + dp[i - num]);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }


    public static void main(String[] args) {
        Q322零钱兑换 q = new Q322零钱兑换();
        int re = q.coinChange(new int[]{1, 2, 5}, 11);
        System.out.println(re);
    }
}
