/*
209. 长度最小的子数组
给定一个含有 n 个正整数的数组和一个正整数 target 。

找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。



示例 1：

输入：target = 7, nums = [2,3,1,2,4,3]
输出：2
解释：子数组 [4,3] 是该条件下的长度最小的子数组。
示例 2：

输入：target = 4, nums = [1,4,4]
输出：1
示例 3：

输入：target = 11, nums = [1,1,1,1,1,1,1,1]
输出：0


提示：

1 <= target <= 109
1 <= nums.length <= 105
1 <= nums[i] <= 105
 */
package Array数组;
import java.util.*;

public class Q209长度最小子数组 {
    // 滑动窗口法  维护变量sum存储从num[i] 到 num[j]的和
    // 如果 sum >= target : i ++ ; 更新最小len
    // 如果 sum < target : j --
    public int minSubArrayLen(int target, int[] nums) {
        int i = 0; int j = 0;
        int len = nums.length + 1;
        int sum = nums[0];
        while (true){
            if (sum >= target){
                len = Math.min(len, j - i + 1);
                sum = sum - nums[i];
                i ++;
            }
            else{
                j ++;
                if (j == nums.length)
                    break;
                sum = sum + nums[j];
            }
        }
        return (len == nums.length + 1) ? 0 : len;
    }

    public static void main(String[] args){
//        int r = Arrays.binarySearch(new int[]{1,2,3,4,5}, -2);
//        System.out.println(r);
        int[] temp = new int[] {1,2,3};
        int[] t = Arrays.copyOfRange(temp, 0, 1);

    }
}
