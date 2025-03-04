package 数据结构.DisjointSet;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Q128最长连续序列解法2 {
    public static int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++)
            set.add(nums[i]);

        int max = 1;
        for (Integer n : set) {
            if (!set.contains(n - 1)) {
                int cur = n;
                int tempMax = 1;
                while (set.contains(cur + 1)) {
                    tempMax += 1;
                    cur += 1;
                }
                if (tempMax > max) {
                    max = tempMax;
                }
            }
        }
        return max;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{200, 100, 1, 2, 3, 4};
        System.out.println(longestConsecutive(nums));
    }
}
