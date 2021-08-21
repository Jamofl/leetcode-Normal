/**
 * 剑指 Offer 51. 数组中的逆序对
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
 *
 *
 *
 * 示例 1:
 *
 * 输入: [7,5,6,4]
 * 输出: 5S
 */
public class Offer51数组中的逆序对 {
    int[] temp;
    int count;

    public int reversePairs(int[] nums) {
        if(nums.length == 0)
            return 0;
        temp = new int[nums.length];
        count = 0;
        mergeSort(nums, 0, nums.length - 1);
        return count;
    }

    private void mergeSort(int[] nums, int start, int end){
        if(end == start)
            return ;
        // 1.merge sort right and left part
        int middle = start + (end - start) / 2;
        mergeSort(nums, start, middle);
        mergeSort(nums, middle + 1, end);

        // 2.merge right and left parts together
        int i = start;
        int j = middle + 1;
        int index = start;
        while (i <= middle && j <= end){
            if(nums[i] <= nums[j]){ // 如果指针的元素小于右指针的元素，说明存在 r - (middle + 1) 个逆序对
                count += j - (middle + 1);
                temp[index ++] = nums[i ++];
            }
            else{
                temp[index ++] = nums[j ++];
            }
        }

        while(i <= middle) {
            count += j - (middle + 1);
            temp[index++] = nums[i++];
        }

        while(j <= end)
            temp[index ++] = nums[j ++];

        // 3.copy sorted array to nums
        for(int k = start; k <= end; k ++)
            nums[k] = temp[k];
    }
}
