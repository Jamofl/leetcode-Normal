package String;
import java.util.*;
/**
 * 剑指 Offer 48. 最长不含重复字符的子字符串
 * 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
 *
 *
 *
 * 示例 1:
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 */
public class Offer48无重复字符最长子串 {
    public int lengthOfLongestSubstring(String s) {
        char[] charArr = s.toCharArray();
        Set<Character> set = new HashSet<>();
        int max = 0;
        int n = s.length();

        int i = 0;
        while (i < n){
            int j = i;
            while (j < n){
                char cur = charArr[j];
                if (!set.contains(cur)){
                    set.add(cur);
                    j ++;
                }
                else{
                    break;
                }
            }
            max = Math.max(max, set.size());
            set.clear();
            i ++;
        }
        return max;
    }

    public static void main(String[] args) {
        Offer48无重复字符最长子串 q = new Offer48无重复字符最长子串();
        int r = q.lengthOfLongestSubstring("abcabcbb");
        System.out.println(r);
    }
}
