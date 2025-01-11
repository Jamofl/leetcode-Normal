package 数据结构.Tree;

import java.util.*;

public class TreeTraversal遍历迭代法 {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
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

    public List<Integer> levelOrder(TreeNode root){
        LinkedList<Integer> ans = new LinkedList<>();
        Queue<TreeNode> queue = new  LinkedList<>();
        queue.offer(root);
        while (queue.size() != 0){
            TreeNode temp = queue.poll();
            if (temp.left != null)
                queue.offer(temp.left);
            if (temp.right != null)
                queue.offer(temp.right);
            ans.addLast(temp.val);
        }
        return ans;
    }

    // 中序遍历, 左-根-右
    // dfs(left),push-> dfs(left),push -> root.equals(null), return up level, pop -> print, dfs(right) ...
    //                1.首先先将自己压入栈底，然后不断将左子节点压入栈中，直到左子节点为空。
    //               2.然后弹出栈顶元素，也就是左子树为空的节点，将该节点的值添加到结果中；
    //               3.最后将当前节点赋值为该节点的右子节点，然后重复外层循环；
    public List<Integer> inOrder (TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> ans = new LinkedList<>();
        while(!stack.isEmpty() || root != null){
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            TreeNode temp = stack.pop();
            ans.add(temp.val);
            root = temp.right;
        }
        return ans;
    }

    // 前序遍历, 根-左-右
    // print, push, dfs(left) -> print, push, dfs(left) -> null, return up, pop ->  dfs(right)...
    public List<Integer> preOrder (TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> ans = new LinkedList<>();

        while(!stack.isEmpty() || root != null){
            while(root != null){
                ans.add(root.val);
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            root = root.right;
        }
        return ans;
    }


    // 后序遍历  右-左-根  困难版本
    // too hard to remember and implement, but is the one most close to original call stack
    // push, dfs(left) -> push, dfs(left) -> null, pop() -> root.right != null -> push,  ->  dfs(right) : dfs(left) ...null ...pop() -> dfs(right)
    //                                                   -> root.right == null -> print, pop(), dfs(right) ...
    public List<Integer> postOrder (TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> ans = new LinkedList<>();
        TreeNode pre = null;
        while(root != null || !stack.isEmpty()){
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if(root.right == null || root.right == pre){
                ans.add(root.val);
                pre = root;
                root = null;
                //root = stack.pop();
            }
            else{ // root.right != null
                stack.push(root);
                root = root.right;
            }
        }
        return ans;
    }

    // 后序遍历 好记版本
    // 后序遍历的巧妙迭代: 利用前序遍历的思想，改变链表插入位置和遍历顺序，将 根左右 -> 右左根 -> 左右根
    // 前序遍历是 根-左-右， 将链表的插入顺序改为前插，则变为右-左-根；将左右子树的遍历顺序改为先右后左，则变为左-右-根，得到后序遍历
    public List<Integer> postOrderReversedpreOrder (TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        LinkedList<Integer> ans = new LinkedList<>();
        while (!stack.isEmpty() || root != null){
            while (root != null){
                ans.addFirst(root.val);
                stack.push(root);
                root = root.right;
            }
            root = stack.pop();
            root = root.left;
        }
        return ans;
    }

    public static void main(String[] args){
        TreeTraversal遍历迭代法 treetraverse = new TreeTraversal遍历迭代法();
        List<Integer> ans = treetraverse.levelOrder(new TreeNode(3, new TreeNode(1, new TreeNode(2), new TreeNode(5)), new TreeNode(4)));
        System.out.println(ans);
    }


}
