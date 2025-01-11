/*
236. 二叉树的最近公共祖先
https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/
给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。

百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”

例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]





示例 1:

输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
输出: 3
解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
示例 2:

输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
输出: 5
解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。


说明:

所有节点的值都是唯一的。
p、q 为不同节点且均存在于给定的二叉树中。
 */
package 数据结构.Tree;

import java.util.*;

public class Q236最近公共祖先 {
    public static class TreeNode {
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

        private int height(TreeNode root) {
            if (root == null)
                return 0;
            if (root.left == null && root.right == null)
                return 1;
            return 1 + Math.max(height(root.left), height(root.right));
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
        }

        @Override
        public int hashCode() {
            int result = this.val;
            if (this.left != null)
                result += this.left.hashCode();
            if (this.right != null)
                result += this.right.hashCode();
            return result;
        }
    }


    // Solution 1: 分别找到两个节点的 path-to-root, 然后找这两个path的第一个交点，即为最低公共祖先。
    // 复杂度: O(N + logN ）
    public TreeNode ROOT;

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        this.ROOT = root;
        LinkedList<TreeNode> path1 = new LinkedList();
        LinkedList<TreeNode> path2 = new LinkedList();
        findNode(path1, root, p);
        findNode(path2, root, q);
        //System.out.println(path1);
        //System.out.println(path2);

        for (TreeNode n : path1) {
            if (path2.contains(n))
                return n;
        }
        return ROOT;
    }

    /**
     * 找到目标节点target 并返回到当前节点的路径path
     *
     * @param path
     * @param root
     * @param target
     * @return
     */
    private boolean findNode(LinkedList<TreeNode> path, TreeNode root, TreeNode target) {
        if (root == null)
            return false;
        else if (target.equals(root)) {
            path.add(root);
            return true;
        } else if (findNode(path, root.left, target) || findNode(path, root.right, target)) {
            path.add(root);
            return true; // return true很关键！
        }
        return false;
    }


    // Solution 2: O(n)
    // 先对整个树进行dfs，找到每个节点的父节点，建立一个映射。然后先找p的path to root， 再找q的path to root， 找公共交点。
    private static HashMap<TreeNode, TreeNode> map = new HashMap();

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        getParentMap(root);
        Set<Integer> path1 = new HashSet();
        while (p != null) {
            path1.add(p.val);
            p = map.get(p);
        }

        while (q != null) {
            if (path1.contains(q.val))
                return q;
            q = map.get(q);
        }
        return null;
    }

    private void getParentMap(TreeNode root) {
        if (root.left != null) {
            map.put(root.left, root);
            getParentMap(root.left);
        }

        if (root.right != null) {
            map.put(root.right, root);
            getParentMap(root.right);
        }
    }

    // 方法3 递归法
    // 假设leftPQ 表示左子树中含有P或Q;  rightPQ表示右子树中含有P或Q
    // 则当前节点为lca的条件等价于 =>
    //      ((leftPQ && rightPQ) || ((root.val == p.val || root.val == q.val) && (leftPQ || rightPQ)))
    // 即 左子树和右子树都含有pq  或者   当前节点为pq 且 左子树或右子树含有pq
    TreeNode lca;

    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        this.lca = null;
        dfs(root, p, q);
        return this.lca;

    }

    /**
     * 状态一定要定义对！
     * 假设leftPQ/rightPQ 表示左子树或者右子树中含有P或Q
     *
     * @param root
     * @param p
     * @param q
     * @return 返回值代表当前root节点中是否含有P或Q
     */
    private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return false;

        boolean leftPQ = dfs(root.left, p, q); // 表示左子树中含有pq
        boolean rightPQ = dfs(root.right, p, q);// 表示右子树中含有pq
        if ((leftPQ && rightPQ) ||
                ((root.val == p.val || root.val == q.val) && (leftPQ || rightPQ))) {
            lca = root;
        }

        // root及其子树含有PQ等价于: 如果左子树含有pq  或  右子树含有pq   或   根节点就是pq 则返回true
        return leftPQ || rightPQ || (root.val == q.val) || (root.val == p.val);
    }


    public static void main(String[] args) {
        Q236最近公共祖先 q = new Q236最近公共祖先();
        TreeNode t = new TreeNode(3, new TreeNode(1, new TreeNode(2), new TreeNode(5)), new TreeNode(4));
        TreeNode re = q.lowestCommonAncestor(t, new TreeNode(1, new TreeNode(2), new TreeNode(5)), new TreeNode(5));
        System.out.println(re.val);
    }
}
