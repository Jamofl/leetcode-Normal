package DP动态规划;

import java.util.Scanner;

/*
题目描述今盒子里有n个小球，A、B两人轮流从盒中取球，每个人都可以看到另一个人取了多少个，
也可以看到盒中还剩下多少个，并且两人都很聪明，不会做出错误的判断。
我们约定：
每个人从盒子中取出的球的数目必须是：1，3，7或者8个。
轮到某一方取球时不能弃权！
A先取球，然后双方交替取球，直到取完。
被迫拿到最后一个球的一方为负方（输方）

请编程确定出在双方都不判断失误的情况下，对于特定的初始球数，A是否能赢？
 */
public class 盒子取球 {
    public static boolean[] result = new boolean[10001];
    public static int[] lst = new int[] {1,3,7,8};

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();

        for (int n : lst)
            result[n + 1] = true;

        for (int i = 0; i < result.length ; i ++){
            for (int j : lst){
                if (i > j){
                    if (result[i - j] == false){  // 说明A拿了j个球之后，B输了，即A赢了
                        result[i] = true;
                        break;
                    }
                }
            }
        }
        System.out.println(result[input]);
    }
}
