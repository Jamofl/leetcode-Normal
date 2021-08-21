/*
230. 二叉搜索树中第K小的元素
给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。

说明：
你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。

示例 1:

输入: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
输出: 1
示例 2:

输入: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
输出: 3
 */
package 数据结构.Tree;

import java.util.*;
public class Q230kthSmallest {

     // Definition for a binary tree node.
     public static class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode() {}
          TreeNode(int val) { this.val = val; }
          TreeNode(int val, TreeNode left, TreeNode right) {
             this.val = val;
              this.left = left;
              this.right = right;
          }
     }
    public int k ;
    public int kthSmallest(TreeNode root, int k) {
        this.k = k;
        LinkedList<Integer> lst = new LinkedList();
        inOrder(lst, root);
        return lst.getLast();
    }

    private void inOrder(LinkedList<Integer> lst, TreeNode root){ // BST中序遍历可以得到递增的顺序
        if (root == null)
            return;
        inOrder(lst, root.left);

        if (k == 0)  //剪枝
            return;
        lst.add(root.val);
        k --;

        inOrder(lst, root.right);
    }

    public static void main(String[] args){
        Q230kthSmallest q = new Q230kthSmallest();
        TreeNode t = new TreeNode(3, new TreeNode(1), new TreeNode(4));
        int r = q.kthSmallest(t, 1);
        System.out.println(r);


    }

}
