/*
283. 移动零
给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。

示例:

输入: [0,1,0,3,12]
输出: [1,3,12,0,0]
说明:

必须在原数组上操作，不能拷贝额外的数组。
尽量减少操作次数。
通过次数309,940提交次数486,774
 */
package Array数组;

public class Q283移动零 {


    // O(n2)
    // 采用和冒泡排序相似的思路，不断将0向后冒泡
    public void moveZeroes(int[] nums) {
        int i = 0;
        int zeroCount = 0;
        for (int num : nums){
            if (num == 0)
                zeroCount ++;
        }
        while (i < nums.length - zeroCount){
            if (nums[i] == 0){
                for (int j = i; j <= nums.length - 2; j ++){
                    if (nums[j + 1] != 0){
                        swap(nums, j, j + 1);
                    }
                }
            }
            else
                i ++;
        }
    }

    // 双指针
    // 采用和快速排序相似的思路，不断将i指针所指的0元素和j指针所指的非零元素进行交换
    public void moveZeroes2(int[] nums) {
        int i = 0, j = 0;
        int n = nums.length;
        while (i <= j && j < n){
            if (nums[j] == 0) // j指针元素为0,j ++
                j ++;
            else { // j指针元素不为0
                if (nums[i] != 0){ // i 不为0, i ++, j ++
                    i ++;
                    j ++;
                }
                else { // i 为0, 交换
                    swap(nums, i, j);
                    i ++;
                }
            }
        }
    }

    public void swap(int[] nums, int i, int j){
        if (i == j)
            return;
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
