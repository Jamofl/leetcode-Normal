/**
 * 337. 打家劫舍 III
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。
 * 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
 * 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 *
 * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 *
 * 示例 1:
 *
 * 输入: [3,2,3,null,3,null,1]
 *
 *      3
 *     / \
 *    2   3
 *     \   \
 *      3   1
 *
 * 输出: 7
 * 解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
 * 示例 2:
 *
 * 输入: [3,4,5,1,3,null,1]
 *
 *      3
 *     / \
 *    4   5
 *   / \   \
 *  1   3   1
 *
 * 输出: 9
 * 解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
 */
package DP动态规划;


import java.util.HashMap;
import java.util.Map;

public class Q337 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }



    // up - bottom, 每个节点的最大值为： Max(选择此节点， 不选择此节点)；
    // 选择此节点 = 不选左 + 不选右； 不选此节点 = 左变最大 + 右边最大；
    /*
    public int rob(TreeNode root) {
        return Math.max(robThis(root, false), robThis(root, true));
    }

    public int robThis(TreeNode root, boolean flag){
        if(root == null)
            return 0;
        else if(root.right == null && root.left == null){ // leaf node
            if (!flag)
                return 0;
            else
                return root.val;

        }
        else{
            if (flag){
                return root.val + robThis(root.left, false) + robThis(root.right, false);
            }
            else{
                return Math.max(robThis(root.left, false), robThis(root.left, true)) +
                        Math.max(robThis(root.right, false), robThis(root.right, true));
            }
        }
    }
     */



    Map<TreeNode, Integer> map1 = new HashMap<>(); // include this node
    Map<TreeNode, Integer> map2 = new HashMap<>(); // not include this node
    private void dfs(TreeNode root){
        if(root == null)
            return ;
        if(root.left == null && root.right == null){
            map1.put(root, root.val);
            map2.put(root, 0);
        }
        dfs(root.left);
        dfs(root.right);
        int value1 = root.val + map2.getOrDefault(root.left, 0) + map2.getOrDefault(root.right, 0);
        int value2 = Math.max(map1.getOrDefault(root.left, 0), map2.getOrDefault(root.left, 0)) +
                Math.max(map1.getOrDefault(root.right, 0), map2.getOrDefault(root.right, 0));
        map1.put(root, value1);
        map2.put(root, value2);
    }

    public int rob(TreeNode root) {
        dfs(root);
        return Math.max(map1.getOrDefault(root, 0), map2.getOrDefault(root, 0));
    }

    }
