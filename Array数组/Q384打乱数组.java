/*
384. 打乱数组
给你一个整数数组 nums ，设计算法来打乱一个没有重复元素的数组。

实现 Solution class:
Solution(int[] nums) 使用整数数组 nums 初始化对象
int[] reset() 重设数组到它的初始状态并返回
int[] shuffle() 返回数组随机打乱后的结果

示例：

输入
["Solution", "shuffle", "reset", "shuffle"]
[[[1, 2, 3]], [], [], []]
输出
[null, [3, 1, 2], [1, 2, 3], [1, 3, 2]]

解释
Solution solution = new Solution([1, 2, 3]);
solution.shuffle();    // 打乱数组 [1,2,3] 并返回结果。任何 [1,2,3]的排列返回的概率应该相同。例如，返回 [3, 1, 2]
solution.reset();      // 重设数组到它的初始状态 [1, 2, 3] 。返回 [1, 2, 3]
solution.shuffle();    // 随机返回数组 [1, 2, 3] 打乱后的结果。例如，返回 [1, 3, 2]
 */
package Array数组;

import java.util.*;
public class Q384打乱数组 {

    // Fisher-Yates 洗牌算法
    /*
    思路
        我们可以用一个简单的技巧来降低之前算法的时间复杂度和空间复杂度，
        那就是让数组中的元素互相交换，这样就可以避免掉每次迭代中用于修改列表的时间了。
    算法
        Fisher-Yates 洗牌算法跟暴力算法很像。在每次迭代中，生成一个范围在当前下标到数组
        末尾元素下标之间的随机整数。接下来，将当前元素和随机选出的下标所指的元素互相交换 -
        这一步模拟了每次从 “帽子” 里面摸一个元素的过程，其中选取下标范围的依据在于每个被摸出
        的元素都不可能再被摸出来了。此外还有一个需要注意的细节，当前元素是可以和它本身互相交换
        的 - 否则生成最后的排列组合的概率就不对了。
     */

    public int[] original;
    public int[] nums;
    public int n;
    public Random random;
    public Q384打乱数组(int[] nums) {
        this.original = nums.clone();
        this.nums = nums;
        this.n = nums.length;
        this.random = new Random();
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        this.nums = original.clone();
        return this.nums;
    }

    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int rand;
        for (int i = 0; i < n; i++){
            rand = this.random.nextInt(n);
            swap(nums, i, rand);
        }
        return nums;
    }

    private void swap(int[] nums, int i, int j){
        if (i == j)
            return;
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    public static void main(String[] args){
//        Q384打乱数组 q = new Q384打乱数组(new int[] {1,2,3,4});
//        q.shuffle();
        List<Integer> lst = new ArrayList<>();
        lst.add(3);
        lst.add(2);
        lst.add(1);
        lst.remove(1); // index
    }
}
