/*
279. 完全平方数
给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。

示例 1:

输入: n = 12
输出: 3
解释: 12 = 4 + 4 + 4.
示例 2:

输入: n = 13
输出: 2
解释: 13 = 4 + 9.
 */
package DP动态规划;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Q279完全平方数 {


    /**
     * 【基础版】 动态规划
     * 转移方程:
     *     for i (1,n)
     *       for j (1,n/2)
     *          f(i) = min[f(i), f(i - j)]
     * 时间复杂度 ON2
     * 空间复杂度 0N
     *
     *  1 2 3 4 5 6 7 8 9 10 11 12
     * 1 2 3 1 2 3 4 2 1  2
     *
     * 6 = 1 + 1 + 4
     * dp[6] = dp[4] + dp[2]
     * dp[6] = dp[3] + dp[3]
     * dp[6] = dp[5] + dp[1]
     *
     * j = n / 2 无论n是奇数还是偶数 直接除以2都是想下取整
     * 6  1 2 3
     * 7  1 2 3
     * 8  1 2 3 4
     * 9  1 2 3 4
     *
     * n  1 2 3 ... n / 2
     *
     *
     * for i(1, n)
     *     for j(1, n/2)
     * @param n
     * @return
     */
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            if (isSquare(i)) {
                dp[i] = 1;
            }
            else {
                for (int j = 1; j <= i / 2; j++) {
                    dp[i] = Math.min(dp[i], dp[j] + dp[i - j]);
                }
            }
        }
        return dp[n];
    }

    public boolean isSquare(int n) {
        if ((int)Math.sqrt(n) * (int)Math.sqrt(n) == n) {
            return true;
        }
        return false;
    }

    /**
     * 【进阶版】
     */
    // 自顶向下
    // Solution 1: 使用备忘录的递归
    // 状态转移方程 f(n) = min(f(i) + 1)   i = (1, 2 ... sqrt(n))
//    for (int j = 1; j <= (int) Math.sqrt(n); j ++){
//        f[n] = Math.min(f[n], f(n - j * j) + 1);
//    }

    public int[] cache;
    public int numSquares1(int n) {
        cache = new int[n + 1];
        return helper(n);
    }

    private int helper(int n){
        if (n == 0)
            return 0;
        if (cache[n] != 0)
            return cache[n];
        int ans = Integer.MAX_VALUE;
        for (int j = 1; j <= (int) Math.sqrt(n); j ++){
            ans = Math.min(helper(n - j * j) + 1, ans); // 若j * j == n,即n是一个完全平方数时，下一次n=0； 因此要加入special case 0 的判断
        }
        cache[n] = ans;
        return ans;
    }



    // 自底向上
    // Solution 2: 动态规划。 与递归相比的好处是，迭代时i从1开始向上递增，因此在计算第i个数时，对于所有小于i的数k都已经计算过，可以直接使用其结果，不需判断dp[k]是否存在
    // 复杂度分析
    //
    //时间复杂度：O(N 根号)n
    // )，在主步骤中，我们有一个嵌套循环，其中外部循环是 n 次迭代，而内部循环最多需要 \sqrt{n}
    //空间复杂度：O(n)，使用了一个一维数组 dp。

    public int numSquares2(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for(int i = 1; i <= n; i ++){ // 外层循环遍历i 递增 确保了i之前的答案都是遍历过的
            for(int j = 1; j <= (int)(Math.sqrt(i)); j++){ // 内层循环遍历j 即从1，2 ...  sqrt(i)的所有数
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1); // 加的这个1，就是减掉的这个完全平方书j*j所用掉的1次；
            }
        }
        return dp[n];
    }



    // Solution 3: 贪心枚举
    // 判断n是否可以被拆解为k个完全平方数的和 k从小到大枚举递增 第一个返回的即为最小的k
    // 其中，1 <= k <= n
    // 需要先构建一个set 用于存放所有的完全平方数
    List<Integer> squares = new LinkedList();// 全局变量，用于存放从 1 ~ n 的完全平方数
    public int numSquares3(int n) {
        // 初始化数组
        for(int i = 1; i <= (int) Math.sqrt(n); i ++)
            squares.add(i * i); // 1 4 9 16...

        // 遍历k 找到最小的那个k
        for (int k = 1; k <= n; k ++){ // i = 1 2 3 4...
            if (canNBeSplitToKSquareSum(n, k))
                return k;
        }
        return 0;
    }

    /**
     * 判断n这个数是否可以被分成k个完全平方数的和。由于主函数调用时，k从1开始往上递增，因此最开始返回的k一定是最小值。
     * @param n
     * @param k
     * @return
     */
    private boolean canNBeSplitToKSquareSum(int n, int k){
        // k == 1说明到头了 当前的n必须被拆分为1个完全平方数的和 也就是当前的n必须本身就是完全平方数
        if (k == 1){
            if (squares.contains(n))
                return true;
            else
                return false;
        }
        // 遍历所有的完全平方数 递归的判断n是否可以被拆分k分， n-square被拆分为k-1份 以此类推
        for(int square : squares){ // 顺序不重要
            if (canNBeSplitToKSquareSum(n - square, k - 1))
                return true;
        }
        return false;
    }



    public static void main(String[] args){
        Q279完全平方数 q = new Q279完全平方数();
        int r = q.numSquares(12);
        System.out.println(r);

     }

}
