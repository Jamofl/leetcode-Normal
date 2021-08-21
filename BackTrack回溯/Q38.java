/**
 * 剑指 Offer 38. 字符串的排列
 * 输入一个字符串，打印出该字符串中字符的所有排列。
 * <p>
 * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
 * 示例:
 * <p>
 * 输入：s = "abc"
 * 输出：["abc","acb","bac","bca","cab","cba"]
 * 限制：
 * <p>
 * 1 <= s 的长度 <= 8
 */
package BackTrack回溯;

import java.util.*;

public class Q38 {

    // solution 1 正常回溯  通过set集合来去重
    /*
    List<String> ans = new 数据结构.LinkedList<>();
    public String[] permutation(String s) {
        boolean[] isSearched = new boolean[s.length()];
        Arrays.fill(isSearched, false);
        dfs(s.toCharArray(), isSearched, new String(), s.length());

        Set<String> set = new HashSet<>(ans);
        return set.toArray(new String[set.size()]);
    }

    public void dfs(char[] s, boolean[] isSearched, String path, int n){
        if (path.length() == n){
            ans.add(path);
            return;
        }

        for (int i = 0; i < s.length; i ++){
            if (!isSearched[i]) {
                isSearched[i] = true;
                dfs(s, isSearched, path + s[i], n);
                isSearched[i] = false;
            }
        }
    }
     */


    // solution 2  回溯 + 去重
    // 先对s排序，这样重复的元素一定是相邻的；在回溯时判断，如果当前元素和上一个元素相同，且上一个元素未被使用，说明一定会重复，直接跳过
    List<String> ans = new ArrayList<>();
    public String[] permutation(String s) {
        if (s == null || s.length() == 0)
            return new String[]{};
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        s = new String(arr);
        int n = s.length();
        boolean[] visit = new boolean[n];
        dfs(s, "", n, visit);
        String[] ansStr = new String[ans.size()];
        for (int i = 0; i < ans.size(); i ++)
            ansStr[i] = ans.get(i);
        return ansStr;
    }

    private void dfs(String s, String path, int n, boolean[] visit){
        if (path.length() == n){
            ans.add(path);
            return;
        }

        for (int i = 0; i < n; i ++){
            if (!visit[i]){
                if (i >= 1 && s.charAt(i) == s.charAt(i - 1) && visit[i - 1] == false)
                    continue;

                visit[i] = true;
                dfs(s, path + String.valueOf(s.charAt(i)), n, visit);
                visit[i] = false;
            }
        }
    }


    // solution 3 交换位置回溯法
    // 将当前在位置i的元素，依次和位置i 、i + 1 、i + 2的元素交换，交换后dfs进入下一层；当当前的index == n时，将arr加入到答案中
    List<String> ans3 = new ArrayList<>();

    public String[] permutation3(String s) {
        dfs(s.toCharArray(), 0, s.length());
        String[] ansStr = new String[ans3.size()];
        for (int i = 0; i < ans3.size(); i++)
            ansStr[i] = ans3.get(i);
        return ansStr;
    }

    private void swap(char[] arr, int i, int j) {
        if (i == j)
            return;
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private void dfs(char[] arr, int index, int n) {
        if (index == n) {
            ans3.add(new String(arr));
            return;
        }
        // 新建一个set用于存储已经交换过的元素，若要交换的元素已经存在于set中，直接跳过即可
        Set<Character> set = new HashSet<Character>();
        for (int i = index; i < n; i++) {
            if (set.contains(arr[i]))
                continue;
            set.add(arr[i]);
            swap(arr, index, i);
            dfs(arr, index + 1, n);
            swap(arr, index, i);
        }
    }

    public static void main(String[] args) {
        Q38 q = new Q38();
        String s = "abb";
        String[] re = q.permutation(s);
        System.out.println(Arrays.asList(re));
    }
}
