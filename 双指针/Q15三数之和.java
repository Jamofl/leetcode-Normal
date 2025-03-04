package 双指针;

import java.util.*;

public class Q15三数之和 {
    public static List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> re = new ArrayList<>();
        for (int i = 0; i <= n - 1; i ++){
            // 遇到重复元素 跳过 无论上次在nums[i]的这轮搜索有答案还是没有答案 都是重复搜索了
            if (i >= 1 && nums[i] == nums[i - 1]){
                continue;
            }
            int l = i + 1, r = n - 1;
            while (l < r){ // 一个数不能取两次 所以l严格小于r
                if (nums[i] + nums[l] + nums[r] == 0){
                    // 找到答案了 加入结果集
                    re.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    // 左指针右移 并跳过重复元素
                    l ++;
                    while (l < r && nums[l] == nums[l - 1]) l ++;
                    // 右指针左移 并跳过重复原数据
                    r --;
                    while (l < r && nums[r] == nums[r + 1]) r--;
                }
                // 如果三数之和>0  则右指针左移
                else if (nums[i] + nums[l] + nums[r] > 0){
                    r --;
                }
                // 如果三数之和<0  则左指针右移
                else{
                    l ++;
                }
            }
        }
        return re;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-1,0,1,2,-1,-4};

        System.out.println(threeSum(nums));
    }
}
