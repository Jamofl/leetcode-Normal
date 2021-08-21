package 排序算法;

import java.util.Arrays;

public class HeapSort {
    // 维护一个大顶堆，每次取出堆顶元素，和数组的最后一个元素进行交换。
    private int[] swap(int[] nums, int i, int j) {
        if (i == j)
            return nums;
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
        return nums;
    }
//    private void swim(int[] nums, int i, int end){
//        int indexParent = (i - 1) / 2 <= 0 ? 0 : (i - 1) / 2;
//        if (nums[i] > nums[indexParent]){
//            swap(nums, i ,indexParent);
//            swim(nums, indexParent, end);
//        }
//    }

    /**
     * @param nums 数组
     * @param i    要下沉元素的索引
     * @param end  数组末尾(包含)
     */
    private void sink(int[] nums, int i, int end) {
        int indexLeft = i * 2 + 1 > end ? -1 : i * 2 + 1;
        int indexRight = i * 2 + 2 > end ? -1 : i * 2 + 2;
        if (indexLeft == -1 && indexRight == -1)
            return;

        int indexLarger = indexLeft;
        if (indexRight != -1)
            indexLarger = (nums[indexLeft] > nums[indexRight]) ? indexLeft : indexRight;

        if (nums[i] < nums[indexLarger]) {
            swap(nums, i, indexLarger);
            sink(nums, indexLarger, end);
        }
    }


    private void heapify(int[] nums, int end) {
        for (int i = end; i >= 0; i--) {
            sink(nums, i, end);
        }
    }

    public void heapSort(int[] nums) {
        int n = nums.length;
        heapify(nums, n - 1);
        for (int i = n - 1; i >= 0; i--) {
            swap(nums, 0, i);
            sink(nums, 0, i - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{32, 15};
        HeapSort h = new HeapSort();
        h.heapSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
