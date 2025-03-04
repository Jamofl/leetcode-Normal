/*
35. 搜索插入位置
给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。

你可以假设数组中无重复元素。

示例 1:

输入: [1,3,5,6], 5
输出: 2
示例 2:

输入: [1,3,5,6], 2
输出: 1
示例 3:

输入: [1,3,5,6], 7
输出: 4
示例 4:

输入: [1,3,5,6], 0
输出: 0
 */
package 二分查找;

public class Q35二分法搜索插入位置 {

    // 递归 二分法
    public int searchInsert(int[] nums, int target) {
        if (target < nums[0])
            return 0;
        if (target > nums[nums.length - 1])
            return nums.length;
        int re = binarySearch(nums, 0, nums.length - 1, target);
        if (re != -1)
            return re;
        else
            return binaryFindIndex(nums, 0, nums.length - 1, target);
    }

    private int binaryFindIndex(int[] nums, int left, int right, int target){
        if (left > right)
            return left;
        int middle = left + (right - left) / 2;
        if (nums[middle] == target)
            return middle;
        else if (target > nums[middle])
            return binaryFindIndex(nums, middle + 1, right, target);
        else
            return binaryFindIndex(nums, left, middle - 1, target);
    }

    private int binarySearch(int[] nums, int left, int right, int target){
        if (left > right)
            return -1;
        int middle = left + (right - left) / 2;
        if (nums[middle] == target)
            return middle;
        else if (target > nums[middle])
            return binarySearch(nums, middle + 1, right, target);
        else
            return binarySearch(nums, left, middle - 1, target);
    }



    // 迭代 二分法
    public int searchInser2(int[] nums, int target) {
        int middle;
        int l = 0;
        int r = nums.length - 1;
        while (l <= r){
            middle = l + (r - l) / 2;
            if (target > nums[middle])
                l = middle + 1;
            else if (target < nums[middle])
                r = middle - 1;
            else
                return middle;
        }
        return l;
    }
    public static void main(String[] args){
        Q35二分法搜索插入位置 q = new Q35二分法搜索插入位置();
        int re = q.searchInsert(new int[] {1,3,5,7}, 4);
        System.out.println(re);
    }
}
