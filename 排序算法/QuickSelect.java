package 排序算法;

public class QuickSelect {

    public int findKthSmallest(int[] nums, int k) { // k starts from 0
        if (nums == null || nums.length == 0) {
            return Integer.MIN_VALUE;
        }

        int len = nums.length;
        return quickSelect(nums, k, 0, len - 1);
    }

    private int[] swap(int[] nums, int i, int j){
        if(i == j)
            return nums;
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
        return nums;
    }

    private int quickSelect(int[] nums, int k, int start, int end){
        int pivot = nums[start];
        int i = start + 1; int j = end;
        while (i <= j){
            if (nums[i] < pivot)
                i ++;
            else if (nums[j] > pivot)
                j --;
            else {
                swap(nums, i, j);
                i ++;
                j --;
            }
        }
        swap(nums, start, j); // swap pivot and element at j

        if (k == j)
            return pivot;
        else if (k > j) // target in the right part
            return quickSelect(nums, k, j + 1, end);
        else // target in the left part
            return quickSelect(nums, k, start, j - 1);
    }

    public static void main(String[] args){

        QuickSelect q = new QuickSelect();
        int[] nums = new int[]{3,2,1,5,6,4};
        int r = q.findKthSmallest(nums, 0);
        System.out.println(r);

    }

}
