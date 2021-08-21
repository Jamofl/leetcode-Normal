package 数据结构.Tree;

/**
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
 */

import java.util.*;

public class Q102树的层序遍历 {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        TreeNode(int x, TreeNode left, TreeNode right) {
            val = x;
            this.left = left;
            this.right = right;
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null)
            return new LinkedList<>();
        List<List<Integer>> ans = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        List<Integer> path = new LinkedList<>();

        while (queue.size() != 0){
            int m = queue.size();
            while (m > 0){
                TreeNode pop = queue.poll();
                path.add(pop.val);
                if (pop.left != null)
                    queue.offer(pop.left);
                if (pop.right != null)
                    queue.offer(pop.right);
                m --;
            }
            ans.add(new LinkedList<>(path));
            path.clear();
        }
        return ans;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        Q102树的层序遍历 t = new Q102树的层序遍历();
        List re = t.levelOrder(root);
        System.out.println(re.toString());
    }
}
