package 数据结构.Tree;
/*
给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。
注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
https://www.nowcoder.com/practice/9023a0c988684a53960365b889ceaf5e?tpId=13&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking&tab=answerKey
 */
public class Q剑指57二叉树的下一个节点 {

    public class TreeLinkNode {
        int val;
        TreeLinkNode left = null;
        TreeLinkNode right = null;
        TreeLinkNode next = null;

        TreeLinkNode(int val) {
            this.val = val;
        }
    }
    private TreeLinkNode getSuccessor(TreeLinkNode node){
        while (node.left != null)
            node = node.left;
        return node;
    }

    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        if (pNode.right != null){
            return getSuccessor(pNode.right);
        }
        else{
            if (pNode.next == null)
                return null;
            else if (pNode.val < pNode.next.val)
                return pNode.next;
            else{
                TreeLinkNode temp = pNode;
                while (temp.next != null && temp.val > temp.next.val){
                    temp = temp.next;
                }
                return temp.next;
            }
        }

    }
}
