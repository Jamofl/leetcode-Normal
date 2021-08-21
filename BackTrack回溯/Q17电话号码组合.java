package BackTrack回溯;

import java.util.LinkedList;
import java.util.List;

/*
https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
 */
public class Q17电话号码组合 {
    String[] map = new String[]{"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        if (digits.equals(""))
            return new LinkedList<String>();
        LinkedList<String> ans = new LinkedList<>();
        dfs("", ans, digits);
        return ans;
    }

    private void dfs(String path, LinkedList<String> ans, String digits){
        if (digits.length() == 0){
            ans.add(path);
            return;
        }

        int index = digits.charAt(0) - '0' - 2;
        String alph = map[index]; // "abc"
        for (int i = 0; i < alph.length(); i ++){
            dfs(path + alph.charAt(i), ans, digits.substring(1, digits.length()));
        }
    }
}
