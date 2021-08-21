package Array数组;
/*
153. 寻找旋转排序数组中的最小值
已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：
若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
若旋转 7 次，则可以得到 [0,1,2,4,5,6,7]
注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。

给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。

示例 1：

输入：nums = [3,4,5,1,2]
输出：1
解释：原数组为 [1,2,3,4,5] ，旋转 3 次得到输入数组。
示例 2：

输入：nums = [4,5,6,7,0,1,2]
输出：0
解释：原数组为 [0,1,2,4,5,6,7] ，旋转 4 次得到输入数组。
示例 3：

输入：nums = [11,13,15,17]
输出：11
解释：原数组为 [11,13,15,17] ，旋转 4 次得到输入数组。

提示：
n == nums.length
1 <= n <= 5000
-5000 <= nums[i] <= 5000
nums 中的所有整数 互不相同
nums 原来是一个升序排序的数组，并进行了 1 至 n 次旋转
 */
public class Q153旋转数组最小值 {
    public int findMin(int[] nums) {
        return binarySearch3(nums, 0, nums.length - 1);

    }


    // 初始版本 考虑的情况较多 较复杂
    private int binarySearch(int[] nums){
        int start = 0;
        int end = nums.length - 1;
        int middle = start + (end - start) / 2;

        while (start <= end){
            if (start == end)
                return nums[start];
            else if (start == end - 1)
                return Math.min(nums[start], nums[end]);

            middle = start + (end - start) / 2;
            if (nums[start] < nums[middle] && nums[middle] < nums[end]) // a < b < c 左边找
                end = middle - 1;   // 不会取到middle
            else if (nums[middle] < nums[end] && nums[end] < nums[start]) // b < c < a 左边找
                end = middle;       // 可以取到middle
            else if (nums[end] < nums[start] && nums[start] < nums[middle]) // c < a < b 右边找
                start = middle + 1; // 不会取到middle
        }
        return -1;
    }

    // 简洁 迭代版本
    // 二分法，将middle的值和区间右端点的值比较。
    // 若小于右端点的值，说明最小值在pivot左边，砍掉右半边区间。
    // 若大于右端点的值，说明最小值在pivot右边，砍掉左半边区间。
    private int binarySearch2(int[] nums){
        int start = 0;
        int end = nums.length - 1;
        int middle;

        while (start < end){
            middle = start + (end - start) / 2;
            if (nums[middle] < nums[end])
                end = middle;
            else if (nums[middle] > nums[end])
                start = middle + 1;
        }
        return nums[start];
    }

    // 简洁 递归版本
    private int binarySearch3(int[] nums, int start, int end){
        if (start == end)
            return nums[start];
        int middle = start + (end - start) / 2;
        if (nums[middle] < nums[end])
            return binarySearch3(nums, start, middle);
        else if (nums[middle] > nums[end])
            return binarySearch3(nums, middle + 1, end);
        return -1;
    }



    public static void main(String[] args){
        Q153旋转数组最小值 q = new Q153旋转数组最小值();
        int r = q.findMin(new int[] {3,4,5,1,2});
        System.out.println(r);
    }
}
