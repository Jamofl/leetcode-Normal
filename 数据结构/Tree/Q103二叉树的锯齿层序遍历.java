package 数据结构.Tree;

/**
 * https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/
 */
import java.util.*;
public class Q103二叉树的锯齿层序遍历 {
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

    /**
     *  方法1 将队列换成双端队列 比较复杂 【不推荐】
     *  对于从左往右的遍历 尾插头取
     *  对于从右往左的遍历 尾取头插
     */
    /*
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null)
            return new 数据结构.LinkedList<>();
        List<List<Integer>> ans = new ArrayList<>();
        Deque<TreeNode> deque = new 数据结构.LinkedList<>();
        deque.offer(root);
        数据结构.LinkedList<Integer> path = new 数据结构.LinkedList<>();
        boolean fromLeft = true;

        while (deque.size() != 0){
            int m = deque.size();
            if (fromLeft){   //从左到右  尾插
                while (m > 0){
                    TreeNode pop = deque.removeFirst();
                    path.add(pop.val);
                    if (pop.left != null)
                        deque.addLast(pop.left);
                    if (pop.right != null)
                        deque.addLast(pop.right);
                    m --;
                }
            }
            else {          //从右到左  头插
                while (m > 0){
                    TreeNode pop = deque.removeLast();
                    path.add(pop.val);
                    if (pop.right!= null)
                        deque.addFirst(pop.right);
                    if (pop.left != null)
                        deque.addFirst(pop.left);
                    m --;
                }
            }

            fromLeft = !fromLeft;
            ans.add(new 数据结构.LinkedList<>(path));
            path.clear();
        }
        return ans;
    }
     */

    /**
     *  方法2 将path换成双端队列 简介 【推荐】
     *  对于从左往右的遍历 尾插
     *  对于从右往左的遍历 头插
     */
    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        LinkedList<Integer> path = new LinkedList<>();
        boolean fromLeft = true;

        while (queue.size() != 0) {
            int m = queue.size();
            while (m > 0) {
                TreeNode pop = queue.poll();
                if (fromLeft)
                    path.addLast(pop.val);
                else
                    path.addFirst(pop.val);

                if (pop.left != null)
                    queue.offer(pop.left);
                if (pop.right != null)
                    queue.offer(pop.right);
                m--;
            }
            fromLeft = !fromLeft;
            ans.add(new LinkedList<>(path));
            path.clear();
        }
        return ans;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, new TreeNode(2, new TreeNode(4), new TreeNode(5)), new TreeNode(3, new TreeNode(6), new TreeNode(7)));
        Q103二叉树的锯齿层序遍历 t = new Q103二叉树的锯齿层序遍历();
        List re = t.zigzagLevelOrder2(root);
        System.out.println(re.toString());
    }
}
