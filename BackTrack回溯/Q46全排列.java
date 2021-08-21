/*
 46. 全排列
给定一个 没有重复 数字的序列，返回其所有可能的全排列。

示例:

输入: [1,2,3]
输出:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
 */
package BackTrack回溯;
import java.util.*;

public class Q46全排列 {
    /*
    // Solution 1: 使用交换的方法，一次交换第一个元素和第二个、第三个...元素，dfs后回退，还原交换位置。 当交换的下标到达数组长度时，将数组加入答案中
    private void swap(Integer[] nums, int i, int j) {
        if (i == j)
            return;
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new LinkedList<>();
        Integer[] numsInteger = new Integer[nums.length];
        for (int i = 0; i < nums.length; i ++)
            numsInteger[i] = nums[i];
        dfs(numsInteger, ans, 0);
        return ans;
    }

    private void dfs(Integer[] nums, List<List<Integer>>ans, int index){
        if (index == nums.length){
            ans.add(new LinkedList(Arrays.asList(nums)));
            return;
        }
        for(int j = index; j < nums.length; j ++){
            swap(nums, index, j);
            dfs(nums, ans, index + 1);
            swap(nums, index ,j);
        }
    }
     */
    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        int n = nums.length;
        LinkedList<Integer> path = new LinkedList<>();
        boolean[] visit = new boolean[n];
        dfs(nums, visit, path, n);
        return ans;
    }

    private void dfs(int[] nums, boolean[] visit, LinkedList<Integer> path, int n){
        if (path.size() == n){
            ans.add(new LinkedList<>(path));
            return;
        }
        for (int i = 0; i < n; i ++){
            if (! visit[i]){
                visit[i] = true;
                path.add(nums[i]);
                dfs(nums, visit, path, n);
                visit[i] = false;
                path.removeLast();
            }
        }
    }

    public static void main(String[] args){
        Q46全排列 q = new Q46全排列();
        List<List<Integer>> l = q.permute(new int[]{1,2,3});
        System.out.println(l);



    }
}
