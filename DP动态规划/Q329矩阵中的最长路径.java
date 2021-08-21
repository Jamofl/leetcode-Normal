/*
给定一个整数矩阵，找出最长递增路径的长度。

对于每个单元格，你可以往上，下，左，右四个方向移动。 你不能在对角线方向上移动或移动到边界外（即不允许环绕）。

示例 1:

输入: nums =
[
  [9,9,4],
  [6,6,8],
  [2,1,1]
]
输出: 4
解释: 最长递增路径为 [1, 2, 6, 9]。
示例 2:

输入: nums =
[
  [3,4,5],
  [3,2,6],
  [2,2,1]
]
输出: 4
解释: 最长递增路径是 [3, 4, 5, 6]。注意不允许在对角线方向上移动。
 */
package DP动态规划;

import java.util.*;

public class Q329矩阵中的最长路径 {


    // 解法1 dfs
    int[] x = new int[]{0, 1, 0, -1};
    int[] y = new int[]{1, 0, -1, 0};
    boolean[][] isMarked;
    int[][] ans;

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0)
            return 0;

        int max = 0;
        //isMarked = new boolean[matrix.length][matrix[0].length];
        ans = new int[matrix.length][matrix[0].length];

        for(int i = 0 ; i < matrix.length; i ++){
            for(int j = 0 ; j < matrix[0].length; j ++){
                if (ans[i][j] == 0)
                    search(matrix, i, j);
                if (ans[i][j] > max)
                    max = ans[i][j];
            }
        }
        return max;
    }

    private int search(int[][] matrix, int i, int j){
        if (ans[i][j] != 0)
            return ans[i][j];

        for(int k = 0; k <= 3; k ++){
            int newX = i + x[k];
            int newY = j + y[k];
            if (newX >= 0 && newX <= matrix.length - 1 && newY >= 0 && newY <= matrix[0].length - 1){
                if (matrix[newX][newY] > matrix[i][j]){
                    ans[i][j] = Math.max(ans[i][j], 1 + search(matrix, newX, newY));
                }
            }
        }
        if (ans[i][j] == 0){
            ans[i][j] = 1;
        }
        return ans[i][j];
    }



    // 解法2 bfs
    public int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int rows, columns;

    public int longestIncreasingPath2(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        rows = matrix.length;
        columns = matrix[0].length;
        int[][] outdegrees = new int[rows][columns];
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                for (int[] dir : dirs) {
                    int newRow = i + dir[0], newColumn = j + dir[1];
                    if (newRow >= 0 && newRow < rows && newColumn >= 0 && newColumn < columns && matrix[newRow][newColumn] > matrix[i][j]) {
                        ++outdegrees[i][j];
                    }
                }
            }
        }
        Queue<int[]> queue = new LinkedList<int[]>();
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                if (outdegrees[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                }
            }
        }
        int ans = 0;
        while (!queue.isEmpty()) {
            ++ans;
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                int[] cell = queue.poll();
                int row = cell[0], column = cell[1];
                for (int[] dir : dirs) {
                    int newRow = row + dir[0], newColumn = column + dir[1];
                    if (newRow >= 0 && newRow < rows && newColumn >= 0 && newColumn < columns && matrix[newRow][newColumn] < matrix[row][column]) {
                        --outdegrees[newRow][newColumn];
                        if (outdegrees[newRow][newColumn] == 0) {
                            queue.offer(new int[]{newRow, newColumn});
                        }
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args){
        Q329矩阵中的最长路径 q = new Q329矩阵中的最长路径();
        int[][] nums = new int[][]{{3,4,5},{3,2,6},{2,2,1}};
        int r = q.longestIncreasingPath(nums);
        System.out.println(r);
    }
}
