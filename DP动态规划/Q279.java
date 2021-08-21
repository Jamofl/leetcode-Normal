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

public class Q279 {

    // 自顶向下
    // Solution 1: 递归动态规划 + 备忘录记忆法。本质上和递归相同。
    public int[] cache;
    public int numSquares(int n) {
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
            ans = Math.min(helper(n - j * j) + 1, ans); // 若j * j == n,即n是一个完全平方数时，下一次会调用0； 因此要加入special case 0 的判断
        }
        cache[n] = ans;
        return ans;
    }


    /*
    // 自底向上
    // Solution 2: 动态规划 ，迭代。 与递归相比的好处是，迭代时i从1开始向上递增，因此在计算第i个数时，对于所有小于i的数k都已经计算过，可以直接使用其结果，不需判断dp[k]是否存在
    // 复杂度分析
    //
    //时间复杂度：O(N 根号)n
    // )，在主步骤中，我们有一个嵌套循环，其中外部循环是 n 次迭代，而内部循环最多需要 \sqrt{n}
    //空间复杂度：O(n)，使用了一个一维数组 dp。

    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for(int i = 1; i <= n; i ++){ // i的值递增，确保了i之前的答案都是对的。
            for(int j = (int)(Math.sqrt(i)); j >= 1; j--){ // 即从1，2 ...  sqrt(i)的所有数
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1); // 加的这个1，就是减掉的这个完全平方书j*j所用掉的1次；
            }
        }
        return dp[n];
    }
    */

    /*
    // Solution 3: 贪心枚举
    // 通过枚举从1 ~ k个完全平方数的和，最早返回的k的值记为最小值。 其中，1 <= k <= n
    List<Integer> squares = new LinkedList();// 全局变量，用于存放从 1 ~ n 的完全平方数
    public int numSquares(int n) {
        for(int i = 1; i <= (int) Math.sqrt(n); i ++)
            squares.add(i * i); // 1 4 9 16...
        Collections.reverse(squares);// 加不加都可

        for (int i = 1; i <= n; i ++){ // i = 1 2 3 4...
            if (isPerfectSquareNum(n, i))
                return i;
        }
        return 0;
    }

    // 判断n这个数是否可以被分成k个完全平方数的和。由于主函数调用时，k从1开始往上递增，因此最开始返回的k一定是最小值。
    private boolean isPerfectSquareNum(int n, int k){
        if(k == 1){
            if (squares.contains(n))
                return true;
            else
                return false;
        }
        for(int square : squares){ // 顺序不重要
            if (isPerfectSquareNum(n - square, k - 1))
                return true;
        }
        return false;
    }

     */

    public static void main(String[] args){
        Q279 q = new Q279();
        int r = q.numSquares(12);
        System.out.println(r);

     }

}
