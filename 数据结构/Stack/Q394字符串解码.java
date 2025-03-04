package 数据结构.Stack;

import java.util.*;

public class Q394字符串解码 {
    public String decodeString(String s) {
        return dfs(0, s);

    }


    private String dfs(int index, String s){
        if (index == s.length() || s.charAt(index) == ']'){
            return "";
        }
        String re = "";
        // 构建当前数字和字符串组成的字符串 然后递归的构建剩下的部分
        if (Character.isDigit(s.charAt(index))){
            String numStr = "";
            while (Character.isDigit(s.charAt(index))) {
                numStr += String.valueOf(s.charAt(index));
                index++;
            }
            index ++;
            String temp = dfs(index, s);
            index ++;// 过滤右括号
            int num = Integer.valueOf(numStr);
            re += getKTimesString(num, temp);
        }
        // 字母 加入re
        else if (Character.isLetter(s.charAt(index))){
            re += String.valueOf(s.charAt(index));
            index ++;
        }
        // [ 啥也不加
        else if (s.charAt(index) == '['){
            re += "";
        }
        return re + dfs(index, s);

    }

    private String getKTimesString(int k, String s) {
        String re = "";
        while (k > 0) {
            re = re + s;
            k--;
        }
        return re;
    }

    public static void main(String[] args) {
        Q394字符串解码 q = new Q394字符串解码();
        String re = q.decodeString("3[a]2[b]");
        System.out.println(re);
    }
}
