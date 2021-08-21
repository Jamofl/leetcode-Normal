package Array数组;
/*
154. 寻找旋转排序数组中的最小值 II
已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,4,4,5,6,7] 在变化后可能得到：
若旋转 4 次，则可以得到 [4,5,6,7,0,1,4]
若旋转 7 次，则可以得到 [0,1,4,4,5,6,7]
注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。

给你一个可能存在 重复 元素值的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。



示例 1：

输入：nums = [1,3,5]
输出：1
示例 2：

输入：nums = [2,2,2,0,1]
输出：0


提示：

n == nums.length
1 <= n <= 5000
-5000 <= nums[i] <= 5000
nums 原来是一个升序排序的数组，并进行了 1 至 n 次旋转


进阶：

这道题是 寻找旋转排序数组中的最小值 的延伸题目。
允许重复会影响算法的时间复杂度吗？会如何影响，为什么？
 */
public class Q154带重复元素的旋转数组最小值 {

    public int findMin(int[] nums) {
        return binarySearch1(nums, 0, nums.length - 1);
    }

    // 方法1
    // 二分法，将middle的值和区间右端点的值比较。
    // 若小于右端点的值，说明最小值在pivot左边，砍掉右半边区间。
    // 若大于右端点的值，说明最小值在pivot右边，砍掉左半边区间。
    // 若等于右端点的值，则至少可以将右端点这一个坐标点去除。
    private int binarySearch1(int[] nums, int start, int end){
        if (start == end)
            return nums[start];
        int middle = start + (end - start) / 2;

        if (nums[middle] < nums[end])
            return binarySearch1(nums, start, middle);
        else if (nums[middle] > nums[end])
            return binarySearch1(nums, middle + 1, end);
        else{
            return binarySearch1(nums, start, end - 1);
        }
    }

    // 方法2
    // 与上一个方法不同的地方在于，若pivot等于右端点的值，则说明左右两边都有可能出现最小值，
    // 则向左 向右都进行递归查询，返回较小者。
    private int binarySearch2(int[] nums, int start, int end){
        if (start == end)
            return nums[start];
        int middle = start + (end - start) / 2;

        if (nums[middle] < nums[end])
            return binarySearch2(nums, start, middle);
        else if (nums[middle] > nums[end])
            return binarySearch2(nums, middle + 1, end);
        else{
            return binarySearch2(nums, start, end - 1);
        }
    }


}
