/*
74. 搜索二维矩阵
编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：

每行中的整数从左到右按升序排列。
每行的第一个整数大于前一行的最后一个整数。
 */
package Array数组;

public class Q74搜索二维矩阵 {

    /*
    O(LOG(M * N))
    把该矩阵当成一个虚拟的一维数组来看待。二分时进行下标的转换。 x = i * n + j;
    推出 i = x / n; j = x % n;

    public int m;
    public int n;
    public boolean searchMatrix(int[][] matrix, int target) {
        m = matrix.length;
        n = matrix[0].length;
        return (binarySearch(matrix, target) == -1) ? false : true;

    }

    public int binarySearch(int[][] matrix, int target){
        int l = 0;
        int r = m * n - 1;
        while (r >= l){
            int middle = l + (r - l) / 2;
            int col = middle % n;
            int row = middle / n;
            if (target > matrix[row][col])
                l = middle + 1;
            else if (target < matrix[row][col])
                r = middle - 1;
            else
                return middle;
        }
        return -1;
    }
     */
}
