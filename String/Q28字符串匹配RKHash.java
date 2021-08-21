package String;

import java.awt.event.MouseAdapter;

/*
28. 实现 strStr()
实现 strStr() 函数。

给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回  -1 。

说明：

当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。

对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与 C 语言的 strstr() 以及 Java 的 indexOf() 定义相符。

示例 1：

输入：haystack = "hello", needle = "ll"
输出：2
示例 2：

输入：haystack = "aaaaa", needle = "bba"
输出：-1
示例 3：

输入：haystack = "", needle = ""
输出：0
 */
public class Q28字符串匹配RKHash {

    // Rabin - Karp 算法
    // 时间复杂度：O(N)，计算 pattern 字符串的哈希值需要 O(L)时间，之后需要执行 (N - L) 次循环，每次循环的计算复杂度为O(1)

    public static final long BASE = 26;
    public static final long MODULUS = (long) Math.pow(2, 31);
    public int strStr(String input, String pattern) {
        int n = input.length();
        int m = pattern.length();
        if (n < m)
            return -1;

        long hashPattern = 0;
        long hashStrOfLenM = 0;
        for (int i = 0; i < m; i ++){
            hashPattern = (hashPattern * BASE + (pattern.charAt(i) - 'a')) % MODULUS;
        }
        for (int i = 0; i < m; i ++){
            hashStrOfLenM = (hashStrOfLenM * BASE + (input.charAt(i) - 'a')) % MODULUS;
        }

        long baseToM = 1;
        for (int i = 0; i < m; i++)
            baseToM = (baseToM * BASE) % MODULUS;
        for (int i = 0; i <= n - m; i ++){
            if (hashPattern == hashStrOfLenM){
                if (pattern.equals(input.substring(i, i + m)))
                    return i;
            }
            if (i == n - m)
                break;
            hashStrOfLenM = (hashStrOfLenM * BASE - (input.charAt(i) - 'a') * baseToM +
                    input.charAt(i + m) - 'a') % MODULUS;

//            long lastFirstHash = (input.charAt(i) - 'a') * (long) Math.pow(BASE, m - 1) % MODULUS;
//            hashStrOfLenM = (BASE * (hashStrOfLenM - lastFirstHash) + input.charAt(i + m) - 'a') % MODULUS;
        }
        return -1;
    }

    public static void main(String[] args){
        Q28字符串匹配RKHash q = new Q28字符串匹配RKHash();
        int r = q.strStr("ababcaababcaabc", "ababcaabc");
        System.out.println(r);
    }
}
