package Array数组;
/*
15. 三数之和
给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。

注意：答案中不可以包含重复的三元组。

示例 1：

输入：nums = [-1,0,1,2,-1,-4]
输出：[[-1,-1,2],[-1,0,1]]
示例 2：

输入：nums = []
输出：[]
示例 3：

输入：nums = [0]
输出：[]


提示：

0 <= nums.length <= 3000
-105 <= nums[i] <= 105
通过次数455,876提交次数1,450,512
 */
import java.util.*;

public class Q15三数之和 {
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length == 0)
            return new LinkedList<>();

        int n = nums.length;
        List<List<Integer>> ans = new LinkedList<>();

        // 先对数组进行排序
        Arrays.sort(nums);

        for (int i = 0; i < n; i ++){

            // 当前元素和上一个元素不相等
            if (i == 0 || nums[i] != nums[i - 1]){
                // 双指针
                int k = n - 1;
                for (int j = i + 1; j < n; j ++){
                    if (j == i + 1 || nums[j] != nums[j - 1]){
                        while (k > j && nums[i] + nums[j] + nums[k] > 0)
                            k --;
                        if (k > j && nums[i] + nums[j] + nums[k] == 0)
                            ans.add(List.of(nums[i], nums[j], nums[k]));
                        else if (nums[i] + nums[j] + nums[k] < 0)
                            continue;
                    }
                }
            }
        }
        return ans;
    }
}
