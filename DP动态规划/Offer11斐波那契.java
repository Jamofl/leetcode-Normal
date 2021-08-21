package DP动态规划;
/*
剑指 Offer 10- I. 斐波那契数列
写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：

F(0) = 0,   F(1) = 1
F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。

答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。

示例 1：

输入：n = 2
输出：1
示例 2：

输入：n = 5
输出：5

提示：

0 <= n <= 100
通过次数153,577提交次数448,032
 */
public class Offer11斐波那契 {
    int[] cache;
    public int fib(int n) {
        cache = new int[101];
        cache[0] = 0;
        cache[1] = 1;

        for (int i = 2; i <= 100; i ++)
            cache[i] = (cache[i - 1] + cache[i - 2]) % (int)(1e9 + 7);
        return cache[n];
    }

}
