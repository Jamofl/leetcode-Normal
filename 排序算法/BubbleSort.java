package 排序算法;

public class BubbleSort {


    public void swap(int[] nums, int i, int j){
        if (i == j)
            return;
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public int[] bubbleSort(int[] nums){
        for (int i = nums.length - 1; i >= 1; i --){
            for (int j = 0; j <= i - 1; j ++){
                if (nums[j] > nums[j + 1])
                    swap(nums, j, j + 1);
            }
        }
        return nums;
    }

    public static void main(String[] args){
        BubbleSort bs = new BubbleSort();
        int[] re = bs.bubbleSort(new int[]{3,5,1,7,6});
        System.out.println();
    }
}
