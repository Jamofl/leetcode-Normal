/*
125. 验证回文串
给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。

说明：本题中，我们将空字符串定义为有效的回文串。

示例 1:

输入: "A man, a plan, a canal: Panama"
输出: true
示例 2:

输入: "race a car"
输出: false
 */
package String;

public class Q125Palindrome {
    public boolean isPalindrome(String s) {
        if (s.length() == 0)
            return true;
        s = s.toLowerCase();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length() ; i ++){
            char c = s.charAt(i);
            if ((c <= 'z' && c >= 'a') || (c >= '0' && c <= '9'))
                sb.append(String.valueOf(c));
        }

        String clearS = sb.toString();
        int i = 0;
        int j = clearS.length() - 1;
        while (j > i){
            if (clearS.charAt(i) != clearS.charAt(j))
                return false;
            i ++;
            j --;
        }
        return true;

    }
}
