import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 378. 有序矩阵中第K小的元素
 * 给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。
 * 请注意，它是排序后的第 k 小元素，而不是第 k 个不同的元素。
 *
 * 示例：
 *
 * matrix = [
 *    [ 1,  5,  9],
 *    [10, 11, 13],
 *    [12, 13, 15]
 * ],
 * k = 8,
 *
 * 返回 13。
 *
 *
 * 提示：
 * 你可以假设 k 的值永远是有效的，1 ≤ k ≤ n2 。
 */
public class Q378 {


    // O(n2 log n2)
//    public int kthSmallest(int[][] matrix, int k) {
//        PriorityQueue<Integer> pq = new PriorityQueue<>();
//        for(int i = 0; i < matrix.length; i ++){
//            for(int j = 0; j < matrix[0].length; j ++){
//                pq.add(matrix[i][j]);
//            }
//        }
//        int r = 0;
//        while(k > 0){
//            r = pq.poll();
//            k --;
//        }
//        return r;
//    }

    // O(k log n)
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] t1, int[] t2) {
                return t1[0] - t2[0];
            }
        });
        for(int i = 0; i < n; i++)
            pq.add(new int[]{matrix[i][0], i, 0}); // 第i行 第0个 元素
        int[] temp = new int[]{0, 0, 0};
        while (k > 0){
            temp = pq.poll();
            if(temp[2] + 1 < n)
                pq.add(new int[]{matrix[temp[1]][temp[2] + 1], temp[1], temp[2] + 1}); // 第i行 第j列
            k --;
        }
        return temp[0];


    }
    public static void main(String[] args){
        Q378 q = new Q378();
        int[][] matrix = new int[][]{{1,5,9}, {2,4,6}, {3,8,10}};
        int r = q.kthSmallest(matrix, 7);
        System.out.println(r);
    }
}
