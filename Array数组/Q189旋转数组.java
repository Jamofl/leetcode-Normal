/*
189. 旋转数组
给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。

进阶：

尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
你可以使用空间复杂度为 O(1) 的 原地 算法解决这个问题吗？


示例 1:

输入: nums = [1,2,3,4,5,6,7], k = 3
输出: [5,6,7,1,2,3,4]
解释:
向右旋转 1 步: [7,1,2,3,4,5,6]
向右旋转 2 步: [6,7,1,2,3,4,5]
向右旋转 3 步: [5,6,7,1,2,3,4]
 */
package Array数组;

public class Q189旋转数组 {

    // 妙
    // nums = "----->-->"; k =3
    // result = "-->----->";
    // reverse "----->-->" we can get "<--<-----"
    // reverse "<--" we can get "--><-----"
    // reverse "<-----" we can get "-->----->"
    // 整体思路为: 先翻转整个数组(0, n)，然后翻转 (0 , k - 1) , 再翻转 (k, n)
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        flip(nums, 0, n - 1);
        flip(nums, 0, k - 1);
        flip(nums, k, n - 1);
    }

    private void flip(int[] nums, int start, int end){
        int i = start;
        int j = end;
        while (i < j){
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i ++;
            j --;
        }
    }
}
