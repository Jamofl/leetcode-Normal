/*
238. 除自身以外数组的乘积
给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。

示例:

输入: [1,2,3,4]
输出: [24,12,8,6]

提示：题目数据保证数组之中任意元素的全部前缀元素和后缀（甚至是整个数组）的乘积都在 32 位整数范围内。

说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。

进阶：
你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
 */
package Array数组;
import java.util.*;

public class Q238 {

    // 时间O（N) 空间O(N)
    // 使用左右两个数组L/R，分别存储当前元素的前缀乘积和后缀乘积(不包含当前元素的)
    // 最终答案即为L[i] * R[i]
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] L = new int[n];
        int[] R = new int[n];

        L[0] = 1;
        R[n - 1] = 1;
        for (int i = 1; i < n; i ++){
            L[i] = L[i - 1] * nums[i - 1];
        }

        for (int i = n - 2; i >= 0; i --){
            R[i] = R[i + 1] * nums[i + 1];
        }

        for (int i = 0 ; i < n; i ++){
            nums[i] = R[i] * L[i];
        }
        return nums;
    }

    // 时间O（N) 空间O(1)
    // 由于输出数组不算在空间复杂度内，那么我们可以将 L 或 R 数组用输出数组来计算。
    // 先把输出数组当作 L 数组来计算，然后再动态构造 R 数组得到结果。
    public int[] productExceptSelf2(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans, 1);

        for (int i = 1; i < n; i ++){
            ans[i] = ans[i - 1] * nums[i - 1];
        }

        int R = 1;
        for (int i = n - 2 ; i >= 0; i --){
            R = R * nums[i + 1];
            ans[i] = R * ans[i];
        }
        return ans;
    }
}
