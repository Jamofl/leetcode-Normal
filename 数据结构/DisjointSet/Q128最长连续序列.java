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
 */
package 数据结构.DisjointSet;
import java.util.*;

public class Q128最长连续序列 {private class UnionFind{

    // 方法1
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
        // if (m < n)
        //     parent.put(rootM, rootN);
        // else
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
            ans = Math.max(ans, Math.abs(uf.find(i) - i) + 1);
        }
        return Math.abs(ans);
    }
}
