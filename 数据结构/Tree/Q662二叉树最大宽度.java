package 数据结构.Tree;
import javax.swing.plaf.IconUIResource;
import java.util.*;
/*
662. 二叉树最大宽度
给定一个二叉树，编写一个函数来获取这个树的最大宽度。树的宽度是所有层中的最大宽度。这个二叉树与满二叉树（full binary tree）结构相同，但一些节点为空。

每一层的宽度被定义为两个端点（该层最左和最右的非空节点，两端点间的null节点也计入长度）之间的长度。

示例 1:

输入:

           1
         /   \
        3     2
       / \     \
      5   3     9

输出: 4
解释: 最大值出现在树的第 3 层，宽度为 4 (5,3,null,9)。
示例 2:

输入:

          1
         /
        3
       / \
      5   3

输出: 2
解释: 最大值出现在树的第 3 层，宽度为 2 (5,3)。
示例 3:

输入:

          1
         / \
        3   2
       /
      5

输出: 2
解释: 最大值出现在树的第 2 层，宽度为 2 (3,2)。
示例 4:

输入:

          1
         / \
        3   2
       /     \
      5       9
     /         \
    6           7
输出: 8
解释: 最大值出现在树的第 4 层，宽度为 8 (6,null,null,null,null,null,null,7)。
注意: 答案在32位有符号整数的表示范围内。
 */
public class Q662二叉树最大宽度 {
    public static class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode() {}
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
    }


    // 新建一个node类，目的就是保存node所在的position下标
    // 在每一层，最大宽度就是该层最右边的元素的id - 最左边元素的id
    public static class AnnotatedNode {
        TreeNode node;
        int pos;

        AnnotatedNode(TreeNode node, int pos) {
            this.node = node;
            this.pos = pos;
        }
    }


    public int widthOfBinaryTree(TreeNode root) {
        if (root == null)
            return 0;

        return getWidth(root);
    }



    private int getWidth(TreeNode root){
        int width = 1;
        Deque<AnnotatedNode> q = new LinkedList<>();
        q.offer(new AnnotatedNode(root, 1));

        while (q.size() != 0) {
            int n = q.size();

            width = Math.max(width, q.getLast().pos - q.getFirst().pos  + 1); // 宽度就是队尾元素的下标 - 队首元素下标 + 1
            while (n > 0){
                AnnotatedNode pop = q.poll();
                if (pop.node.left != null)
                    q.offer(new AnnotatedNode(pop.node.left, pop.pos * 2));
                if (pop.node.right !=null)
                    q.offer(new AnnotatedNode(pop.node.right, pop.pos * 2 + 1));
                n --;
            }
        }
        return width;
    }



    public static void main(String[] args){
        Q662二叉树最大宽度 q = new Q662二叉树最大宽度();
        TreeNode t = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        int r = q.getWidth(t);
        System.out.println(r);
    }
}
