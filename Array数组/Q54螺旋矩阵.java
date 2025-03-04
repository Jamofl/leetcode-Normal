package Array数组;

import java.util.*;

public class Q54螺旋矩阵 {

    /**
     *
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> order = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return order;
        }
        int m = matrix.length, n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];
        int total = m * n;
        int i = 0, j = 0;
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int index = 0;
        for (int count = 0; count < total; count++) {
            order.add(matrix[i][j]);
            visited[i][j] = true;
            int nextI = i + directions[index][0], nextJ = j + directions[index][1];
            // 如果下一个坐标碰壁了（碰壁等价于==> 超出了矩阵的边界 || 遇到了之前访问过的元素） 那么就转向
            if (nextI < 0 || nextI >= m || nextJ < 0 || nextJ >= n || visited[nextI][nextJ]) {
                index = (index + 1) % 4;
            }
            i = i + directions[index][0];
            j = j + directions[index][1];
        }
        return order;
    }
}
