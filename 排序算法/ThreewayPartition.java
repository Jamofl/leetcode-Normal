package 排序算法;

import java.util.Arrays;
// 三分法  通过一次遍历，将数组分为 小于 等于 大于 target 的三个部分；与快排中pivot不同的是，快排是将数组分为两个部分
/**
 * The following code for three-way partitioning assumes zero-based array indexing.
 * It uses three indices i, j and n, maintaining the invariant that i ≤ j. n holds the
 * boundary of numbers greater than mid. j is the position of number under consideration.
 * And i is the boundary for the numbers lesser than the mid.
 *
 * i 是小于middle的数组的上界， n是大于middle的数组的下界， j是当下考虑的元素
 * procedure three-way-partition(A : array of values, mid : value):
 *     i ← 0
 *     j ← 0
 *     n ← size of A - 1
 *
 *     while j ≤ n:
 *         if A[j] < mid: // 当前元素小于middle，交换middle（i所在位置）与小于middle的元素（j所在位置）。
 *             swap A[i] and A[j]
 *             i ← i + 1
 *             j ← j + 1
 *         else if A[j] > mid: // 当前元素大于middle， 与n交换， 且大于middle的数组的下界减一
 *             swap A[j] and A[n]
 *             n ← n - 1
 *         else: // 当前元素等于middle， 不进行操作，考虑下一个元素，留下i指针指向等于 middle 的元素
 *             j ← j + 1
 */
public class ThreewayPartition {

    private void swap(int[] nums, int i, int j){
        if(i == j)
            return;
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

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

    public static void main(String[] args){
        ThreewayPartition t = new ThreewayPartition();
        int[] nums = new int[]{3,5,4,1,2};
        t.threewayPartitioning(nums, 3);
        System.out.println(Arrays.toString(nums));
    }
}
