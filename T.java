import com.sun.source.tree.Tree;

import java.util.*;
public class T {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
        TreeNode(int x, TreeNode left, TreeNode right){
            val = x;
            this.left = left;
            this.right = right;
        }
    }
    public List<List<Integer>> levelOrderWithList(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        List<Integer> path = new LinkedList<>();

        while (queue.size() != 0){
            int m = queue.size();
            while (m > 0){
                TreeNode pop = queue.poll();
                path.add(pop.val);
                if (pop.left != null)
                    queue.offer(pop.left);
                if (pop.right != null)
                    queue.offer(pop.right);
                m --;
            }
            ans.add(new LinkedList<>(path));
            path.clear();
        }
        return ans;
    }

    public static void main(String[] args){
        TreeNode root = new TreeNode(1, new TreeNode(2, new TreeNode(4), new TreeNode(5)), new TreeNode(3));
        T t = new T();
        List re = t.postOrder(root, new ArrayList<>());
        System.out.println(re.toString());
    }

    public List<Integer> levelOrder(TreeNode root){
        Queue<TreeNode> queue = new LinkedList<>();
        LinkedList<Integer> re = new LinkedList<>();
        queue.offer(root);

        while (queue.size() > 0){
            TreeNode poll = queue.poll();
            re.add(poll.val);
            if (poll.left != null){
                queue.offer(poll.left);
            }
            if (poll.right != null){
                queue.offer(poll.right);
            }
        }
        return re;
    }

    public List<Integer> preOrder(TreeNode root, List<Integer> lst) {
        if (root == null){
            return lst;
        }
        lst.add(root.val);
        preOrder(root.left, lst);
        preOrder(root.right, lst);
        return lst;
    }

    public List<Integer> inOrder(TreeNode root, List<Integer> lst) {
        if (root == null){
            return lst;
        }
        inOrder(root.left, lst);
        lst.add(root.val);
        inOrder(root.right, lst);
        return lst;
    }

    public List<Integer> postOrder(TreeNode root, List<Integer> lst) {
        if (root == null){
            return lst;
        }
        postOrder(root.left, lst);
        postOrder(root.right, lst);
        lst.add(root.val);
        return lst;
    }























}
