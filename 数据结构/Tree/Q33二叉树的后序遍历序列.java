package 数据结构.Tree;
/*
剑指 Offer 33. 二叉搜索树的后序遍历序列
输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。

参考以下这颗二叉搜索树：

     5
    / \
   2   6
  / \
 1   3
示例 1：

输入: [1,6,3,2,5]
输出: false
示例 2：

输入: [1,3,2,6,5]
输出: true
https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/
 */
public class Q33二叉树的后序遍历序列 {


    // 左 右  根 后序遍历一定是  先小于根  再大于根  如  1 3 2 ，   6  ，  5
    public boolean verifyPostorder(int[] postorder) {
        return verifyPostorderHelper(postorder, 0, postorder.length - 1);
    }

    private boolean verifyPostorderHelper(int[] postorder, int start, int end){
        if (start >= end)
            return true;

        int middle = end;
        for (int i = start; i < end; i ++){
            if (postorder[i] > postorder[end]){
                middle = i;
                break;
            }
        }

        for (int j = middle; j < end; j ++){
            if (postorder[j] < postorder[end])
                return false;
        }

        return verifyPostorderHelper(postorder, start, middle - 1) &&
                verifyPostorderHelper(postorder, middle, end - 1);
    }

    public static void main(String[] args){
        Q33二叉树的后序遍历序列 q = new Q33二叉树的后序遍历序列();
        boolean r = q.verifyPostorder(new int[] {4,6,7,5});
        System.out.println(r);
    }
}
