import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 给你 root1 和 root2 这两棵二叉搜索树。
 *
 * 请你返回一个列表，其中包含 两棵树 中的所有整数并按 升序 排序。
 *
 * eg
 * 输入：root1 = [2,1,4], root2 = [1,0,3]
 * 输出：[0,1,1,2,3,4]
 */

public class Q1305 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    // Solution 1: 无脑dfs遍历两个树，然后进行排序，时间复杂度为 O(n logn) （n为两个树的长度之和）
    private void dfs(TreeNode tree, List lst){
        if (tree == null)
            return;
        lst.add(tree.val);
        dfs(tree.left, lst);
        dfs(tree.right, lst);
    }

    // Solution 2: inorder 中序遍历两个树，则两个树获得的列表均为有序列表，然后采用归并排序的思想合并两个列表，复杂度为 O(N)
    private void inorderDFS(TreeNode tree, List lst){
        if (tree == null)
            return;
        inorderDFS(tree.left, lst);
        lst.add(tree.val);
        inorderDFS(tree.right, lst);
    }


    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> lst1 = new LinkedList<>();
        List<Integer> lst2 = new LinkedList<>();
        List<Integer> ans = new LinkedList<>();
        inorderDFS(root1, lst1);
        inorderDFS(root2, lst2);
        while(true){
            if (lst1.isEmpty() && !lst2.isEmpty())
                ans.add(lst2.remove(0));
            else if (lst2.isEmpty() && !lst1.isEmpty())
                ans.add(lst1.remove(0));
            else if (!lst1.isEmpty() && !lst2.isEmpty()){
                if (lst1.get(0) <= lst2.get(0))
                    ans.add(lst1.remove(0));
                else
                    ans.add(lst2.remove(0));
            }
            else
                break;
        }
        return ans;
    }

}
