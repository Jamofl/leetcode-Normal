package DP动态规划;
/**
 * https://leetcode-cn.com/problems/jump-game-ii/
 * 45. 跳跃游戏 II
 * 给你一个非负整数数组 nums ，你最初位于数组的第一个位置。
 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 *
 * 假设你总是可以到达数组的最后一个位置。
 * 示例 1:
 *
 * 输入: nums = [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * 示例 2:
 *
 * 输入: nums = [2,3,0,1,4]
 * 输出: 2
 */

import java.util.*;
public class Q45跳跃游戏2 {

    /**
     * 动态规划 倒叙跳跃 从后往前跳
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        int n = nums.length;
        // dp[i]的定义为当前第i个位置跳到最后一阶台阶的最小代价；  如dp[0]表示从index为0的地方跳到最后一节台阶需要的最小代价
        int[] dp = new int[n];
        Arrays.fill(dp, n);
        dp[n - 1] = 0;

        for (int i = n - 2; i >= 0; i --){
            for (int j = i + 1; j <= i + nums[i]; j ++){
                if (j >= n)
                    break;
                dp[i] = Math.min(dp[i], dp[j] + 1);
            }
        }
        return dp[0];
    }
}
