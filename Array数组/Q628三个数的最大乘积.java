package Array数组;
/*
628. 三个数的最大乘积
给你一个整型数组 nums ，在数组中找出由三个数组成的最大乘积，并输出这个乘积。

示例 1：

输入：nums = [1,2,3]
输出：6
示例 2：

输入：nums = [1,2,3,4]
输出：24
示例 3：

输入：nums = [-1,-2,-3]
输出：-6


提示：

3 <= nums.length <= 104
-1000 <= nums[i] <= 1000
 */
import java.util.*;
public class Q628三个数的最大乘积 {


    /*
      数学的方法:  首先将数组排好序，
      若全为正整数或全为负整数，则三个数的最大乘积，一定是最大的三个元素相乘。
      若正负整数交替，则三个数的最大乘积，为 max(最小的两个数 乘以 最大的那个数， 最大的三个元素相乘)
     */

    // 方法1: 排序，然后取 最大的三个数 和 最小的两个数  O(NLogN)
    public int maximumProduct(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        return Math.max(nums[n - 1] * nums[n - 2] * nums[n - 3], nums[0] * nums[1] * nums[n - 1]);
    }

    // 方法2: 直接一遍遍历，获得数组中最大的三个元素和最小的两个元素 O(N)
    public int maximumProduct2(int[] nums) {
        int n = nums.length;
        int firstMax = Integer.MIN_VALUE, secondMax = Integer.MIN_VALUE, thirdMax = Integer.MIN_VALUE;
        int firstMin = Integer.MAX_VALUE, secondMin = Integer.MAX_VALUE;
        for (int i = 0; i < n; i ++){
            if (nums[i] < firstMin){
                secondMin = firstMin;
                firstMin = nums[i];
            }
            else if (nums[i] < secondMin)
                secondMin = nums[i];

            if (nums[i] > firstMax){
                thirdMax = secondMax;
                secondMax = firstMax;
                firstMax = nums[i];
            }
            else if (nums[i] > secondMax){
                thirdMax = secondMax;
                secondMax = nums[i];
            }
            else if (nums[i] > thirdMax)
                thirdMax = nums[i];
        }
        return Math.max(firstMax * secondMax * thirdMax,  firstMin * secondMin * firstMax);
    }
}
