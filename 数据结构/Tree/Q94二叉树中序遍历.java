package 数据结构.Tree;

import java.util.*;

/**
 * https://leetcode.cn/problems/binary-tree-inorder-traversal/
 * 访问标记法
 *      // 若未访问过 则标记为已访问，且依次入栈 右子节点、自己、左子节点（因为栈的顺序和遍历的顺序是反的 中序遍历的顺序是 左 中 右）
 *      // 若访问过   则直接打印当前值
 *
 */
public class Q94二叉树中序遍历 {
    public List<Integer> inorderTraversal(TreeNode root) {
        Map<TreeNode, Boolean> map =  new HashMap<>();
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> re = new LinkedList<>();
        map.put(root, false);
        stack.push(root);
        while (stack.size() > 0){
            TreeNode temp = stack.pop();
            if (!map.getOrDefault(temp, false)){
                map.put(temp, true);
                if (temp.right != null){
                    stack.push(temp.right);
                }
                stack.push(temp);
                if (temp.left != null){
                    stack.push(temp.left);
                }
            }
            else{
                re.add(temp.val);
            }
        }
        return re;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1, new TreeNode(2, new TreeNode(4), new TreeNode(5)), new TreeNode(3));
        Q94二叉树中序遍历 q94二叉树中序遍历 = new Q94二叉树中序遍历();
        List<Integer> integers = q94二叉树中序遍历.inorderTraversal(treeNode);
        System.out.println(integers);
    }
}
