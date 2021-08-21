package DP动态规划;
/*
128. 最长连续序列
给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。

进阶：你可以设计并实现时间复杂度为 O(n) 的解决方案吗？

示例 1：

输入：nums = [100,4,200,1,3,2]
输出：4
解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
示例 2：

输入：nums = [0,3,7,2,5,8,4,6,0,1]
输出：9

提示：

0 <= nums.length <= 104
-109 <= nums[i] <= 109
 */
import java.util.*;

public class Q128 {

    /*
    O (N2)
    // Solution 1: 使用集合，对于集合中的每个元素i，判断i + 1是否存在集合中。若集合中存在i - 1，直接跳过对i的判断，因为在i - 1时已经判断过i
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0)
            return 0;
        else if (nums.length == 1)
            return 1;

        int con = 1;
        int maxCon = 1;
        Set<Integer> set = new HashSet();
        for(int i : nums)
            set.add(i);

        for (int i : set){
            if (set.contains(i - 1))
                continue;

            int j = i + 1;
            con = 1;
            while(set.contains(j)){
                con += 1;
                j += 1;
            }
            if (con > maxCon)
                maxCon = con;
        }
        return maxCon;
    }

     */

    // Solution 2: 使用 union find (disjoint set)并查集;
    // 首先对数组中的元素进行遍历，若i + 1在数组中，则将i和i + 1连在一起。
    // 遍历disjoint set, 计算出最长连续序列的长度。
    private static class UnionFind{
        public Map<Integer, Integer> parent;
        public UnionFind(int[] nums){
            this.parent = new HashMap();
            for (int num : nums)
                parent.put(num, num);
        }

        public void union(int m, int n){
            int rootM = find(m);
            int rootN = find(n);
            if (rootM == rootN)
                return ;
//            if (m < n)
//                parent.put(rootM, rootN);
//            else
                parent.put(rootN, rootM);
        }

        // return root of n; do the path compression at the same time
        public int find(int n){
            int root = n;
            while (parent.get(root) != root)
                root = parent.get(root);

            int cur = n;
            while (parent.get(cur) != root){
                int preParent = parent.get(cur);
                parent.put(cur, root);
                cur = preParent;
            }
            return root;
        }
    }

    public int longestConsecutive(int[] nums) {
        if (nums.length == 0)
            return 0;

        UnionFind uf = new UnionFind(nums);

        for (int num : nums){
            if (uf.parent.containsKey(num + 1)){
                uf.union(num, num + 1);
            }
        }

        int ans = 0;
        for (Integer i : uf.parent.keySet()){
            ans = Math.max(ans, uf.find(i) - i + 1);
        }
        return Math.abs(ans);
    }


    public static void main(String[]  args){
        Q128.UnionFind unionfind = new Q128.UnionFind(new int[] {1,2,3,4});
        unionfind.union(1,2);
        unionfind.union(3,4);
        unionfind.union(2,3);
    }


}
