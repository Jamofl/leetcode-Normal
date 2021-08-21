package 排序算法;

public class SelectionSort {

    public void selectionSort(int[] nums){
        int smallestIndex;
        for(int i = 0; i < nums.length; i ++){
            smallestIndex = i;
            for(int j = i + 1; j < nums.length; j ++){
                if (nums[j] < nums[smallestIndex])
                    smallestIndex = j;
            }
            swap(nums, i, smallestIndex);
        }
    }

    private void swap(int[] nums, int i , int j){
        if(i == j)
            return;
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args){
        SelectionSort s = new SelectionSort();
        int[] nums = new int[] {32,15,2,17,19,26,41,17,17};
        s.selectionSort(nums);
        System.out.println();

    }
}
