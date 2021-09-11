package BackTrack回溯;
/*
https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/
剑指 Offer 13. 机器人的运动范围
地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？



示例 1：

输入：m = 2, n = 3, k = 1
输出：3
示例 2：
//
输入：m = 3, n = 1, k = 0
输出：1
提示：

1 <= n,m <= 100
0 <= k <= 20
 */
import java.util.*;

public class OFFER13机器人的运动范围 {

    // 方法1  bfs广度优先遍历  维护一个队列，释放节点的向右和向下的邻居节点，统计满足条件的节点的个数，直到队列为空
    int[] Is = new int[] {0,1};
    int[] Js = new int[] {1,0,};
    public int movingCount(int m, int n, int k) {
        boolean[][] visit = new boolean[m][n];
        int count = 1;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {0, 0});

        while (queue.size() != 0){
            int size = queue.size();
            while (size > 0){
                int[] cell = queue.poll();
                for (int t = 0; t <= 1;  t ++){
                    int newI = cell[0] + Is[t];
                    int newJ = cell[1] + Js[t];
                    if (newI < m && newJ < n && digitSum(newI, newJ) <= k && ! visit[newI][newJ]){
                        visit[newI][newJ] = true;
                        count ++;
                        queue.offer(new int[] {newI, newJ});
                    }
                }
                size --;
            }
        }
        return count;
    }

    // 方法2  动态规划
    // 首先判定dp[i][j]是否满足题目中要求的位数之和小于等于 k的条件，然后判定该格子是否可达。可达性由上方和左方的格子推出
    // dp[i][j] = dp[i][j] | dp[i][j - 1];
    // dp[i][j] = dp[i][j] | dp[i - 1][j];
    public int movingCount2(int m, int n, int k) {
        boolean[][] dp = new boolean[m][n];
        dp[0][0] = true;
        int count = 0;

        for (int i = 0; i < m; i ++){
            for (int j = 0; j < n; j ++){
                if (digitSum(i, j) > k)
                    continue;
                if (i - 1 >= 0)
                    dp[i][j] = dp[i][j] | dp[i - 1][j];
                if (j - 1 >= 0)
                    dp[i][j] = dp[i][j] | dp[i][j - 1];

                if (dp[i][j])
                    count ++;
            }
        }
        return count;
    }


    private int digitSum(int i, int j){
        int sum = 0;
        while (i > 0){
            sum += i % 10;
            i = i / 10;
        }
        while (j > 0){
            sum += j % 10;
            j = j / 10;
        }
        return sum;
    }

    public static void main(String[] args){
        OFFER13机器人的运动范围 o = new OFFER13机器人的运动范围();
        int r = o.movingCount(2,3,1);
        System.out.println(r);
    }
}
