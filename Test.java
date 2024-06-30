import java.util.*;


public class Test {

    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        if (n == 2) {
            return Math.max(nums[0], nums[1]);
        }
        // n >= 3
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[n - 1];
    }

    public int rob2(int[] nums){
        int n = nums.length;
        int[] nums2 = new int[n - 1];
        int[] nums3 = new int[n - 1];
        System.arraycopy(nums, 0, nums2, 0, n - 1);
        System.arraycopy(nums, 1, nums3, 0, n - 1);
        return Math.max(rob(nums2), rob(nums3));

    }

    public static void main(String[] args) {
        Test test = new Test();
        int re = test.rob2(new int[]{2,3,2});
        System.out.println(re);

    }

}
