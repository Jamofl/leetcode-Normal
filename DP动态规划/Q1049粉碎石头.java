package DP动态规划;

/**
 * 1049. 最后一块石头的重量 II
 * 有一堆石头，用整数数组 stones 表示。其中 stones[i] 表示第 i 块石头的重量。
 *
 * 每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
 *
 * 如果 x == y，那么两块石头都会被完全粉碎；
 * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
 * 最后，最多只会剩下一块 石头。返回此石头 最小的可能重量 。如果没有石头剩下，就返回 0。
 *
 *
 *
 * 示例 1：
 *
 * 输入：stones = [2,7,4,1,8,1]
 * 输出：1
 * 解释：
 * 组合 2 和 4，得到 2，所以数组转化为 [2,7,1,8,1]，
 * 组合 7 和 8，得到 1，所以数组转化为 [2,1,1,1]，
 * 组合 2 和 1，得到 1，所以数组转化为 [1,1,1]，
 * 组合 1 和 1，得到 0，所以数组转化为 [1]，这就是最优值。
 * 示例 2：
 *
 * 输入：stones = [31,26,33,21,40]
 * 输出：5
 * 示例 3：
 *
 * 输入：stones = [1,2]
 * 输出：1
 */
import java.util.*;
public class Q1049粉碎石头 {

    /**
     * 转化为01背包问题
     * 首先将问题转换为，在数组中给一些元素前添+，一些元素前添-，使得两部分的差尽可能小，也就是两部分的值尽可能接近。
     * pos + neg = sum  pos - neg = min  => min = 2pos - sum  把问题化为 从数组中选出一定的元素 使他们的和尽可能等于target = sum / 2
     * 把问题转换为 在前n个物品中任选  选出总价值最大 但容量不能超过target的组合  即背包的总容量为target
     * 即01背包问题  物品为石头  容量和价值都为stones[]
     * @param stones
     * @return
     */
    public int lastStoneWeightIIDp(int[] stones) {
        int n = stones.length;
        int sum = 0;
        Arrays.sort(stones);
        for (int i = 0 ; i < n; i ++){
            sum += stones[i];
        }
        int target = sum / 2;
        // 把问题转换为 在前n个物品中任选  选出总价值最大 但不能超过target的组合
        // dp[i][j] = dp[i - 1][j], dp[i - 1][j - stones[i]] + stones[i]
        int[][] dp = new int[n + 1][target + 1];
        for (int i = 1; i <= n; i ++){
            for (int j = 1; j <= target; j ++){
                dp[i][j] = dp[i - 1][j];
                if (j >= stones[i - 1])
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - stones[i - 1]] + stones[i - 1]);
            }
        }
        return sum - (dp[n][target] * 2);
    }


    /**
     *  pos + neg = sum  pos - neg = min  => min = 2pos - sum
     *  把问题化为 从数组中选出一定的元素 使他们的和尽可能等于sum / 2
     *
     */
    public int dif = Integer.MAX_VALUE;
    public int ans = 0;

    public int lastStoneWeightII(int[] stones) {
        int n = stones.length;
        int sum = 0;
        Arrays.sort(stones);
        for (int i = 0 ; i < n; i ++){
            sum += stones[i];
        }
        int target = sum / 2;
        backTrack(stones, target, 0, 0);
        return Math.abs(sum - 2 * ans);
    }

    // O(2 ^ n) 对于数组中的任意一个数 都有选和不选两种操作
    private void backTrack(int[] stones, int target, int sum, int index){
        if (Math.abs(sum - target) < dif){
            dif = Math.abs(sum - target);
            ans = sum;
        }
        if (index == stones.length){
            return;
        }
        backTrack(stones, target, sum + stones[index], index + 1);
        backTrack(stones, target, sum, index + 1);
    }

    public static void main(String[] args) {
        Q1049粉碎石头 q = new Q1049粉碎石头();
//        System.out.println(q.lastStoneWeightII(new int[]{2,7,4,1,8,1}));
        System.out.println(q.lastStoneWeightII(new int[] {31,26,33,21,40}));
    }
}
