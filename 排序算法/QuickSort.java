package 排序算法;

public class QuickSort {

    private int[] swap(int[] nums, int i, int j){
        if (i == j)
            return nums;
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
        return nums;
    }

    public void quickSort(int[] nums, int start, int end){
        if (end <= start)
            return;
        int pivot = nums[start];

        int i = start + 1;
        int j = end;
        while (i <= j) {
            // 左指针只接受比它小的元素
            if (nums[i] < pivot)
                i ++;
            // 右指针只接受比它大的元素
            else if(nums[j] > pivot)
                j --;
            // 如果左右指针都停下了 交换
            else{
                swap(nums, i, j);
                i ++;
                j --;
            }
        }
        swap(nums, start, j); // swap pivot and element at j
        // 虽然左边的元素都比pivot小，右边的元素都比pivot大，但不能保证这些元素之间的相对顺序 所以需要递归的对左右部分再进行快排
        quickSort(nums, start, j - 1);
        quickSort(nums, j + 1, end);
    }

    public static void main(String[] args){
//        QuickSort q = new QuickSort();
//        int[] nums = new int[]{5,1,1,2,0,0};
//        q.quickSort(nums, 0 , nums.length - 1);
    }
}
