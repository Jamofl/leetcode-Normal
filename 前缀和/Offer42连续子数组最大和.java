package 前缀和;
/*
https://leetcode-cn.com/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof/
 */

public class Offer42连续子数组最大和 {

    /**
     * 前缀和的解法
     * 连续子数组最大和 = 当前前缀和 - 截止到目前为止的最小前缀和
     * 在计算前缀和的同时 记录截止到目前为止的最小前缀和即可
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int ans = Integer.MIN_VALUE;
        int sum = 0;
        int min = 0;
        for (int i = 0; i < n; i ++){
            sum = sum + nums[i];
            ans = Math.max(ans, sum - min);
            min = Math.min(min, sum);
        }
        return ans;
    }

    /**
     * 正常动态规划解法 如果累计和为正 则计入dp
     *                如果累计和为负 则重新开始计算dp
     * @param nums
     * @return
     */
    public int maxSubArray2(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        int ans = dp[0];

        for (int i = 1; i < nums.length; i ++){
            if (dp[i - 1] > 0){ // 如果累计和为正数 则继续计入
                dp[i] = dp[i - 1] + nums[i];
            }
            else {              // 如果累计和为复数 则重新计算
                dp[i] = nums[i];
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

}
