package DP动态规划;

/**
 * https://leetcode-cn.com/problems/partition-equal-subset-sum/
 * 416. 分割等和子集
 * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,5,11,5]
 * 输出：true
 * 解释：数组可以分割成 [1, 5, 5] 和 [11] 。
 * 示例 2：
 *
 * 输入：nums = [1,2,3,5]
 * 输出：false
 * 解释：数组不能分割成两个元素和相等的子集。
 */
import java.util.*;

public class Q416分割等和子集 {
    /**
     * 方法1 动态规划
     * 将问题转换为01背包问题
     * 这道题可以换一种表述：给定一个只包含正整数的非空数组 nums，
     * 判断是否可以从数组中选出一些数字，使得这些数字的和等于整个数组的元素和的一半。
     * 因此这个问题可以转换成「0−1 背包问题」。这道题与传统的「0−1 背包问题」的区别在于，
     * 传统的「0−1 背包问题」要求选取的物品的重量之和不能超过背包的总容量，这道题则要求选取的数字的和恰好等于整个数组的元素和的一半。
     * 类似于传统的「0-10−1 背包问题」，可以使用动态规划求解。
     *
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for (int num : nums){
            sum = sum + num;
        }
        // 奇数 不可以被分割
        if (sum % 2 == 1)
            return false;

        int target = sum / 2;
        int n = nums.length;
        // 问题转换为 在数组中找到一个子集 使他们的和为一个定值（另一半子集的和）
        // dp[i][j]定义为 在前i个元素中 找到一定子集 使他们的和为j 若存在则为true
        // 若不选当前元素 dp[i][j] = dp[i - 1][j]
        // 若选当前元素   dp[i][j] = dp[i - 1][j - curNum]
        // 转移方程为 dp[i][j] = dp[i - 1][j] || dp[i - 1][j - curNum]
        boolean[][] dp = new boolean[n + 1][target + 1];
        // dp[i][0] = true 因为在i个元素中选出和为0的组合，总能选出（不选任何元素即可）
        for (int i = 0; i <= n; i ++)
            dp[i][0] = true;

        for (int i = 1; i <= n; i ++){
            for (int j = 0; j <= target; j ++){
                int curNum = nums[i - 1];
                // j >= curNum 可选 可不选
                if (j >= curNum)
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - curNum];
                // j < curNum 说明不能选 直接把上一行的元素抄下来
                else
                    dp[i][j] = dp[i - 1][j];
            }
        }
        return dp[n][target];
    }

    /**
     * 压缩空间的动态规划   二维数组转为一维数组
     * 注意此时需要逆序遍历 否则当前的dp[j - curNum]就是在这一次遍历中更新后的值了，而不是上一行的值了
     */
    public boolean canPartition2(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for (int num : nums){
            sum = sum + num;
        }

        int target = sum / 2;
        int n = nums.length;
        // 奇数 不可以被分割
        if (sum % 2 == 1)
            return false;

        // 若不选当前元素 dp[j] = dp[j]
        // 若选当前元素   dp[j] = dp[j - curNum]
        // 转移方程为 dp[j] = dp[j] || [j - curNum]
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;

        // 需要倒序从后往前遍历 因为dp[j]值的更新会依赖于它前面的dp[j - curNum]
        // 如果正向遍历 此时的dp[j - curNum]的值就已经是更新后的这一行的值了 而不是上一行的值
        for (int i = 1; i <= n; i ++){
            for (int j = target; j >= nums[i - 1]; j --){
                // 对于j >= curNum的情况 取上一行的dp[j] || dp[j - nums[i - 1]]
                // 对于j <  curNum的情况 直接抄上一行的元素 由于用的是同一个dp[]数组 所以相当于直接抄了上一行
                dp[j] = dp[j] || dp[j - nums[i - 1]];
            }
        }
        return dp[target];
    }

    public static void main(String[] args) {
        Q416分割等和子集 q = new Q416分割等和子集();
        System.out.println(q.canPartition(new int[] {1,2,3,6}));
    }
}
