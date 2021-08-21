/*
169. 多数元素
给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。

你可以假设数组是非空的，并且给定的数组总是存在多数元素。



示例 1：

输入：[3,2,3]
输出：3
示例 2：

输入：[2,2,1,1,1,2,2]
输出：2


进阶：

尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
 */
package Array数组;

public class Q169多数元素 {
    
    // 分治算法
    public int majorityElement(int[] nums) {
        return majorityElementrRec(nums, 0, nums.length - 1);
    }

    public int majorityElementrRec(int[] nums, int l, int r){
        // base case
        if (l == r)
            return nums[l];

        int middle = l + (r - l) / 2; // in case of overflow
        int leftMajor = majorityElementrRec(nums, l, middle);
        int rightMajor = majorityElementrRec(nums, middle + 1, r);

        // if left part and right part share the same majority number
        if (leftMajor == rightMajor)
            return leftMajor;

            // otherwise, pick the real majority number
        else{
            int rightCount = count(nums, l, r, rightMajor);
            int leftCount = count(nums, l, r, leftMajor);
            return (leftCount > rightCount) ? leftMajor : rightMajor;
        }
    }

    // count the time that target occurs in Array nums (from l to r);
    private int count(int[] nums, int l, int r, int target){
        int times = 0;
        for (int i = l; i <= r; i ++){
            if (nums[i] == target)
                times ++;
        }
        return times;
    }

}
