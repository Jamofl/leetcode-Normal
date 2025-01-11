package 数据结构.Tree;

import java.util.*;


/**
 * 这个解法做复杂了 不推荐 看看实现就行
 * 假设target是要删除的目标节点 分类讨论了3种情况
 *     叶子节点        直接删除当前节点
 *     有一个子节点     让target的父节点指向叶子节点
 *     有两个子节点     找到target的前驱/后继节点,将其删除然后替代target
 *
 */
public class Q450删除节点复杂版 {
    private static class TreeNode {
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

        private boolean equals(Object o1, Object o2) {
            if (o1 == null)
                return o2 == null;
            return o1.equals(o2);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof TreeNode))
                return false;
            TreeNode that = (TreeNode) obj;
            return equals(this.val, that.val)
                    && equals(this.left, that.left)
                    && equals(this.right, that.right);
//          if (this.right == null && this.left == null && that.right == null && that.left == null)
//              return this.val == that.val;
//          else
//              return (this.val == that.val) && (this.left.equals(that.left)) && (this.right.equals(that.right));
        }
    }

    private HashMap<Integer, TreeNode> map = new HashMap();

    // dfs traverse the tree and update the (parent of every node) map
    private void dfs(TreeNode root) {
        if (root.left != null) {
            map.put(root.left.val, root);
            dfs(root.left);
        }
        if (root.right != null) { // if !!!, not else if,因为每次都是：既要检查左子树，又要检查右子树
            map.put(root.right.val, root);
            dfs(root.right);
        }
    }

    // find the node with key value KEY in the tree ROOT, and return that node; if doesn't exist, return null
    private TreeNode find(TreeNode root, int key) {
        if (root == null)
            return null;
        if (root.val == key) {
            return root;
        }
        TreeNode leftResult = find(root.left, key);
        TreeNode rightResult = find(root.right, key);
        if (leftResult == null && rightResult == null)
            return null;
        return (rightResult == null) ? leftResult : rightResult;
    }

    // return the predecessor of the root
    private TreeNode findPredecessor(TreeNode root) {
        TreeNode temp = root.left;
        while (temp.right != null) {
            temp = temp.right;
        }
        return temp;
    }

    private TreeNode delete(TreeNode root, int key) {
        TreeNode keyNode = find(root, key);
        if (find(root, key) == null) // key doesn't exist
            return root;
        TreeNode parent = map.get(keyNode.val);
        if (parent == keyNode) { // if parent is the keyNode itself, i.e. it is the root node
            if (root.left == null && root.right == null)
                return null;
            else if (root.left != null && root.right == null) {
                root = root.left;
                return root;
            } else if (root.left == null && root.right != null) {
                root = root.right;
                return root;
            }
            // root.left != null && root.right != null, can be classified to normal case 3 below
        }

        if (keyNode.left == null && keyNode.right == null) { // case 1: keyNode is the leaf node
            if (parent.left == keyNode)
                parent.left = null;
            else
                parent.right = null;
        } else if (keyNode.left == null) { // case 2.1: not leaf node, but left child is null
            if (parent.left == keyNode)
                parent.left = keyNode.right;
            else
                parent.right = keyNode.right;
        } else if (keyNode.right == null) { // case 2.2: not leaf node, but right child is null
            if (parent.left == keyNode)
                parent.left = keyNode.left;
            else
                parent.right = keyNode.left;
        } else { // case 3: right child not null and left child not null
            TreeNode predecessor = findPredecessor(keyNode);
            delete(root, predecessor.val);
            keyNode.val = predecessor.val;
        }
        return root;
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        dfs(root);
        map.put(root.val, root);
        return delete(root, key);
    }
}
