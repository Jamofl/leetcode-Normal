package 排序算法;

public class InsertionSort {


    private void swap(int[] nums, int i , int j){
        if(i == j)
            return;
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void insertionSort(int[] nums){
        int n = nums.length;
        for(int i = 0; i < n; i ++){
            for(int j = i; j > 0; j --){ 
                if(nums[j] > nums[j - 1]) // if left is less than me, just stop
                    break;
                else
                    swap(nums, j, j - 1);
            }
        }
    }

    public static void main(String[] args){
        InsertionSort I = new InsertionSort();
        int[] nums = new int[] {32,15,2,17,19,26,41,17,17};
        I.insertionSort(nums);
        System.out.println();
    }
}
