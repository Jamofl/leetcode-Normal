package DP动态规划;

/**
 * 有个小孩正在上楼梯，楼梯有n阶台阶，小孩一次可以上1阶、2阶或3阶。实现一个算法，计算小孩有多少种上楼梯的方式。
 * 输入n，返回一个整数
 */
public class Q走楼梯 {
    public static void main(String[] args) {

    }

    public static int stairs(int n){
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2; // 1 + 1, 2
        dp[3] = 4; // 1 + 1 + 1, 1 + 2, 2 + 1, 3

        for (int i = 4; i <= n; i ++){
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }
        return dp[n];
    }
}
