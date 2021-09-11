/*
474. 一和零
给你一个二进制字符串数组 strs 和两个整数 m 和 n 。

请你找出并返回 strs 的最大子集的大小，该子集中 最多 有 m 个 0 和 n 个 1 。

如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。



示例 1：

输入：strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
输出：4
解释：最多有 5 个 0 和 3 个 1 的最大子集是 {"10","0001","1","0"} ，因此答案是 4 。
其他满足题意但较小的子集包括 {"0001","1"} 和 {"10","1","0"} 。{"111001"} 不满足题意，因为它含 4 个 1 ，大于 n 的值 3 。
示例 2：

输入：strs = ["10", "0", "1"], m = 1, n = 1
输出：2
解释：最大的子集是 {"0", "1"} ，所以答案是 2 。


提示：

1 <= strs.length <= 600
1 <= strs[i].length <= 100
strs[i] 仅由 '0' 和 '1' 组成
1 <= m, n <= 100
 */
package DP动态规划;

public class Q474一和零_零一背包问题 {

    // 方法1 dfs回溯遍历  思路正确 但存储cache仍有问题  有少部分案例无法通过
    int len;
    int ans;
    int[][][] cache;
    public int findMaxForm(String[] strs, int m, int n) {
        this.len = strs.length;
        this.ans = 0;
        this.cache = new int[len + 1][m + 1][n + 1];
        dfs(0, strs, 0, m, n);
        return cache[0][m][n];
    }

    private void dfs(int count, String[] strs, int start, int m, int n){
        if (start >= len){
            ans = Math.max(ans, count);
            // cache[start][m][n] = Math.max(ans, cache[start][m][n]);
            return;
        }

        if (cache[start][m][n] != 0){
            ans = Math.max(ans, count);
            cache[start][m][n] = Math.max(ans, cache[start][m][n]);
            ans = Math.max(ans, cache[start][m][n]);
            return;
        }

        for (int i = start; i < len; i ++){
            int count0 = 0;
            int count1 = 0;
            for (char c : strs[i].toCharArray()){
                if (c == '0')
                    count0 ++;
                else if (c == '1')
                    count1 ++;
            }
            // 不放当前字符串
            if (m - count0 < 0 || n - count1 < 0)
                dfs(count, strs, i + 1, m, n);
                // 放当前字符串
            else
                dfs(count + 1, strs, i + 1, m - count0, n - count1);
        }
        ans = Math.max(ans, count);
        cache[start][m][n] = Math.max(ans, cache[start][m][n]);
        ans = Math.max(ans, cache[start][m][n]);
    }

    // 方法2 动态规划
    // 初始化时多加一个0行 和 0列，避免了分类讨论的情况。因为0个物品时，总价值为0；容量为0时，总价值为0
    public int findMaxForm2(String[] strs, int m, int n) {
        int len = strs.length;
        int[][][] dp = new int[len + 1][m + 1][n + 1];  // 表示i个物品，j个0， k个1时能放的最大个数

        for (int i = 1; i <= len; i ++){
            String temp = strs[i - 1];
            int count0 = 0;
            int count1 = 0;
            for (char c : temp.toCharArray()){
                if (c == '0')
                    count0 ++;
                else if (c == '1')
                    count1 ++;
            }

            // j 和 k都要从0开始遍历，因为比如j = 0, k ！= 0时，是可以放东西进去的（比如 '1'），此时的dp[i][j][k] != 0
            for (int j = 0; j <= m; j ++){
                for (int k = 0; k <= n; k ++){
                    dp[i][j][k] = dp[i - 1][j][k];
                    if (count0 <= j && count1 <= k) // 如果能放下当前物品
                        dp[i][j][k] = Math.max(dp[i - 1][j][k], 1 + dp[i - 1][j - count0][k - count1]);
                }
            }
        }
        return dp[len][m][n];
    }

    // 方法3 动态规划 空间压缩的动态规划 将二维数组压缩为一维数组 相当于直接复用上一行的结果 但是需要逆序遍历 否则一个物品会被使用到多次
    public int findMaxForm3(String[] strs, int m, int n) {
        int len = strs.length;
        int[][] dp = new int[m + 1][n + 1];  // j个0， k个1时能放的最大个数

        for (int i = 0; i <len; i ++){
            String s = strs[i];
            int count0 = 0;
            int count1 = 0;
            for (char c : s.toCharArray()){
                if (c == '0')
                    count0 ++;
                else if (c == '1')
                    count1 ++;
            }

            // j k 都逆序遍历 防止一个物品被放入多次
            // 填表的顺序是从上到下 从左到右；因为后面值的更新需要依赖于前面的值 需要逆序遍历
            // 从而保证当前使用的dp[j - count0][k - count1]是上一行的 而不是这一行刚刚更新的值
            for (int j = m; j >= 0; j --){
                for (int k = n; k >= 0; k --){
                    if (count0 <= j && count1 <= k) // 如果能放下当前物品
                        dp[j][k] = Math.max(dp[j][k], 1 + dp[j - count0][k - count1]);
                }
            }
        }
        return dp[m][n];
    }
}
