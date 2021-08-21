/*
395. 至少有K个重复字符的最长子串
找到给定字符串（由小写字符组成）中的最长子串 T ， 要求 T 中的每一字符出现次数都不少于 k 。输出 T 的长度。

示例 1:

输入:
s = "aaabb", k = 3

输出:
3

最长子串为 "aaa" ，其中 'a' 重复了 3 次。
示例 2:

输入:
s = "ababbc", k = 2

输出:
5

最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。
 */
package String;
import java.util.*;

public class Q395 {
    public int longestSubstring(String s, int k) {
        if (s == null || s.length() < k)
            return 0;

        Map<Character, Integer> map = new HashMap();
        for (char c : s.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        char charToSplit = ' '; // 不可以赋字符 i.e. char c = ''; error !!!
        int minCount = k;
        for (Character c : map.keySet()){
            int count = map.get(c);
            if (count < k){
                if (minCount > count){
                    charToSplit = c;
                    minCount = count;
                }
            }
        }
        if (minCount == k)
            return s.length();
        else{
            int re = 0;
            String[] stringArr = s.split(String.valueOf(charToSplit));
            for (String str : stringArr){
                int temp = longestSubstring(str, k);
                re = (temp > re) ? temp : re;
            }
            return re;
        }
    }
}
