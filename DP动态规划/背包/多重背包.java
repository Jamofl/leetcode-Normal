package DP动态规划.背包;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class 多重背包 {
    /**
     * 和01背包的区别在于，每个物品可以拿取多次,而不是只能拿一次
     */


    public static int[] values ;
    public static int[] weights ;
    public static int[] quantities ;

    public static int[][] cache;
    public static int item ;
    public static int capacity;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        item = sc.nextInt();
        capacity = sc.nextInt();
        ArrayList<Integer> weightList = new ArrayList<>();
        ArrayList<Integer> valueList = new ArrayList<>();
        ArrayList<Integer> quantityList = new ArrayList<>();
        while (sc.hasNext()){
            weightList.add(sc.nextInt());
            valueList.add(sc.nextInt());
            quantityList.add(sc.nextInt());
        }
        weights = weightList.stream().mapToInt(Integer::intValue).toArray();
        values = valueList.stream().mapToInt(Integer::intValue).toArray();
        quantities = quantityList.stream().mapToInt(Integer::intValue).toArray();
        sc.close();
        int re = maxValueDP(item, capacity);
        System.out.println(re);
    }

    public static int maxValueDP(int item, int capacity) {
        int[][] dp = new int[item + 1][capacity + 1]; // dp[i][j]表示有i个物品，j个容量时的最大价值

        // i的下标从1开始  因为有i - 1的存在
        for (int i = 1; i <= item; i++) {
            // j的下标从0开始  dp[i][0] = 0 即容量为0的背包只能放入价值为0的东西
            // 20241119更新: 这里感觉j的下标也可以从1开始  dp[i][0] = 0 即容量为0的背包只能放入价值为0的东西 上面做初始化的目的就是为了这里避免分类讨论
            for (int j = 1; j <= capacity; j++) {
                // 如果j-weight[i-1] >= 0 就进行动规 根据动态转移方程从上一次的结果推导本次结果
                for (int k = 0; k <= quantities[i - 1]; k ++){
                    if (j - weights[i - 1] * k >= 0){
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - weights[i-1]*k] + values[i-1]*k);
                    }
                }
            }
        }
        return dp[item][capacity];
    }

}
