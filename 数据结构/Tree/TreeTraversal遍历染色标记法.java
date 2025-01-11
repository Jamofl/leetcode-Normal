package 数据结构.Tree;

import com.sun.source.tree.Tree;

import java.util.*;


/**
 * 记录每个节点的访问状态, false未访问, true访问过
 * 如果当前节点未访问,则标记为已访问,然后依次入栈右、中、左(以中序遍历举例)
 * 如果当前节点已访问,则加入到结果序列中
 * e.g. 中序遍历 遍历顺序为左中右,由于栈是FIFO,所以入栈顺序为 右中左,这样出栈时刚好可以还原
 */
public class TreeTraversal遍历染色标记法 {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
            this.right = null;
            this.left = null;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    // 中序遍历, 左-根-右, 对应入栈顺序右-根-左
    public List<Integer> inOrder(TreeNode root) {
        if (root == null){
            return new LinkedList<>();
        }
        HashMap<TreeNode, Boolean> visitMap = new HashMap<>();
        Stack<TreeNode> stack = new Stack<>();
        LinkedList<Integer> ans = new LinkedList<>();
        TreeNode top = null;
        stack.push(root);
        while (stack.size() != 0) {
            top = stack.pop();
            if (!visitMap.getOrDefault(top, false)){
                visitMap.put(top, true);
                if (top.right != null){
                    stack.push(top.right);
                }
                stack.push(top);
                if (top.left != null){
                    stack.push(top.left);
                }
            }
            else {
                ans.add(top.val);
            }
        }
        return ans;
    }

    // 前序遍历, 根-左-右 对应入栈顺序右-左-根
    public List<Integer> preOrder(TreeNode root) {
        if (root == null){
            return new LinkedList<>();
        }
        HashMap<TreeNode, Boolean> visitMap = new HashMap<>();
        Stack<TreeNode> stack = new Stack<>();
        LinkedList<Integer> ans = new LinkedList<>();
        TreeNode top = null;
        stack.push(root);
        while (stack.size() != 0) {
            top = stack.pop();
            if (!visitMap.getOrDefault(top, false)){
                visitMap.put(top, true);
                if (top.right != null){
                    stack.push(top.right);
                }
                if (top.left != null){
                    stack.push(top.left);
                }
                stack.push(top);
            }
            else {
                ans.add(top.val);
            }
        }
        return ans;
    }


    // 后序遍历  右-左-根  对应入栈顺序根-左-右
    public List<Integer> postOrder(TreeNode root) {
        if (root == null){
            return new LinkedList<>();
        }
        HashMap<TreeNode, Boolean> visitMap = new HashMap<>();
        Stack<TreeNode> stack = new Stack<>();
        LinkedList<Integer> ans = new LinkedList<>();
        TreeNode top = null;
        stack.push(root);
        while (stack.size() != 0) {
            top = stack.pop();
            if (!visitMap.getOrDefault(top, false)){
                visitMap.put(top, true);
                stack.push(top);
                if (top.right != null){
                    stack.push(top.right);
                }
                if (top.left != null){
                    stack.push(top.left);
                }
            }
            else {
                ans.add(top.val);
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        TreeTraversal遍历染色标记法 treetraverse = new TreeTraversal遍历染色标记法();
        System.out.println();
    }


}
