/**
    给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。

    有效字符串需满足：

    左括号必须用相同类型的右括号闭合。
    左括号必须以正确的顺序闭合。
    注意空字符串可被认为是有效字符串。

*/
import java.util.HashMap;
import java.util.Stack;



class Q20 {
    public boolean isValid(String s) {
        if(s == null)
            return true;
        else if(s.length() % 2 == 1)
            return false;

        HashMap map = new HashMap();
        map.put('(', ')');
        map.put('{', '}');
        map.put('[', ']');

        Stack<Character> st = new Stack();
        for(int i = 0 ; i < s.length(); i ++){
            char ch = s.charAt(i);
            if(ch == '(' || ch == '{' || ch == '[')
                st.push(ch);
            else if(ch == ')' || ch == '}' || ch == ']'){
                if(st.empty())
                    return false;
                char c = st.pop();

                if((char)(map.get(c)) != ch)
                    return false;
            }
        }
        if(!st.empty())
            return false;
        return true;

    }
}