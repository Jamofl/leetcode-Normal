import java.util.*;
public class FindKthLargestQ215 {


    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return Integer.MIN_VALUE;
        }

        int len = nums.length;
        return quickSelect(nums, k, 0, len - 1);
    }

    private void swap(int[] nums, int i, int j){
        if(i == j)
            return ;
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private int quickSelect(int[] nums, int k, int start, int end){
        int pivot = nums[start];
        int i = start + 1; int j = end;
        int indexOfk = nums.length - k; // 第 k 大 即为从左到右第 n - k 个元素
        while(i <= j){
            if(nums[i] < pivot)
                i ++;
            else if(nums[j] > pivot)
                j --;
            else { //if(nums[i] >= pivot && nums[j] <= pivot){
                swap(nums, i, j);
                i ++;
                j --;
            }
        }
        swap(nums, start, j); // swap pivot and element at j

        if (indexOfk == j)
            return pivot;
        else if(indexOfk > j) // target in the right part
            return quickSelect(nums, k, j + 1, end);
        else // target in the left part
            return quickSelect(nums, k, start, j - 1);
    }


    // 方法2  小顶堆
    public int findKthLargest2(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // small heap

        for (int i = 0; i < nums.length; i ++){
            if (i >= k){
                pq.add(nums[i]);
                pq.poll();
            }
            else
                pq.add(nums[i]);
        }
        return pq.peek();
    }


    // 方法3 自己建 小顶堆
    private class Heap{ // small heap
        public int[] heap;  // 0 ~ n - 1
        public int n;
        public Heap(int[] heap, int n){
            this.heap = heap;
            this.n = n;
            heapify();
        }

        public void sink(int i){
            int indexLeftChild = 2 * i + 1 >= n ? -1 : 2 * i + 1;
            int indexRightChild = 2 * i + 2 >= n ? -1 : 2 * i + 2;
            if (indexLeftChild == -1 && indexRightChild == -1) // 叶子节点 可以简化为 if (leftChild == -1)因为左边为-1时右边一定为-1
                return;

            int indexLittleChild = indexLeftChild;
            if (indexRightChild != -1)
                indexLittleChild = (heap[indexLeftChild] < heap[indexRightChild]) ? indexLeftChild : indexRightChild;

            if (heap[i] > heap[indexLittleChild]){
                swap(heap, i, indexLittleChild);
                sink(indexLittleChild);
            }
        }

        public void swap(int[] nums, int i, int j){
            if (i == j)
                return;
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }

        public void heapify(){
            for (int i = n - 1; i >= 0; i --)
                sink(i);
        }

        public int peek(){
            return heap[0];
        }

        public void offer(int num){
            heap[0] = num;
            sink(0);
//            heapify();
        }

    }

    public int findKthLargest3(int[] nums, int k) {
        int n = nums.length;
        int[] heapNums = new int[k];
        for (int i = 0; i < k; i ++)
            heapNums[i] = nums[i];

        Heap heap = new Heap(heapNums, k);
        // System.out.println(Arrays.toString(heap.heap));

        for (int i = k; i < n; i ++){
            if (nums[i] > heap.peek())
                heap.offer(nums[i]);
            // System.out.println(Arrays.toString(heap.heap));
        }
        return heap.peek();
    }



    public static void main(String[] args){
        FindKthLargestQ215 f = new FindKthLargestQ215();
        int[] nums = new int[]{3,2,3,1,2,4,5,5,6};
        int r = f.findKthLargest(nums, 4);
        System.out.println(r);
    }

}
