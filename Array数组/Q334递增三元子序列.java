/*
334. 递增的三元子序列
给你一个整数数组 nums ，判断这个数组中是否存在长度为 3 的递增子序列。

如果存在这样的三元组下标 (i, j, k) 且满足 i < j < k ，使得 nums[i] < nums[j] < nums[k] ，返回 true ；否则，返回 false 。



示例 1：

输入：nums = [1,2,3,4,5]
输出：true
解释：任何 i < j < k 的三元组都满足题意
示例 2：

输入：nums = [5,4,3,2,1]
输出：false
解释：不存在满足题意的三元组
 */
package Array数组;
import java.util.*;
public class Q334递增三元子序列 {

    // 方法1 dp 复杂度O (N2)
    // 用一个dp数组保存(0,i)连续上升的最大长度，当dp[i] >= 3，循环结束，return true
    public boolean increasingTriplet(int[] nums) {
        int n = nums.length;
        if (n <= 2)
            return false;

        int[] dp = new int[n]; // 表示该数字左边(包括该数)的连续上升长度
        Arrays.fill(dp, 1);
        for (int i = 1; i < n; i ++){
            for (int j = 0; j < i; j ++){
                if (nums[i] > nums[j])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                if (dp[i] >= 3)
                    return true;
            }
        }
        return false;
    }

    /*
    方法2
    新建两个变量，min保存当前为止最小值，mid保持当前为止第二小值；
    当出现比min小的数时，更新min；
    当出现min ~ mid中间的数时，更新mid；
    当出现大于mid的数时，找到答案；
    巧妙的地方就在于，当出现一个比min小的数时，用该数更新了min，同时也说明在前面有着一个介于min和mid之间的数存在。
    若后面遇到了一个max，则可以直接找到答案
     */
    public boolean increasingTriplet2(int[] nums) {
        int n = nums.length;
        if (n <= 2)
            return false;
        int min = Integer.MAX_VALUE;
        int mid = Integer.MAX_VALUE;

        for (int i = 0; i < n; i ++){
            if (nums[i] <= min)
                min = nums[i];
            else if (nums[i] <= mid)
                mid = nums[i];
            else
                return true;
        }
        return false;
    }


    /*
    方法3   三次遍历  O（n）
    使用两个数组 mins[i] 表示（0，i）最小元素
               maxs[i] 表示（i，n-1）最大元素
    若存在 mins[i] < nums[i] < maxs[i] 则存在三连
     */
    public boolean increasingTriplet3(int[] nums) {
        int n = nums.length;
        if (n <= 2)
            return false;
        int[] mins = new int[n]; // minimum element from index 0 to i
        int[] maxs = new int[n]; // maximum element from index i to n - 1;
        LinkedList<Integer> q1 = new LinkedList<>();
        LinkedList<Integer> q2 = new LinkedList<>();
        for (int i = 0; i < n; i ++){
            increaseStackPush(q1, nums[i]);
            mins[i] = q1.getFirst();
        }

        for (int i = n - 1; i >= 0; i --){
            decreaseStackPush(q2, nums[i]);
            maxs[i] = q2.getFirst();
        }

        for (int i = 0; i < n; i ++){
            if (mins[i] < nums[i] && maxs[i] > nums[i])
                return true;
        }
        return false;
    }

    private void increaseStackPush(LinkedList<Integer> q, int num){
        while (q.size() != 0 && q.getLast() >= num)
            q.removeLast();
        q.add(num);
    }

    private void decreaseStackPush(LinkedList<Integer> q, int num){
        while (q.size() != 0 && q.getLast() <= num)
            q.removeLast();
        q.add(num);
    }
}
