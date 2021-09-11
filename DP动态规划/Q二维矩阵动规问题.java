package DP动态规划;

/**
 * 一个由水  陆地   障碍物组成的小矩阵  1表示陆地，代价为1；0表示水，代价为2；2表示障碍物，不可通过。
 * 请计算从左上方起始位置到右下方重点需要的最小代价
 * 只可以水平或者垂直方向上移动
 *
 * 解法：
 * 1、【dfs回溯遍历】  在复杂用例的情况下会超时
 *
 * 2、【动态规划】 O(n2)
 *    对于第一行需要特殊处理
 *    对于第一列，如果上一行的方格不可达，该方格也不可达；
 *               如果上一行的方格可达，dp[i][j] = dp[i - 1][j] + cost[i][j]
 *    对于正常case, 如果左边和上方的方格都不可达，该方格也不可达
 *                 否则 dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + cost(matrix[i][j]);
 */
public class Q二维矩阵动规问题 {
    public static int minCost(int[][] matrix){
        int row = matrix.length;
        int col = matrix[0].length;
        if (matrix[0][0] == 2 || matrix[row - 1][col - 1] == 2)
            return -1;

        int[][] dp = new int[row][col];
        for (int i = 0; i < row; i ++){
            for (int j = 0; j < col; j ++){
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        dp[0][0] = 0;
        for (int j = 1; j < col; j ++){
            if (matrix[0][j] == 2)
                break;
            else
                dp[0][j] = dp[0][j - 1] + cost(matrix[0][j]);
        }

        for (int i = 1; i < row; i ++){
            for (int j = 0; j < col; j ++){
                // 当前区域不可达 直接置为Integer.MAX_VALUE
                if (matrix[i][j] == 2)
                    continue;
                    // 第一列的情况
                else if (j == 0)
                    dp[i][j] = dp[i - 1][j] == Integer.MAX_VALUE ? Integer.MAX_VALUE : dp[i - 1][j] + cost(matrix[i][j]);
                    // 正常case  分两种情况
                else{
                    // 不可达
                    if (dp[i - 1][j] == Integer.MAX_VALUE && dp[i][j - 1] == Integer.MAX_VALUE)
                        continue;
                        // 等于左边和上面方格中较小者 + 当前cost
                    else
                        dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + cost(matrix[i][j]);
                }
            }
        }
        if (dp[row - 1][col - 1] == Integer.MAX_VALUE)
            return -1;
        else
            return dp[row - 1][col - 1];
    }

    public static int cost(int n){
        return n == 1 ? 1 : 2;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][] {{1,1,1,1,0}, {0,1,0,1,0}, {1,1,2,1,1}, {0,2,0,0,1}};
        System.out.println(minCost(matrix));
    }
}
