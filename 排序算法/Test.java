package 排序算法;
import java.util.*;
public class Test {
    private void swap(int[] nums, int i , int j){
        if (i == j)
            return;
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void quickSort(int[] nums){
        quickSortHelper(nums, 0, nums.length - 1);
    }

    public void quickSortHelper(int[] nums, int start, int end){
        if (start >= end)
            return;
        int pivot = start;
        int i = start + 1;
        int j = end;
        while (i <= j){
            if (nums[i] < nums[pivot])
                i++;
            else if (nums[j] > nums[pivot])
                j --;
            else{
                swap(nums, i, j);
                i ++;
                j --;
            }
        }
        swap(nums, pivot, j);
        quickSortHelper(nums, start, j - 1);
        quickSortHelper(nums, j + 1, end);
    }


    public static void main(String[] args) {
        //Scanner in = new Scanner(System.in);
        //int a = in.nextInt();
        //System.out.println(a);M
        Test m = new Test();

        int[] nums = new int[]{2,4,6,1,7,0,9,3};
        //selectionSort(nums);
        //insertionSort(nums);
        //m.heapSort(nums);
        //m.mergeSort(nums);
        m.quickSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
