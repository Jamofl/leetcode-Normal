/*
152. 乘积最大子数组
给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。



示例 1:

输入: [2,3,-2,4]
输出: 6
解释: 子数组 [2,3] 有最大乘积 6。
示例 2:

输入: [-2,0,-1]
输出: 0
解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 */
package Array数组;

public class Q152乘积最大子数组 {

    // 前缀和枚举  O(n2) : 内外两层循环遍历，复杂度为O(n2)； 通过前缀和计算乘积，将乘积计算的复杂度降维O（1）
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int ans = 1;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < n; i ++){
            ans = 1;
            for (int j = i; j < n; j ++){
                ans = ans * nums[j];
                max = Math.max(max, ans);
            }
        }
        return max;
    }

    // 动态规划
    // 由于乘法具有特殊性，无法直接根据 dp[i] = max(nums[i], nums[i] * dp[i - 1])来判定，因为乘法不止和前一个答案有关，还和正负有关
    // 故维护两个数组dpMax dpMin, 分别记录到目前为止的最大乘积和最小乘积;
    // 当nums[i]为正数时，我们想要的是上一个最大乘积；当nums[i]为负数时，我们想要上一个最小乘积;
    // 最终返回dpMax中最大的那个即可
    public int maxProduct2(int[] nums) {
        int n = nums.length;
        int[] dpMax = new int[n];
        int[] dpMin = new int[n];
        dpMax[0] = nums[0];
        dpMin[0] = nums[0];
        int ans = dpMax[0];
        for (int i = 1; i < n; i ++){
            dpMax[i] = Math.max(nums[i], Math.max(dpMax[i - 1] * nums[i], dpMin[i - 1] * nums[i]));
            dpMin[i] = Math.min(nums[i], Math.min(dpMax[i - 1] * nums[i], dpMin[i - 1] * nums[i]));
            ans = Math.max(ans, dpMax[i]);
        }
        return ans;
    }

    // dp 空间复杂度降低为O（1) 不需要使用哪个额外数组
    public int maxProduct3(int[] nums) {
        int n = nums.length;
        int PreMax = nums[0];
        int PreMin = nums[0];
        int ans = nums[0];
        for (int i = 1; i < n; i ++){
            // 需要用两个额外变量保存当前的值，同步进行更新。
            int Max = Math.max(nums[i], Math.max(PreMax * nums[i], PreMin * nums[i]));
            int Min = Math.min(nums[i], Math.min(PreMax * nums[i], PreMin * nums[i]));
            ans = Math.max(ans, Max);
            PreMax = Max;
            PreMin = Min;
        }
        return ans;
    }

    public static void main(String[] args){
        Q152乘积最大子数组 q = new Q152乘积最大子数组();
        int r = q.maxProduct3(new int[] {-4, -3, -2});
        System.out.println(r);

    }
}
