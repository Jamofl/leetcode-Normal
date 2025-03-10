/*
496. 下一个更大元素 I
给定两个 没有重复元素 的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。找到 nums1 中每个元素在 nums2 中的下一个比其大的值。

nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。如果不存在，对应位置输出 -1 。



示例 1:

输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
输出: [-1,3,-1]
解释:
    对于num1中的数字4，你无法在第二个数组中找到下一个更大的数字，因此输出 -1。
    对于num1中的数字1，第二个数组中数字1右边的下一个较大数字是 3。
    对于num1中的数字2，第二个数组中没有下一个更大的数字，因此输出 -1。
示例 2:

输入: nums1 = [2,4], nums2 = [1,2,3,4].
输出: [3,-1]
解释:
    对于 num1 中的数字 2 ，第二个数组中的下一个较大数字是 3 。
    对于 num1 中的数字 4 ，第二个数组中没有下一个更大的数字，因此输出 -1 。


提示：

nums1和nums2中所有元素是唯一的。
nums1和nums2 的数组大小都不超过1000。
 */
package 数据结构.MonotonouosStack;
import java.util.*;

public class Q496下一个更大元素 {
    /*
    Solution:
    对于nums2数组，建立一个单调栈，找到每个元素对应的NGE，并将这些结果存储在一个hashmap中。
    然后遍历hashmap，找到nums1中每个元素的NGE.
    其中，寻找NGE的过程为：若nums[i] > stack.peek(), 则弹出栈顶元素，并将弹出元素的NGE置为nums[i];
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack();
        int[] ans = new int[nums1.length];
        Map<Integer, Integer> map = new HashMap();
        for(int i = 0 ; i < nums2.length; i ++){
            while (stack.size() != 0 && stack.peek() < nums2[i])
                map.put(stack.pop(), nums2[i]);
            stack.push(nums2[i]);
        }

        for(int i = 0; i < nums1.length; i ++){
            ans[i] = map.getOrDefault(nums1[i], -1);
        }
        return ans;
    }
}
