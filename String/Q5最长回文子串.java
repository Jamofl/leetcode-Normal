/*
5. 最长回文子串
给你一个字符串 s，找到 s 中最长的回文子串。



示例 1：

输入：s = "babad"
输出："bab"
解释："aba" 同样是符合题意的答案。
示例 2：

输入：s = "cbbd"
输出："bb"
示例 3：

输入：s = "a"
输出："a"
示例 4：

输入：s = "ac"
输出："a"


提示：

1 <= s.length <= 1000
s 仅由数字和英文字母（大写和/或小写）组成
 */
package String;

public class Q5最长回文子串 {
    // O(n2)
    public String longestPalindrome(String s) {
        // 边界情况
        int len = s.length();
        if (len <= 1) {
            return s;
        }

        int maxLen = 0;
        int begin = 0;
        // dp[i][j] 表示 s[i:j+1] 是否是回文串
        boolean[][] dp = new boolean[len][len];
        char[] charArray = s.toCharArray();

        // i 必须小于j，整体在表格的右上方
        for (int i = len - 1; i >= 0; i --) {
            for (int j = i; j <= len - 1; j ++) {
                if (j == i)
                    dp[i][j] = true;
                else if (j == i + 1)
                    dp[i][j] = (charArray[i] == charArray[j]);
                else // 必须保证填表时该格的左下方有值
                    dp[i][j] = (charArray[i] == charArray[j]) && (dp[i + 1][j - 1]);

                // 只要 dp[i][j] == true 成立，就表示子串 s[i..j] 是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }
}
