package BackTrack回溯;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
 */
public class Q17电话号码组合 {
    public static String[] cons = new String[]{"","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};


    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if (digits == null || digits.length()== 0){
            return ans;
        }
        dfs(digits, ans, "", 0);
        return ans;
    }

    public void dfs(String digits, List<String> ans, String path, int index){
        if (index == digits.length()){
            ans.add(path);
            return;
        }
        String keyStringSet = cons[digits.charAt(index) - '1'];
        for(char c : keyStringSet.toCharArray()){
            dfs(digits, ans, path + c, index + 1);
        }
    }
}
