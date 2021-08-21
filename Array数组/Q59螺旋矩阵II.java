/*
59. 螺旋矩阵 II
给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。



示例 1：


输入：n = 3
输出：[[1,2,3],[8,9,4],[7,6,5]]
示例 2：

输入：n = 1
输出：[[1]]


提示：

1 <= n <= 20
通过次数60,236提交次数76,739
 */
package Array数组;

public class Q59螺旋矩阵II {

    // 顺时针模拟填充
    // 始终遵循着左闭右开的填充原则 比如对于 n = 4 的矩阵，第一层填充时只填3个
    // 对于n为奇数的情况，需要特殊处理
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int num = 1;
        int i = 0, j = 0;
        int m = n - 1;
        int count = 0;
        while (num <= n * n){
            if (m == 0){ // n 为奇数时，特殊处理
                matrix[i][j] = num;
                num ++;
                continue;
            }

            while (count < m){ // 向右画行
                matrix[i][j] = num;
                num ++;
                count ++;
                j ++;
            }
            count = 0;


            while (count < m){ // 向下画列
                matrix[i][j] = num;
                num ++;
                count ++;
                i ++;
            }
            count = 0;


            while (count < m){ // 向左画行
                matrix[i][j] = num;
                num ++;
                count ++;
                j --;
            }
            count = 0;


            while (count < m){ // 向上画列
                matrix[i][j] = num;
                num ++;
                count ++;
                if (count == m) // 向内缩圈
                    j ++;
                else
                    i --;
            }
            count = 0;
            m = m - 2;
        }
        return matrix;
    }

    public static void main(String[] args){
        Q59螺旋矩阵II q = new Q59螺旋矩阵II();
        int[][] m = q.generateMatrix(7);
        System.out.println();

    }
}
