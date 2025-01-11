import java.text.DateFormatSymbols;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Test {
    public class TreeNode {
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



    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null){
            return new ArrayList<>();
        }
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> re = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        queue.offer(root);
        while (queue.size() != 0){
            int m = queue.size();

            while (m > 0){
                TreeNode pop = queue.poll();
                path.add(pop.val);
                if (pop.left != null){
                    queue.add(pop.left);
                }
                if (pop.right != null){
                    queue.add(pop.right);
                }
                m --;
            }
            re.add(new ArrayList(path));
            path.clear();
        }
        return re;

    }


    public static void main(String[] args) {


    }

}
