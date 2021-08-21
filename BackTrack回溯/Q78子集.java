package BackTrack回溯;
import java.util.*;

public class Q78子集 {

    // 方法1 位运算
    // 如123 ，如果1代表选择，0代表不选，  可以看成是000 001 010 ... 111的组合。
    List<List<Integer>> ans = new LinkedList<>();
    List<Integer> path = new LinkedList<>();
    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < (1 << n); i ++){
            path.clear();
            for (int j = 0; j < n; j ++){
                int temp = 1 << j;
                if ((i & temp) != 0)
                    path.add(nums[j]);
            }
            ans.add(new LinkedList<>(path));
        }
        return ans;
    }

    // 方法2 回溯
    public List<List<Integer>> subsets2(int[] nums) {
        dfs(nums, new LinkedList<Integer>(), 0);
        return ans;
    }

    private void dfs(int[] nums, LinkedList<Integer> path, int start){
        ans.add(new LinkedList(path));
        if (start == nums.length)
            return;
        for (int i = start; i < nums.length; i ++){
            path.add(nums[i]);
            dfs(nums, path, i + 1);
            path.removeLast();
        }
    }

    // 方法3 回溯
    List<Integer> t = new ArrayList<Integer>();
    List<List<Integer>> ans2 = new ArrayList<List<Integer>>();

    public List<List<Integer>> subsets3(int[] nums) {
        dfs(0, nums);
        return ans2;
    }

    public void dfs(int cur, int[] nums) {
        if (cur == nums.length) {
            ans2.add(new ArrayList<Integer>(t));
            return;
        }
        t.add(nums[cur]);
        dfs(cur + 1, nums); // 选当前元素

        t.remove(t.size() - 1);
        dfs(cur + 1, nums); // 不选当前元素
    }



    public static void main(String[] args){
//        Q78子集 q = new Q78子集();
//        List l = q.subsets(new int[] {1,2,3});
//        System.out.println(l.toString());
        List<Integer> l1 = new LinkedList<>();
        l1.add(1);
        l1.add(2);

        List<Integer> l2 = new LinkedList<>();
        l2.add(1);
        l2.add(2);
        System.out.println(l1.equals(l2));


    }
}
