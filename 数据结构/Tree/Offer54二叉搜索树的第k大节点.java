package 数据结构.Tree;
/*
https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof/
剑指 Offer 54. 二叉搜索树的第k大节点
给定一棵二叉搜索树，请找出其中第k大的节点。

示例 1:

输入: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
输出: 4
示例 2:

输入: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
输出: 4

限制：

1 ≤ k ≤ 二叉搜索树元素个数
 */
import java.util.*;

public class Offer54二叉搜索树的第k大节点 {
    public static class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode() {}
          TreeNode(int val) {
              this.val = val;
              this.left = null;
              this.right = null;
          }
          TreeNode(int val, TreeNode left, TreeNode right) {
             this.val = val;
              this.left = left;
              this.right = right;
          }
    }

    // 解法1 : level order遍历该树，然后排序，然后取 第 n - k 个元素  比较耗时
    public int kthLargest(TreeNode root, int k) {
        List<Integer> lst = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (queue.size() != 0){
            int m = queue.size();
            while (m > 0){
                TreeNode pop = queue.poll();
                lst.add(pop.val);
                if (pop.left != null)
                    queue.offer(pop.left);
                if (pop.right != null)
                    queue.offer(pop.right);
                m --;
            }
        }
        // System.out.println(lst);
        Collections.sort(lst);
        // System.out.println(lst);
        return lst.get(lst.size() - k);
    }

    // 解法2 ： 以逆序中序遍历的方式（即右-根-左)的方式遍历该树，当遍历到第k个节点时，return
    public int k;
    public int kthLargest2(TreeNode root, int k) {
        List<Integer> lst = new LinkedList<>();
        this.k = k;
        inOrder(root, lst);
        return lst.get(lst.size() - 1);
    }

    private void inOrder(TreeNode root, List<Integer> lst){
        if (root == null)
            return;

        inOrder(root.right, lst);
        if (k == 0)
            return;
        lst.add(root.val);
        k --;
        if (k == 0)
            return;
        inOrder(root.left, lst);
    }
}
