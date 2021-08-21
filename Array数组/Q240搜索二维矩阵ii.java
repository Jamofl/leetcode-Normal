/*
240. 搜索二维矩阵 II
编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：

每行的元素从左到右升序排列。
每列的元素从上到下升序排列。
 */
package Array数组;

public class Q240搜索二维矩阵ii {

    // 方法1  O(M + N)
    // 由于矩阵行递增且列递增，取左下角元素为起始元素，若target大于该元素，向右移动；若target小于该元素，向上移动，若等于，返回
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0]. length;
        if (m == 0 || n == 0)
            return false;

        int i = m - 1;
        int j = 0;
        while (i >= 0 && j <= n - 1){
            if (target > matrix[i][j])
                j ++;
            else if (target < matrix[i][j])
                i --;
            else
                return true;
        }
        return false;
    }

    // 方法2
    // 对每一行进行二分查找  O(M LON(N))
    public boolean searchMatrix2(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0)
            return false;
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i ++){
            if (target < matrix[i][0] || target > matrix[i][n - 1])
                continue;
            else{
                int re = binarySearch(matrix[i], target);
                if (re != -1)
                    return true;
            }
        }
        return false;
    }

    private int binarySearch(int[] nums, int target){
        int l = 0;
        int r = nums.length - 1;
        int middle;
        while (l <= r){
            middle = l + (r - l) / 2;
            if (target > nums[middle])
                l = middle + 1;
            else if (target < nums[middle])
                r = middle - 1;
            else
                return middle;
        }
        return -1;
    }

}
