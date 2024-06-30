package 数据结构.Tree;

import java.util.*;

/**
 * https://leetcode.cn/problems/binary-tree-postorder-traversal/submissions/502853951/
 *  * 访问标记法
 *  *      // 若未访问过 则标记为已访问，且依次入栈 自己、右子节点、左子节点（因为栈的顺序和遍历的顺序是反的 中序遍历的顺序是 左 右 中）
 *  *      // 若访问过   则直接打印当前值
 */
public class Q二叉树的后序遍历 {
    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null){
            return new LinkedList<>();
        }
        Map<TreeNode, Boolean> map =  new HashMap<>();
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> re = new LinkedList<>();
        map.put(root, false);
        stack.push(root);
        while (stack.size() > 0){
            TreeNode temp = stack.pop();
            if (!map.getOrDefault(temp, false)){
                map.put(temp, true);
                stack.push(temp);
                if (temp.right != null){
                    stack.push(temp.right);
                }
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
}
