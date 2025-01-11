package 数据结构.Tree;

import java.util.*;

public class Q450删除节点 {
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

    /**
     * Solution 2
     * key > root.val 找右子树进行删除
     * key < root.val 找左子树进行删除
     * key = root.val 删除当前节点
         * 左为空 直接用右子节点代替当前节点
         * 右为空 直接用左子节点代替当前节点
         * 二者都不为空 取前驱 或 后继节点代替当前节点
     *
     * @param root
     * @return
     */


    // return the predecessor of the root ——》 左子树的最右节点
    private TreeNode findPredecessor(TreeNode root) {
        TreeNode temp = root.left;
        while (temp.right != null)
            temp = temp.right;
        return temp;
    }

    // return the successor of the root ——》 右子树的最左节点
    private TreeNode findSuccessor(TreeNode root) {
        TreeNode temp = root.right;
        while (temp.left != null)
            temp = temp.left;
        return temp;
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null)
            return null;

        if (key > root.val)
            root.right = deleteNode(root.right, key);
        else if (key < root.val)
            root.left = deleteNode(root.left, key);
        else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;
            else {
                int successorVal = findSuccessor(root).val;
                root = deleteNode(root, successorVal);
                root.val = successorVal;
            }
        }
        return root;
    }

    public static void main(String[] args) {
        Q450删除节点 q = new Q450删除节点();
        TreeNode t = new TreeNode(5, new TreeNode(3, new TreeNode(2), new TreeNode(4)),
                new TreeNode(6, null, new TreeNode(7)));
        q.deleteNode(t, 5);

        Queue<Integer> s = new LinkedList<>();

    }
}
