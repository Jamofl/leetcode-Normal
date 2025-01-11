package 数据结构.Tree.HuffumanCoding;

import java.util.*;

public class Q1382二叉树变二叉搜索树 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

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

    // 中序遍历
    List<Integer> inorderList = new ArrayList<Integer>();

    public TreeNode Q1382二叉树变二叉搜索树(TreeNode root) {
        getInorder(root);
        return build(0, inorderList.size() - 1);
    }

    public void getInorder(TreeNode node) {
        if (node == null) {
            return;
        }
        getInorder(node.left);
        inorderList.add(node.val);
        getInorder(node.right);

    }

    public TreeNode build(int l, int r) {
        if (l > r) {
            return null;
        }
        int mid = (l + r) / 2;
//        int mid = (l + r) >> 1;  // n右移一位 等价于 n/2向下取整
        TreeNode node = new TreeNode(inorderList.get(mid));
        node.left = build(l, mid - 1);
        node.right = build(mid + 1, r);
        return node;
    }
}
