package BackTrack回溯;
import java.util.*;

public class Q78子集 {

    // 方法1 位运算
    // 如123 ，如果1代表选择，0代表不选，  可以看成是000 001 010 ... 111的组合。
    List<List<Integer>> ans = new LinkedList<>();
    List<Integer> path = new LinkedList<>();
    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < (1 << n); i ++){ // 遍历0-8  即000 001 010 011... 总共9种情况
            path.clear();
            for (int j = 0; j < n; j ++){ //  遍历数字i的第j位 j[0,2]  哪位有1 则取nums[j]加到path中
                int temp = 1 << j;
                if ((i & temp) != 0)
                    path.add(nums[j]);
            }
            ans.add(new LinkedList<>(path));
        }
        return ans;
    }

    // 方法2 回溯 看不懂。。。
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

    /**
     * 方法3 回溯 推荐
     * 对每一个元素 都有选或不选两种选择
     */


    public List<List<Integer>> subsets3(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(nums,0, ans, new LinkedList<>());
        return ans;
    }

    public void dfs(int[] nums, int index, List<List<Integer>> ans, LinkedList<Integer> path){
        if (index == nums.length){
            ans.add(new ArrayList<>(path));
            return;
        }

        dfs(nums, index + 1, ans, path);
        path.addLast(nums[index]);
        dfs(nums, index + 1, ans, path);
        path.removeLast();
    }

    /**
     * 方法4 动态规划 推荐
     * https://leetcode.cn/problems/subsets/solutions/420294/zi-ji-by-leetcode-solution/?envType=study-plan-v2&envId=top-100-liked
     * 可以这么表示，dp[i]表示前i个数的解集，dp[i] = dp[i - 1] + collections(i)。其中，collections(i)表示把dp[i-1]的所有子集都加上第i个数形成的子集。
     * 【具体操作】
     * 因为nums大小不为0，故解集中一定有空集。令解集一开始只有空集，然后遍历nums，每遍历一个数字，拷贝解集中的所有子集，将该数字与这些拷贝组成新的子集再放入解集中即可。时间复杂度为O(n^2)。
     * 例如[1,2,3]，一开始解集为[[]]，表示只有一个空集。
     * 遍历到1时，依次拷贝解集中所有子集，只有[]，把1加入拷贝的子集中得到[1]，然后加回解集中。此时解集为[[], [1]]。
     * 遍历到2时，依次拷贝解集中所有子集，有[], [1]，把2加入拷贝的子集得到[2], [1, 2]，然后加回解集中。此时解集为[[], [1], [2], [1, 2]]。
     * 遍历到3时，依次拷贝解集中所有子集，有[], [1], [2], [1, 2]，把3加入拷贝的子集得到[3], [1, 3], [2, 3], [1, 2, 3]，然后加回解集中。此时解集为[[], [1], [2], [1, 2], [3], [1, 3], [2, 3], [1, 2, 3]]。
     */

    public List<List<Integer>> subsets4(int[] nums) {
        int n = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<>());
        for (int num : nums){
            int len = ans.size();
            for (int i = 0; i < len; i++) {
                ArrayList<Integer> lst = new ArrayList<>(ans.get(i));
                lst.add(num);
                ans.add(lst);
            }
        }
        return ans;
    }


    public static void main(String[] args){
        Q78子集 q = new Q78子集();
        List l = q.subsets(new int[] {1,2,3});
        System.out.println(l.toString());



    }
}
