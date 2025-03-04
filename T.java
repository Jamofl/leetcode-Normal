import java.util.*;

public class T {

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0){
            return 0;
        }
        Set<Character> set = new HashSet<>();
        int ans = 0, n = s.length();
        int j = 0;
        set.add(s.charAt(j));
        for (int i = 0; i < s.length(); i ++){
            if (i != 0){
                set.remove(s.charAt(i - 1));
            }
            while (j + 1 < n && !set.contains(s.charAt(j + 1))){
                set.add(s.charAt(j + 1));
                j ++;
            }
            ans = Math.max(ans, set.size());
        }
        return ans;

    }


    public static void main(String[] args) {
        T t = new T();
        int b = t.lengthOfLongestSubstring("pwwkew");
        System.out.println(b);

    }
}
