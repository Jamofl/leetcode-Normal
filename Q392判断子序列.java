/*
392. 判断子序列
给定字符串 s 和 t ，判断 s 是否为 t 的子序列。

字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。

进阶：

如果有大量输入的 S，称作 S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？


示例 1：

输入：s = "abc", t = "ahbgdc"
输出：true
示例 2：

输入：s = "axc", t = "ahbgdc"
输出：false

提示：

0 <= s.length <= 100
0 <= t.length <= 10^4
两个字符串都只由小写字符组成。
 */
import java.util.*;
public class Q392判断子序列 {

    public boolean isSubsequence(String s, String t) {
        Set<String> subStringT = allSubString(t, s.length());
        return subStringT.contains(s);
    }

    private Set<String> allSubString(String s, int k){
        int n = s.length();
        Set<String> set = new HashSet<>();
        boolean[] visit = new boolean[n];
        subString(set, visit, s, "", 0, k);
        return set;
    }

    private void subString(Set<String> ans, boolean[] visit, String s, String path, int start, int k){
        if (path.length() == k){
            ans.add(path);
            return;
        }
        for (int i = start; i < s.length(); i ++) {
                subString(ans, visit, s, path + s.charAt(i), i + 1, k);
        }
    }

    public static void main(String[] args){
//        Q392判断子序列 q = new Q392判断子序列();
//        boolean r = q.isSubsequence("adf", "abcde");
//        System.out.println(r);
        Random rand = new Random();
        for (int i = 0; i < 10; i ++)
            System.out.println(rand.nextInt(10));


    }
}
