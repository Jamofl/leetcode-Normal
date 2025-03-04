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
        // 找到左右子节点的index
        int indexLeft = i * 2 + 1 > end ? -1 : i * 2 + 1;
        int indexRight = i * 2 + 2 > end ? -1 : i * 2 + 2;
        // 若当前就是叶子节点（indexLeft == -1就可以代表 因为左边为-1 右边一定为-1），直接返回
        if (indexLeft == -1 && indexRight == -1)
            return;

        // 找到左右子节点中 数值更大的那个叶子节点
        int indexLarger = indexLeft;
        if (indexRight != -1)
            indexLarger = (nums[indexLeft] > nums[indexRight]) ? indexLeft : indexRight;

        // 若当前节点的值 < 较大的那个子节点的值 交换；然后继续下沉交换后的子节点
        if (nums[i] < nums[indexLarger]) {
            swap(nums, i, indexLarger);
            sink(nums, indexLarger, end);
        }
    }


    private void heapify(int[] nums, int end) {
        // 因为是通过下沉来进行堆化的 下沉后 只有数组末尾的元素可以保证是最小的 不会再改动的
        // 而数组前面的元素很可能已经被替换了 此时如果正序遍历 可能会漏掉某些元素 所以需要倒叙下沉来建堆
        // 可以参考数组-4,0,7,4,9 如果正序下沉堆化 在对-4下沉后 7跑到了队首 由于i++ 那么7永远无法再次下沉了

        for (int i = end; i >= 0; i--) {
            sink(nums, i, end);
        }
    }

    public void heapSort(int[] nums) {
        int n = nums.length;
        // 1.建堆 O(n)
        heapify(nums, n - 1);
        for (int i = n - 1; i >= 0; i--) {
            // 交换【堆顶元素】 和 【数组末尾元素】
            swap(nums, 0, i);
            // 把【当前元素】下沉到适当位置，堆的边界变为i-1。  本质就是重新堆化一下 保持堆顶仍为最大元素
            // 当i = 0 时，边界变为 -1 此时sink会直接return
            sink(nums, 0, i - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-4,0,7,4,9};
        HeapSort h = new HeapSort();
        h.heapSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
