package DP动态规划;

public class Q124 {
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

    public int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        dfs(root);
        return max;
    }

    private int dfs(TreeNode root){
        if (root == null)
            return 0;
        int rightGain = Math.max(dfs(root.right), 0);
        int leftGain = Math.max(dfs(root.left), 0);
        max = Math.max(max, root.val + rightGain + leftGain);
        return root.val + Math.max(leftGain, rightGain);
    }
}
