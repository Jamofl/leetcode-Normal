package DP动态规划;

/**
 * https://leetcode-cn.com/problems/target-sum/
 * 给你一个整数数组 nums 和一个整数 target 。
 *
 * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
 *
 * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
 * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,1,1,1], target = 3
 * 输出：5
 * 解释：一共有 5 种方法让最终目标和为 3 。
 * -1 + 1 + 1 + 1 + 1 = 3
 * +1 - 1 + 1 + 1 + 1 = 3
 * +1 + 1 - 1 + 1 + 1 = 3
 * +1 + 1 + 1 - 1 + 1 = 3
 * +1 + 1 + 1 + 1 - 1 = 3
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/target-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
import java.util.*;
public class Q494目标和 {
    /**
     * 方法1 backTrack回溯遍历
     * 对于每一个元素，都有取正 取负两种操作  对每一种操作都进行回溯 当当前index == nums.length && sum == target，将答案 + 1
     * 但该操作比较费时  无法进行时间上的优化
     * 时间复杂度为O(2 ^ N)
     */
    public int ans = 0;
    public int findTargetSumWays(int[] nums, int target) {
        backTrack(nums, 0, target, 0);
        return ans;
    }

    public void backTrack(int[] nums, int index, int target, int sum){
        if (index == nums.length){
            if (sum == target)
                ans += 1;
            return;
        }
        backTrack(nums, index + 1, target, sum + nums[index]);
        backTrack(nums, index + 1, target, sum - nums[index]);
    }

    /**
     * 方法2 动态规划
     * 将问题转化为： 在该数组中找到k个元素 使他们的和等于一个定值  可以转换为01背包问题
     * 定义dp[i][j]  表示在前i个元素中取数 和为[j]的方案个数
     * @param nums
     * @param target
     * @return
     */
    public int findTargetSumWays2(int[] nums, int target) {
        int n = nums.length;
        int sum = 0;
        for (int num : nums)
            sum = sum + num;

        // 无解
        if (sum - target < 0 || (sum - target) % 2 == 1)
            return 0;

        // pos + neg = sum  pos - neg = targe  => neg = (sum - target) / 2
        // 把问题转换为 在数组中找到一定的元素 他们之和为neg
        int neg = (sum - target) / 2;

        // dp[i][j]的状态定义为：在前i个元素中 选取之和为j的方案的个数
        int[][] dp = new int[n + 1][neg + 1];
        // 转移方程为：
        // j <  curNum 不选，dp[i][j] = dp[i - 1][j]
        // j >= curNum 可选  dp[i][j] = dp[i - 1][j - curNum]
        //            可不选  dp[i][j] = dp[i - 1][j]

        // 由于转移方程中涉及到了dp[i - 1]所以需要对第一行进行初始化；转移方程中并未涉及到[j - 1]所以不需要对第一列初始化
        // 第一行初始化  在0个元素中 选出和为0的组合 共有一种；选出和为非零的组合，共有0种
        dp[0][0] = 1;

        for (int i = 1; i <= n; i ++){
            int curNum = nums[i - 1];
            for (int j = 0; j <= neg; j ++){
                if (j < curNum) // 不选该元素
                    dp[i][j] = dp[i - 1][j];
                else            // 选该元素
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - curNum];
            }
        }
        return dp[n][neg];
    }

    public static void main(String[] args) {
        Q494目标和 q = new Q494目标和();
        int r = q.findTargetSumWays2(new int[] {1,1,1}, 1);
        System.out.println(r);
    }

}
