package BackTrack回溯;
import java.util.*;
/*
https://leetcode-cn.com/problems/subsets-ii/
90. 子集 II
给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。

解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。

示例 1：

输入：nums = [1,2,2]
输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
示例 2：

输入：nums = [0]
输出：[[],[0]]

提示：

1 <= nums.length <= 10
-10 <= nums[i] <= 10
 */

public class Q90带重复元素的子集 {
    List<List<Integer>> ans = new LinkedList<>();
    List<Integer> path = new LinkedList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        for (int mask = 0; mask < (1 << n); mask ++){
            path.clear();
            boolean flag = true;
            for (int i = 0; i < n; i ++){
                int temp = 1 << i;
                if ((mask & temp) != 0){ // ！= 0  但不代表为1，因为只有那一个特定的位置上位 1
                    int temp2 = 1 << (i - 1);
                    if (i - 1 >= 0 && nums[i] == nums[i - 1] && (mask & temp2) == 0){
                        flag = false;
                        break;
                    }
                    path.add(nums[i]);
                }
            }
            if (flag)
                ans.add(new LinkedList<>(path));
        }
        return ans;
    }

    public static void main(String[] args){
        Q90带重复元素的子集 q = new Q90带重复元素的子集();
        List l = q.subsetsWithDup(new int[] {1,2,2});
        System.out.println(l.toString());

    }
}
