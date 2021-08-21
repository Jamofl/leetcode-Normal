package Array数组;

public class Q53最大子序列和 {

    // 贪心
    // 当前sum为正，则将sum计入贡献中，sum + 当前num
    // 当前sum为负，则舍弃sum，将sum更新为当前num
    public int maxSubArray(int[] nums) {
        int sum = 0;
        int ans = nums[0];
        for (int num : nums){
            if (sum >= 0)
                sum = sum + num;
            else
                sum = num;
            ans = Math.max(ans, sum);
        }
        return ans;
    }

    // 动规
    // dp[i] = max(nums[i], nums[i] + dp[i - 1])
    public int maxSubArray2(int[] nums) {
        int n = nums.length;
        int ans = nums[0];
        int pre = nums[0];
        for (int i = 1; i < n; i ++){
            pre = Math.max(nums[i], nums[i] + pre);
            ans = Math.max(ans, pre);
        }
        return ans;
    }


}
