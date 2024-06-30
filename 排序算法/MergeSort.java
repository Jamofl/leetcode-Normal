package 排序算法;

import java.util.Arrays;

public class MergeSort {
    // 全局变量 用于进行inplace - merge sort
    int[] temp;

    private int[] sort(int[] nums){
        temp = new int[nums.length];
        mergeSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void mergeSort(int[] nums, int start, int end){
        // 如果只有一个元素 直接返回
        if (end == start)
            return;
        int middle = start + (end - start) / 2;

        mergeSort(nums, start, middle); // sort left
        mergeSort(nums, middle + 1, end); // sort right

        // merge sorted-left part and sorted-right part
        int i = start;      // 左半边的起点
        int j = middle + 1; // 右半边的起点
        int index = start;
        while(i <= middle && j <= end){
            if (nums[i] < nums[j]){
                temp[index ++] = nums[i ++];
            }
            else {
                temp[index ++] = nums[j ++];
            }
        }
        // 如果左半边还剩余元素，放到temp里
        while(i <= middle)
            temp[index ++] = nums[i ++];
        // 如果右半边还剩余元素，放到temp里
        while(j <= end)
            temp[index ++] = nums[j ++];

        // in place merge sort
        for(int k = start; k <= end; k ++)
            nums[k] = temp[k];
    }

    public static void main(String[] args){
        MergeSort m = new MergeSort();
        int[] re = m.sort(new int[]{5,3,4,2,1,6});
        System.out.println(Arrays.toString(re));
    }
}
