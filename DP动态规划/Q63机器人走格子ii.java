/*
63. 不同路径 II
一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。

机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。

现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？

网格中的障碍物和空位置分别用 1 和 0 来表示。

示例 1：


输入：obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
输出：2
解释：
3x3 网格的正中间有一个障碍物。
从左上角到右下角一共有 2 条不同的路径：
1. 向右 -> 向右 -> 向下 -> 向下
2. 向下 -> 向下 -> 向右 -> 向右
示例 2：

输入：obstacleGrid = [[0,1],[0,0]]
输出：1

提示：
m == obstacleGrid.length
n == obstacleGrid[i].length
1 <= m, n <= 100
obstacleGrid[i][j] 为 0 或 1
https://mp.weixin.qq.com/s/lhqF0O4le9-wvalptOVOww
 */
package DP动态规划;

public class Q63机器人走格子ii {

    // 方法1 动态规划
    // 首先初始化第一行和第一列  障碍物之前的dp为1，之后的dp为0；
    // 从点（1，1）开始遍历，若该点有障碍物，dp = 0；否则，dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid[0] == null)
            return 0;
        if (obstacleGrid[0][0] == 1)
            return 0;

        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = 1;

        // initialize 第一行和第一列
        for (int j = 0; j < col; j ++){
            if (obstacleGrid[0][j] == 0)
                dp[0][j] = 1;
            else
                break;
        }
        for (int i = 0; i < row; i ++){
            if (obstacleGrid[i][0] == 0)
                dp[i][0] = 1;
            else
                break;
        }

        for (int i = 1; i < row; i ++){
            for (int j = 1; j < col; j ++)
                if (obstacleGrid[i][j] == 0)
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
        }
        return dp[row - 1][col - 1];
    }

    // 方法2 优化空间的动态规划
    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid[0] == null)
            return 0;
        if (obstacleGrid[0][0] == 1)
            return 0;

        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;
        int[] dp = new int[col];
        dp[0] = 1;

        for (int i = 0; i < row; i ++){
            for (int j = 0; j < col; j ++){
                if (obstacleGrid[i][j] == 1){
                    dp[j] = 0;
                    continue;
                }
                if (j - 1 >= 0)// && obstacleGrid[i][j - 1] == 0)
                    dp[j] = dp[j] + dp[j - 1];
            }
        }
        return dp[col - 1];
    }
}
