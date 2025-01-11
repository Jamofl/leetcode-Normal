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

    /**
     * 方法1 动态规划
     * dp含义 dp[i][j]：s[i, j]为回文字符串
     * 转移方程   P(i, j ) = P(i+1,  j-1) && s[i] == s[j]
     * 答案的解 长度最长的dp[i][j]对应的解
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        char[] arr = s.toCharArray();
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int max = 0;
        int tempi = 0;
        for (int i = n - 1; i >= 0; i--){
            for (int j = i; j <= n - 1; j ++){
                if (j == i || j == i + 1){
                    dp[i][j] = (arr[i] == arr[j]);
                }
                else {
                    dp[i][j] = dp[i+1][j-1] && (arr[i] == arr[j]);
                }
                if (dp[i][j] && (j - i + 1 > max)){
                    max = j - i + 1;
                    tempi = i;
                }
            }
        }
        return s.substring(tempi, tempi + max);
    }



    /**
     * 方法2 中心扩散法 遍历s中的每一个字符将其作为回文中心(长度可能为1或2)
     *  通过回文中心不断向外扩散 找到最长的回文字符串
     * @param s
     * @return
     */
    public String longestPalindrome2(String s) {
        int n = s.length();
        String ans = "";
        for (int i = 0; i < n; i++){
            // 枚举回文中心长度为1或2的情况
            String s1 = expand(s, i, i, n);
            String s2 = expand(s, i, i + 1, n);
            String tempAns = s1.length() > s2.length() ? s1 : s2;
            ans = ans.length() < tempAns.length() ? tempAns : ans;
        }
        return ans;
    }


    /**
     * 从当前的start end进来就开始判断 把第一次的初始情况也当做循环中的一环来判断 不要特判第一次
     * @param s
     * @param start
     * @param end
     * @param n
     * @return
     */
    private String expand(String s, int start, int end, int  n){
        while (start >= 0 && end <= n - 1 &&
                s.charAt(start) == s.charAt(end)){
            start --;
            end ++;
        }
        return s.substring(start + 1, end);
    }



}
