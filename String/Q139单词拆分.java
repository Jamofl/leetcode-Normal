/*
139. 单词拆分
给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。

说明：
拆分时可以重复使用字典中的单词。
你可以假设字典中没有重复的单词。
示例 1：

输入: s = "leetcode", wordDict = ["leet", "code"]
输出: true
解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
示例 2：

输入: s = "applepenapple", wordDict = ["apple", "pen"]
输出: true
解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
     注意你可以重复使用字典中的单词。
示例 3：

输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
输出: false
 */
package String;
import java.util.*;
public class Q139单词拆分 {


    // 方法1: 递归枚举 + 备忘录 容易想到

    public boolean wordBreak(String s, List<String> wordDict) {
        return wordBreakRec(s, wordDict, new HashMap<String, Boolean>());
    }

    /**
     * 递归 + 备忘录
     * 当前问题可以拆解为
     *  s能否被拆分 = s以单词word开头 && s的剩余部分可以被拆分   （转成动态规划 由于最终结果是dp[n] 所以需要从后往前遍 即s的最后一部分是单词 && 剩余部分可以被拆分）
     * @param s
     * @param wordDict
     * @param map 表示当前单词是否能被拆分
     * @return
     */
    public boolean wordBreakRec(String s, List<String> wordDict, Map<String, Boolean> map) {
        if (map.containsKey(s)) {
            return map.get(s);
        }
        boolean re = false;
        if (s == null || s.length() == 0) {
            re = true;
        } else {
            for (String word : wordDict) {
                if (s.startsWith(word) && wordBreakRec(s.substring(word.length()), wordDict, map)) {
                    re = true;
                    break;
                }
            }
        }
        map.put(s, re);
        return re;
    }




    // 方法2  BFS 难想
    public boolean wordBreak2(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] visit = new boolean[n]; // 表示字符串s(i:n)在不在字典内
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        while (queue.size() != 0) {
            int start = queue.poll();
            if (start == n)
                return true;
            if (visit[start]) // 若上一次已经访问过start后面的所有元素，但仍未返回true，说明start后面的元素不在字典中，直接continue即可
                continue;

            visit[start] = true;
            for (int j = start; j < n; j++) {
                if (wordDict.contains(s.substring(start, j + 1))) {
                    queue.offer(j + 1);
                }
            }
        }
        return false;
    }


    /**
     * 方法3   动态规划
     * 转移方程不是很好想
     * 当前字符串s能被拆分 == s的最后一个单词出现在字典中 && 去除最后一个单词的字符串s能被拆分
     *  dp[i] = dp[j] && subString(j - 1, i)是单词
     *  j [1,i]
     *  i [1,n]
     *
     *  dp[i]表示s[0,i-1]能否被拆分 比如leetcode即dp[8] 表示[0,7]能否被拆分 对应s.subString(0,8)
     *  dp[0]默认初始化为true 即空字符串也是可以被拆分的
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak3(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = i ; j >= 1 ; j --){
                if (wordDict.contains(s.substring(j - 1, i)) && dp[j - 1]){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
}

    public static void main(String[] args){
//        Q139单词拆分 q = new Q139单词拆分();
//        boolean re = q.wordBreak("leetcode", List.of("leet", "code"));
//        System.out.println(re);

        List<String>[] cache = new LinkedList[5];
        System.out.println();
    }
}
