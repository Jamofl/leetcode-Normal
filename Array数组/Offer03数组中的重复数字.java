package Array数组;
/*
剑指 Offer 03. 数组中重复的数字
找出数组中重复的数字。


在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。

示例 1：

输入：
[2, 3, 1, 0, 2, 5, 3]
输出：2 或 3


限制：

2 <= n <= 100000
 */
public class Offer03数组中的重复数字 {


    // 官方解法:
    // https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/solution/yuan-di-zhi-huan-shi-jian-kong-jian-100-by-derrick/
    /*
    如果有重复元素，例如：
 nums[i]     1  2  3  2    萝卜
     i       0  1  2  3    坑
同样的，0号坑不要1号，先和1号坑交换，交换完这样的：

 nums[i]     2  1  3  2    萝卜
     i       0  1  2  3    坑
0号坑不要2号萝卜，去和2号坑交换，交换完这样的：

 nums[i]     3  1  2  2    萝卜
     i       0  1  2  3    坑
0号坑不要3号萝卜，去和3号坑交换，交换完这样的：

 nums[i]     2  1  2  3    萝卜
     i       0  1  2  3    坑
0号坑不要2号萝卜，去和2号坑交换，结果发现你2号坑也是2号萝卜，那我还换个锤子，同时也说明有重复元素出现。
     */
    public int findRepeatNumber(int[] nums) {
        int i = 0;
        while (true){
            if (i == nums[i])
                i ++;
            else{
                if (nums[i] == nums[nums[i]])
                    return nums[i];
                else
                    swap(nums, i, nums[i]);
            }
        }
    }

    public void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
