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


    // 方法1: 递归枚举 + 备忘录 ，容易想到
    // 用一个cache[]数组存储 (由于是pure function，可以这么干） dfs 先前递归出来的结果，即可大大降低时间复杂度
    // 用 visit[]数组来判定 当前元素是否访问过
    public List<String> wordDict;
    boolean[] cache;
    boolean[] visit;
    public boolean wordBreak(String s, List<String> wordDict) {
        this.wordDict = wordDict;
        int n = s.length();
        this.cache = new boolean[n + 1];// cache[i]表示s(i, n)在不在字典中
        this.visit = new boolean[n + 1];
        dfs(s, 0, n);
        return cache[0];
    }

    public boolean dfs(String s, int start, int n){
        if (start == n){
            return true;
        }

        if (visit[start])
            return cache[start];

        visit[start] = true;
        int j = start;
        while (j < n){
            if (wordDict.contains(s.substring(start, j + 1))){
                if (dfs(s, j + 1, n)){
                    cache[start] = true;
                    return true;
                }
            }
            j++;
        }
        cache[start] = false;
        return false;
    }




    // 方法2  BFS
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


    // 方法3: 动态规划
    // 转移方程为 dp[i] = dp[i] || (dp[j] && wordDict.contains(s.substring(j + 1, i + 1))) for j in range(0, i)
    // 即s(0, j)在字典中 并且 s(j + 1, i)也在字典中
    public boolean wordBreak3(String s, List<String> wordDict) {
    int n = s.length();
    boolean[] dp = new boolean[n]; // 表示s(0, i)是否在字典中
    for (int i = 0; i < n; i ++){
        if (wordDict.contains(s.substring(0, i + 1))){
            dp[i] = true;
            continue;
        }
        for (int j = 0; j < i; j ++){
            dp[i] = dp[i] || (dp[j] && wordDict.contains(s.substring(j + 1, i + 1)));
        }
    }
    return dp[n - 1];
}

    public static void main(String[] args){
//        Q139单词拆分 q = new Q139单词拆分();
//        boolean re = q.wordBreak("leetcode", List.of("leet", "code"));
//        System.out.println(re);

        List<String>[] cache = new LinkedList[5];
        System.out.println();
    }
}
