/*
297. 二叉树的序列化与反序列化
序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。

请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。

示例:

你可以将以下二叉树：

    1
   / \
  2   3
     / \
    4   5

序列化为 "[1,2,3,null,null,4,5]"
提示: 这与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。

说明: 不要使用类的成员 / 全局 / 静态变量来存储状态，你的序列化和反序列化算法应该是无状态的。


 */
package 数据结构.Tree;
import java.util.*;

public class Q297Serialize {
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

        private boolean equals(Object o1, Object o2){
            if (o1 == null)
                return o2 == null;
            return o1.equals(o2);
        }
        @Override
        public boolean equals(Object obj) {
            if (obj == null || ! (obj instanceof Q236最近公共祖先.TreeNode))
                return false;
            Q236最近公共祖先.TreeNode that = (Q236最近公共祖先.TreeNode) obj;
            return equals(this.val, that.val)
                    && equals(this.left, that.left)
                    && equals(this.right, that.right);
        }
        @Override
        public int hashCode() {
            int result = this.val;
            if (this.left != null)
                result += this.left.hashCode();
            if (this.right != null)
                result += this.right.hashCode();
            return result;
        }
    }

     // 序列化:   先对该树进行前序遍历(根 左 右)，子树为空用null代替，得到对应的字符串序列。
     // 反序列化:  对字符串进行split操作，然后转化为linkedlist / array， 若为null，返回空子树；否则先添加左子树，再添加右子树。
     // idea:    通过递归的调用，代替了使用迭代方法的出栈入栈！！！
     StringBuffer sb;
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        sb = new StringBuffer();
        dfs(root);
        return sb.toString();
    }

    private void dfs(TreeNode root){
        if (root == null){
            sb.append("null,");
            return;
        }
        sb.append(String.valueOf(root.val) + ",");
        dfs(root.left);
        dfs(root.right);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] arr = data.split(",");
        return deseHelper(arr);
    }
    int index = 0;
    private TreeNode deseHelper(String[] arr){
        // if (index == arr.length)
        //     return null;

        String temp = arr[index ++];
        if (temp.equals("null"))
            return null;

        TreeNode root = new TreeNode(Integer.parseInt(temp));
        root.left = deseHelper(arr);
        root.right = deseHelper(arr);
        return root;
    }




    // Solution 2: BFS / Level order
    // Encodes a tree to a single string.
    public String serialize2(TreeNode root) {
        // bfs traverse the tree
        LinkedList<TreeNode> q = new LinkedList();
        String ans = "";
        q.add(root);
        TreeNode temp = null;
        while (q.size() != 0){
            temp = q.removeLast();
            if (temp == null){
                ans += "null,";
                continue;
            }
            q.addFirst(temp.left);
            q.addFirst(temp.right);
            ans += temp.val + ",";
        }
        return ans;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize2(String data) {
        String[] dataArr = data.split(",");
        LinkedList<String> dataLst = new LinkedList(Arrays.asList(dataArr));
        return buildTree(dataLst);
    }

    private TreeNode buildTree(LinkedList<String> dataLst){
        if (dataLst.getFirst().equals("null"))
            return null;

        LinkedList<TreeNode> q = new LinkedList();
        TreeNode tree = new TreeNode(Integer.parseInt(dataLst.removeFirst()));
        q.addFirst(tree);
        while (q.size() != 0){ // dataLst.size() != 0
            TreeNode temp = q.removeLast();
            String leftVal = dataLst.removeFirst();
            String rightVal = dataLst.removeFirst();
            if (!leftVal.equals("null")){
                TreeNode left = new TreeNode(Integer.parseInt(leftVal));
                temp.left = left;
                q.addFirst(left);
            }
            if (!rightVal.equals("null")){
                TreeNode right = new TreeNode(Integer.parseInt(rightVal));
                temp.right = right;
                q.addFirst(right);
            }
        }
        return tree;
    }



    public static void main(String[] args){
        Q297Serialize q = new Q297Serialize();
        TreeNode t = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        String s = q.serialize2(t);
        System.out.println(s);
        TreeNode t2 = q.deserialize2(s);

        List<List<Integer>> ans = new LinkedList<>();



//        String[] arr = new String[]{"d", "b", "a", "a"};
//        LinkedList<String> lst = new LinkedList<>(Arrays.asList(arr));
//        Stream<String> stream = lst.stream();
//        stream.distinct().forEach(System.out::print);
//        Map<Integer, Integer> map = Stream.of(1,2,3).map(x -> x + 1).collect(Collectors.toMap(x ->x, x -> x+1));
//        System.out.println(map);

    }
}
