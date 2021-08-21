/*
376. 摆动序列
如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为摆动序列。第一个差（如果存在的话）可能是正数或负数。少于两个元素的序列也是摆动序列。

例如， [1,7,4,9,2,5] 是一个摆动序列，因为差值 (6,-3,5,-7,3) 是正负交替出现的。相反, [1,4,7,2,5] 和 [1,7,4,5,5] 不是摆动序列，第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一个差值为零。

给定一个整数序列，返回作为摆动序列的最长子序列的长度。 通过从原始序列中删除一些（也可以不删除）元素来获得子序列，剩下的元素保持其原始顺序。

示例 1:

输入: [1,7,4,9,2,5]
输出: 6
解释: 整个序列均为摆动序列。
示例 2:

输入: [1,17,5,10,13,15,10,5,16,8]
输出: 7
解释: 这个序列包含几个长度为 7 摆动序列，其中一个可为[1,17,10,13,10,16,8]。
示例 3:

输入: [1,2,3,4,5,6,7,8,9]
输出: 2
进阶:
你能否用 O(n) 时间复杂度完成此题?
 */
package DP动态规划;

public class Q376摆动序列 {

    // 方法1 动态规划
    // flag为山峰/山谷标志位。
    // 若flag == true，当前为山峰，需要寻找下一个严格小于的数；若flag == false，当前为山谷，需要寻找下一个严格大于的数字
    // 初始化时给flag赋两个值，返回较大的那一个
    private int greedy(int[] nums, boolean flag){
        // n >= 2
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = 1;
        if (nums[1] > nums[0]){
            flag = true;
            dp[1] = 2;
        }
        else if (nums[1] < nums[0]){
            flag = false;
            dp[1] = 2;
        }
        else
            dp[1] = 1;


        for (int i = 2; i < n; i ++){
            if (flag){ // 山峰
                if (nums[i] < nums[i - 1]){
                    dp[i] = dp[i - 1] + 1;
                    flag = false;
                }
                else
                    dp[i] = dp[i - 1];
            }
            else{ // 山谷
                if (nums[i] > nums[i - 1]){
                    dp[i] = dp[i - 1] + 1;
                    flag = true;
                }
                else
                    dp[i] = dp[i - 1];
            }
        }
        return dp[n -1];
    }

    public int wiggleMaxLength(int[] nums) {
        if (nums.length < 2)
            return nums.length;
        return Math.max(greedy(nums, true), greedy(nums, false));
    }

    // 方法2:  贪心算法  (不好想)
    // 只需要统计出数组中上升的趋势和下降的趋势的个数。  将上升下降的趋势动态的连接起来，最长的即为所求
    public int wiggleMaxLength2(int[] nums) {
        int n = nums.length;
        if (nums.length < 2)
            return n;

        int re = (nums[0] != nums[1]) ? 2 : 1; // 若前两个元素不相等, re = 2;若相等, re = 1;
        int preDiff = nums[1] - nums[0];

        for (int i = 2; i < n; i ++){
            int dif = nums[i] - nums[i - 1]; // 当前的差值
            if ((dif > 0 && preDiff <= 0) || (dif < 0 && preDiff >= 0)){ //若当前差值和上一次的差值相反，说明出现了波峰或波谷
                re ++;
                preDiff = dif;
            }
        }
        return re;
    }

    // 方法3 贪心
    // 设up和down为截止到目前，上升沿和下降沿的次数。
    // 若cur > pre, 则up = donw + 1; 若cur < pre, 则 down = up + 1; 最后返回max(up, down)
    public int wiggleMaxLength3(int[] nums) {
        int n = nums.length;
        if (n < 2)
            return n;

        int up = 1, donw = 1;
        for (int i = 1; i < n; i ++){
            if (nums[i] > nums[i - 1])
                up = donw + 1;
            else if (nums[i] < nums[i - 1])
                donw = up + 1;
        }
        return Math.max(up, donw);
    }
}
