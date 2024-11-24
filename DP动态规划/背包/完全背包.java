package DP动态规划.背包;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class 完全背包 {
    /**
     * 和01背包的区别在于，每个物品可以拿取多次,而不是只能拿一次
     */

    public static int[] values ;
    public static int[] weights ;
    public static int[][] cache;
    public static int item ;
    public static int capacity;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        item = sc.nextInt();
        capacity = sc.nextInt();
        ArrayList<Integer> weightList = new ArrayList<>();
        ArrayList<Integer> valueList = new ArrayList<>();
        while (sc.hasNext()){
            weightList.add(sc.nextInt());
            valueList.add(sc.nextInt());
        }
        weights = weightList.stream().mapToInt(Integer::intValue).toArray();
        values = valueList.stream().mapToInt(Integer::intValue).toArray();
        sc.close();
        int re = maxValueDP2(item, capacity);
        System.out.println(re);
//        System.out.println(item);
//        System.out.println(capacity);
//        System.out.println(weights);
//        System.out.println(values);
    }

    // 初始化数组时， 行 列都 + 1， 避免了分类讨论。
    // item = 0时，即不放任何物品，dp[0][j] 为0； capacity = 0时，即容量为0，dp[i][0]为0
    // https://mp.weixin.qq.com/s/FwIiPPmR18_AJO5eiidT6w
    // dp[i][j] = Math.max(dp[i - 1][j], values[i - 1] + dp[i - 1][j - weights[i - 1]]);
    public static int maxValueDP(int item, int capacity) {
        int[][] dp = new int[item + 1][capacity + 1]; // dp[i][j]表示有i个物品，j个容量时的最大价值

        // i的下标从1开始  因为有i - 1的存在
        for (int i = 1; i <= item; i++) {
            // j的下标从0开始  dp[i][0] = 0 即容量为0的背包只能放入价值为0的东西
            // 20241119更新: 这里感觉j的下标也可以从1开始  dp[i][0] = 0 即容量为0的背包只能放入价值为0的东西 上面做初始化的目的就是为了这里避免分类讨论
            for (int j = 1; j <= capacity; j++) {
                // 如果j-weight[i-1] >= 0 就进行动规 根据动态转移方程从上一次的结果推导本次结果
                if (weights[i - 1] <= j) {
//                    dp[i][j] = Math.max(dp[i - 1][j], values[i - 1] + dp[i - 1][j - weights[i - 1]]);
                    dp[i][j] = Math.max(dp[i - 1][j], values[i - 1] + dp[i][j - weights[i - 1]]); // 和01背包的区别就是拿了当前物品后，还可以再拿，所以下标i不用减一
                    // 否则就直接取上一行的数据即可 ==> 个人觉得后面的一维数组是从结果反推的为了节省空间的做法 并不是很好理解 因为直接复制上一行 无法对应真实的理解 是为了省空间而省空间
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[item][capacity];
    }

    // 初始化数组时， 行 列都 + 1， 避免了分类讨论。
    // item = 0时，即不放任何物品，dp[0][j] 为0； capacity = 0时，即容量为0，dp[i][0]为0
    // https://mp.weixin.qq.com/s/FwIiPPmR18_AJO5eiidT6w
    // dp[i][j] = Math.max(dp[i - 1][j], values[i - 1] + dp[i - 1][j - weights[i - 1]]);
    public static int maxValueDP2(int item, int capacity) {
        int[][] dp = new int[item + 1][capacity + 1]; // dp[i][j]表示有i个物品，j个容量时的最大价值

        // i的下标从1开始  因为有i - 1的存在
        for (int i = 1; i <= item; i++) {
            // j的下标从0开始  dp[i][0] = 0 即容量为0的背包只能放入价值为0的东西
            // 20241119更新: 这里感觉j的下标也可以从1开始  dp[i][0] = 0 即容量为0的背包只能放入价值为0的东西 上面做初始化的目的就是为了这里避免分类讨论
            for (int j = 1; j <= capacity; j++) {
                for (int k = 0; j - k * weights[i - 1] >= 0; k ++){
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - weights[i - 1] * k] + values[i - 1] * k);
//                    System.out.println(i +"," + j+ "," + k);
                }
            }
        }
        return dp[item][capacity];
    }

}
