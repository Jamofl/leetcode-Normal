package DP动态规划;
import java.util.*;
public class Q45跳跃游戏2 {
    public int jump(int[] nums) {
        int n = nums.length;
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
