package 数据结构.Tree;

import java.util.List;

/**
 * 给定一个二叉树的根节点 root ，返回它的 中序 前序 后序遍历。
 */
 //Definition for a binary tree node.


public class TreeTraversal递归遍历 {
    public class TreeNode {
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

    private void preOrder(TreeNode tree, List<Integer> lst){
        if(tree == null)
            return;
        lst.add(tree.val);
        preOrder(tree.left, lst);
        preOrder(tree.right, lst);
    }

    private void inOrder(TreeNode tree, List<Integer> lst){
        if(tree == null)
            return;
        inOrder(tree.left, lst);
        lst.add(tree.val);
        inOrder(tree.right, lst);
    }

    private void postOrder(TreeNode tree, List<Integer> lst){
        if(tree == null)
            return;
        postOrder(tree.left, lst);
        postOrder(tree.right, lst);
        lst.add(tree.val);
    }

}
