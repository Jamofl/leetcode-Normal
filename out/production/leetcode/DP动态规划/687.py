"""
687. 最长同值路径
给定一个二叉树，找到最长的路径，这个路径中的每个节点具有相同值。 这条路径可以经过也可以不经过根节点。

注意：两个节点之间的路径长度由它们之间的边数表示。

示例 1:

输入:

              5
             / \
            4   5
           / \   \
          1   1   5
输出:

2
示例 2:

输入:

              1
             / \
            4   5
           / \   \
          4   4   5
输出:

2
注意: 给定的二叉树不超过10000个结点。 树的高度不超过1000。

"""


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:
    def longestUnivaluePath(self, root: TreeNode) -> int:
        result = 0

        def traverse(root):
            nonlocal result
            if root == None:
                return 0
            # elif root.right == None and root.left == None:
            #     return 1

            traveseLeft = traverse(root.left)
            traveseRight = traverse(root.right)
            traveseLeft = 0 if (not root.left or root.val != root.left.val) else traveseLeft
            traveseRight = 0 if (not root.right or root.val != root.right.val) else traveseRight

            if 1 + traveseLeft + traveseRight > result:
                result = 1 + traveseLeft + traveseRight

            return 1 + max(traveseRight, traveseLeft)

        traverse(root)
        return 0 if result == 0 else result - 1


t = TreeNode(1, TreeNode(4, TreeNode(4, None), TreeNode(4, None)), TreeNode(5, None))
s = Solution()
r = s.longestUnivaluePath(t)
print(r)