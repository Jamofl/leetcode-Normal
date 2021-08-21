package 数据结构.Heap;
import java.util.*;


public class Offer40数组中最小的k个数 {
    public int[] getLeastNumbers(int[] arr, int k) {
        if (k == 0)
            return new int[]{};
        // 大顶堆
        PriorityQueue<Integer> pq = new PriorityQueue<>((x, y) -> y - x);

        PriorityQueue<Integer> pq2 = new PriorityQueue<>((Integer x, Integer y) -> y - x);


        for (int i = 0; i < k; i ++){
            pq.add(arr[i]);
        }

        for (int i = k; i < arr.length; i ++){
            if (arr[i] < pq.peek()){
                pq.poll();
                pq.add(arr[i]);
            }
        }

        int[] ans = new int[k];
        for (int i = 0; i < k; i ++){
            ans[i] = pq.poll();
        }
        return ans;
    }
}
