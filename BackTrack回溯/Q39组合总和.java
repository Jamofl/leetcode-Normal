package BackTrack回溯;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Q39组合总和 {
    /**
     * https://leetcode.cn/problems/combination-sum/description/?envType=study-plan-v2&envId=top-interview-150
     * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
     * <p>
     * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
     * <p>
     * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：candidates = [2,3,6,7], target = 7
     * 输出：[[2,2,3],[7]]
     * 解释：
     * 2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
     * 7 也是一个候选， 7 = 7 。
     * 仅有这两种组合。
     */

    /**
     * [解题思路]
     *  1.画树形图
     *  2.分析结果中[2,2,3]重复的原因: 按照顺序遍历，到了3 不可以选之前的2；到了6，不可以选之前的2 3
     *  3.剪枝：对数组进行排序，遍历到某个节点发现target<0,则后面都无需遍历了
     * @param candidates
     * @param target
     * @return
     */

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> re = new ArrayList();
        Arrays.sort(candidates);
        dfsIteration(candidates, target, new LinkedList<>(), re, 0);
        return re;
    }

    /**
     * 迭代法回溯
     *  通过for循环的i++来遍历所有元素,递归里的index不要+1
     * @param candidates
     * @param target
     * @param path
     * @param re
     * @param index
     */
    public void dfsIteration(int[] candidates, int target, LinkedList<Integer> path, List<List<Integer>> re, int index) {
        if (target < 0 || index == candidates.length) {
            return;
        } else if (target == 0) {
            re.add(new LinkedList(path));
            return;
        }
        // 循环拿
        for (int i = index; i <= candidates.length - 1; i++) {
            if (target - candidates[i] >= 0){
                path.addLast(candidates[i]); //选取当前的数字
                System.out.println("递归之前 => " + path + "，剩余 = " + (target - candidates[i]));
                dfsIteration(candidates, target - candidates[i], path, re, i); //下一次的index从i开始 <i的数字就不用再选了 否则会有重复（因为当前数字i在i-1时已经被考虑过了）
                path.removeLast();//去掉当前的数字;下一次for循环i++了就对应着不选当前的数字了
                System.out.println("递归之后 => " + path);
            }
        }
    }


    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> re = new ArrayList();
        dfsRecursion(candidates, re, new LinkedList(), 0, target);
        return re;
    }

    /**
     * 递归法回溯
     *  通过递归层层深入 通过index+1来遍历元素
     * @param candidates
     * @param re
     * @param path
     * @param index
     * @param target
     */
    public void dfsRecursion(int[] candidates, List<List<Integer>> re, LinkedList<Integer> path, int index, int target){
        if (target < 0 || index == candidates.length){
            return;
        }
        if (target == 0){
            re.add(new ArrayList(path));
            return;
        }
        // dfs递归的本质就是通过堆栈层层深入遍历所有元素（和for循环进行迭代遍历所有元素是等效的）;而这里的index + 1就代表了遍历到下个元素了(对应for循环里的i++);
        path.addLast(candidates[index]);
        System.out.println("递归之前 => " + path + "，剩余 = " + (target - candidates[index]));
        dfsRecursion(candidates, re, path, index, target - candidates[index]);
        path.removeLast();
        System.out.println("递归之后 => " + path);
        dfsRecursion(candidates, re, path, index + 1, target);
    }

    public void dfsWrong(int[] candidates, List<List<Integer>> re, LinkedList<Integer> path, int index, int target){
        if (target < 0 || index == candidates.length){
            return;
        }
        if (target == 0){
            re.add(new ArrayList(path));
            return;
        }

        // 错误解法 上面dfs循环了一遍2 3 7,这里通过for循环又遍历了一遍2 3 7,相当于走了两边2 3 7,所以答案会有重复
        // dfs递归的本质就是通过堆栈层层深入遍历所有元素（和for循环进行迭代遍历所有元素是等效的）;而这里的index + 1就代表了遍历到下个元素了(对应for循环里的i++);
        for (int i = index; i < candidates.length; i ++){
            path.addLast(candidates[i]);
            dfsWrong(candidates, re, path, i, target - candidates[i]);
            path.removeLast();
            dfsWrong(candidates, re, path, i + 1, target);
        }
    }

    public static void main(String[] args) {
        Q39组合总和 q39 = new Q39组合总和();
        List<List<Integer>> lists = q39.combinationSum(new int[]{2, 3, 7}, 7);
        System.out.println(lists);
    }
}

