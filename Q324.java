import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 给定一个无序的数组 nums，将它重新排列成 nums[0] < nums[1] > nums[2] < nums[3]... 的顺序。

 示例 1:

 输入: nums = [1, 5, 1, 1, 6, 4]
 输出: 一个可能的答案是 [1, 4, 1, 5, 1, 6]
 示例 2:

 输入: nums = [1, 3, 2, 2, 3, 1]
 输出: 一个可能的答案是 [2, 3, 1, 3, 1, 2]
 说明:
 你可以假设所有输入都会得到有效的结果。

 进阶:
 你能用 O(n) 时间复杂度和 / 或原地 O(1) 额外空间来实现吗？

 思路： 1. 快速排序找到中位数
       2. 分成 小于和大于 两个数组
       3. 逆序，逐个插入

 */

public class Q324 {

    private int findKthSmallest(int[] nums, int k) {
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
        while(i <= j){
            if(nums[i] < pivot)
                i ++;
            else if(nums[j] > pivot)
                j --;
            else{ // i.e. nums[i] >= pivot and nums[j] <= pivot
                swap(nums, i, j);
                i ++;
                j --;
            }
        }
        swap(nums, start, j); // swap pivot and element at j

        if(k == j)
            return pivot;
        else if(k > j) // target in the right part
            return quickSelect(nums, k, j + 1, end);
        else // target in the left part
            return quickSelect(nums, k, start, j - 1);
    }

    // re - arrange the array in the order of : smaller numbers, middle numbers and bigger numbers.
    public void threewayPartitioning(int[] nums, int middle){
        int i = 0; int j = 0;
        int n = nums.length - 1;
        while(j <= n){
            if(nums[j] < middle){
                swap(nums, i, j);
                i ++;
                j ++;
            }
            else if(nums[j] > middle){
                swap(nums, j, n);
                n --;
            }
            else
                j ++;
        }
    }

    public void wiggleSort(int[] nums) {
        int len = nums.length;
        int middle = findKthSmallest(nums, (nums.length - 1) / 2);
        List<Integer> smaller = new LinkedList<>();
        List<Integer> larger = new LinkedList<>();

        threewayPartitioning(nums, middle);
        int middleIndex = (nums.length - 1) / 2;
        for(int i = 0; i <= middleIndex; i ++)
            smaller.add(nums[i]);
        for(int i = middleIndex + 1; i < nums.length; i ++)
            larger.add(nums[i]);

        Collections.reverse(smaller);
        Collections.reverse(larger);
        boolean flag = true;
        for(int i = 0 ; i < len; i++){
            if(flag)
                nums[i] = smaller.remove(0);
            else
                nums[i] = larger.remove(0);
            flag = !flag;
        }
    }

    public static void main(String[] args){
        Q324 q = new Q324();
        int[] nums = new int[]{1,3,2,2,2,1,1,3,1,1,2};
        q.wiggleSort(nums);
        System.out.println();
    }
}
