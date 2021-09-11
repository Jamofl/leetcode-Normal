package 排序算法;

import java.util.Random;

public class QuickSort {
    // 快排最差情况下可能退化为n2  即 当数组已经几乎排好序时，每次pivot都会选择第一个元素，都是最小的元素，本质上成了冒泡排序
    public static Random r = new Random();

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
        // 随机生成生成一个下标 作为我们的pivot 把它移动到队首
        int randomIndex = r.nextInt(end - start + 1) + start;
        swap(nums, start, randomIndex);
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
        for (int i = 0; i < 10; i ++)
            System.out.println(r.nextInt(2));
    }
}
