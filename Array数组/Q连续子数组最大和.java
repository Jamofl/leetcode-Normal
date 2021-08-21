package Array数组;

/*
输入一个整型数组，数组中一个或连续多个整数组成一个子数组，求所有子数组的和的最大值？
例如：输入的数组为1, -2, 3, 10, -4, 7, 2, -5，和最大的子数组为3, 10, -4, 7, 2，因此输出为该子数组的和18。
 */
public class Q连续子数组最大和 {
    public static void main(String [] args){
        int[] nums = new int[]{1,-2,3,10,-4,7,2,-5};
        System.out.println(maxSubArraySum(nums));
    }

    public static int maxSubArraySum(int[] nums){
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        int ans = nums[0];


        for (int i = 1; i < n ; i ++){
            if (dp[i - 1] > 0)
                dp[i] = dp[i - 1] + nums[i];
            else
                dp[i] = nums[i];
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}
