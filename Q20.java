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
        if(s == null){
            return true;
        }
        if(s.length() % 2 == 1){
            return false;
        }

        HashMap<Character,Character> map = new HashMap();
        map.put('(', ')');
        map.put('{', '}');
        map.put('[', ']');

        Stack<Character> stack = new Stack();
        for(int i = 0 ; i < s.length(); i ++){
            char ch = s.charAt(i);
            // 如果是左括号 入栈
            if(map.containsKey(ch)){
                stack.push(ch);
            }
            // 如果是右括号 出栈并检查是否匹配
            else{
                if(stack.empty()){
                    return false;
                }
                char pop = stack.pop();
                if((map.get(pop)) != ch)
                    return false;
            }
        }
        if(!stack.isEmpty()){
            return false;
        }
        return true;
    }
}