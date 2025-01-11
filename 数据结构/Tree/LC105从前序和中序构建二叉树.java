package 数据结构.Tree;
import java.util.*;

public class LC105从前序和中序构建二叉树 {
    HashMap<Integer, Integer> map;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        map = new HashMap();
        for(int i = 0; i < inorder.length; i ++)
            map.put(inorder[i], i);
        return buildTree(preorder, inorder, 0, n - 1, 0, n - 1);
    }


    /**
     * 前序的起止点
     *   左子树 [preStart + 1, preStart + numLeft]
     *   右子树 [preStart + numLeft + 1, preStart + numLeft + numRight]
     * 中序的起止点
     *   左子树 [inStart, inStart + numLeft - 1]
     *   右子树 [indexRootInOrder + 1, indexRootInOrder + numRight]
     * @param preorder
     * @param inorder
     * @param preStart
     * @param preEnd
     * @param inStart
     * @param inEnd
     * @return
     */
    private TreeNode buildTree(int[] preorder, int[] inorder, int preStart, int preEnd, int inStart, int inEnd){
        if(preStart > preEnd || inStart > inEnd)
            return null;
        int indexRootPreorder = preStart;
        int indexRootInorder = map.get(preorder[indexRootPreorder]); // 根节点在中序遍历中的index
        int numLeft = indexRootInorder - inStart;
        int numRight = inEnd - indexRootInorder;
        TreeNode tree = new TreeNode(preorder[indexRootPreorder]);
        TreeNode left = buildTree(preorder, inorder, preStart + 1, preStart + numLeft, inStart, inStart + numLeft - 1);
        TreeNode right = buildTree(preorder, inorder, preStart + numLeft + 1, preStart + numLeft + numRight, indexRootInorder + 1, indexRootInorder + numRight);
        tree.left = left;
        tree.right = right;
        return tree;
    }
}
