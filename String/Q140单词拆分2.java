/*
140. 单词拆分 II
给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的句子。

说明：

分隔时可以重复使用字典中的单词。
你可以假设字典中没有重复的单词。
示例 1：

输入:
s = "catsanddog"
wordDict = ["cat", "cats", "and", "sand", "dog"]
输出:
[
  "cats and dog",
  "cat sand dog"
]
示例 2：

输入:
s = "pineapplepenapple"
wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
输出:
[
  "pine apple pen apple",
  "pineapple pen apple",
  "pine applepen apple"
]
解释: 注意你可以重复使用字典中的单词。
示例 3：

输入:
s = "catsandog"
wordDict = ["cats", "dog", "sand", "and", "cat"]
输出:
[]
通过次数40,320提交次数90,833
 */
package String;
import java.util.*;

public class Q140单词拆分2 {

    /*
    // 这题是建立在139的基础之上的。首先使用dp判断该字符串是否有解， 然后使用dfs进行遍历
    // 没有从本质上优化时间复杂度 只是通过dp判断了本题是否有解
    public List<List<String>> ans;
    public Set<String> set;
    public int n;

    public List<String> wordBreak(String s, List<String> wordDict) {
        this.set = new HashSet<>(wordDict);
        this.n = s.length();
        ans = new LinkedList<>();
        LinkedList<String> path = new LinkedList<>();

        // check if there is an answer
        boolean[] dp = new boolean[n]; // s(0, i)可以被拆分
        for (int i = 0; i < n; i ++){
            if (set.contains(s.substring(0, i + 1))){
                dp[i] = true;
                continue;
            }
            for (int j = 0; j < i; j ++){
                dp[i] = dp[i] || (dp[j] && set.contains(s.substring(j + 1, i + 1)));
                if (dp[i])
                    break;
            }
        }

        // 2. dfs 并且 转换格式
        if (dp[n - 1]){
            dfs(ans, path, s, 0);
            List<String> toReturn = new LinkedList<>();
            for (List lst : ans){
                toReturn.add(String.join(" ", lst));
            }
            return toReturn;
        }
        return new LinkedList<String>();
    }

    public void dfs(List<List<String>> ans, LinkedList<String> path, String s, int start){
        if (start == n){
            ans.add(new LinkedList<>(path));
            return;
        }
        int j = start;
        while (j < n){
            String temp = s.substring(start, j + 1);
            if (set.contains(temp)){
                path.add(temp);
                dfs(ans, path, s, j + 1);
                path.removeLast();
            }
            j ++;
        }
    }
     */

    // hard
    // 官方解法 使用map<Integer, List<List<String>>>存储之前计算的结果
    // e g. 对于案例 s = "dogcatsand" wordDict = [dog, cat, cats, sand, and]
    // 遍历到dog时，会直接返回 [cats, and] 和 [cat, sand]. 此时将dog加入即可
    // 无需对字符“c”开始的答案再遍历一遍

    public int n;
    public Map<Integer, List<LinkedList<String>>> map;
    public Set<String> set;

    public List<String> wordBreak(String s, List<String> wordDict) {
        this.n = s.length();
        this.map = new HashMap<>();
        this.set = new HashSet<>(wordDict);
        List<LinkedList<String>> ans = dfs(s, 0);
        List<String> toReturn = new LinkedList<>();
        for (List<String> lst : ans){
            toReturn.add(String.join(" ", lst));
        }
        return toReturn;
    }

    public List<LinkedList<String>> dfs(String s, int start){
        if (! map.containsKey(start)){
            List<LinkedList<String>> Breaks = new LinkedList<>();
            if (start == n){
                Breaks.add(new LinkedList<String>());
                return Breaks;
            }
            int j = start;
            while (j < n){
                String temp = s.substring(start, j + 1);
                if (set.contains(temp)){
                    List<LinkedList<String>> nextBreaks = dfs(s, j + 1);
                    for (LinkedList<String> nextBreak : nextBreaks){
                        LinkedList<String> Break = new LinkedList<>(nextBreak);
                        Break.addFirst(temp);
                        Breaks.add(Break);
                    }

                }
                j ++;
            }
            map.put(start, Breaks);
            return Breaks;
        }
        else
            return map.get(start);
    }

}
