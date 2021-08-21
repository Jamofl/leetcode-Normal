package 数据结构.Stack;
import java.util.*;

public class Q150 {
    public Stack<Integer> stack;
    public Set<String> LEGALTOKENS = new HashSet();
    int res;
    public int evalRPN(String[] tokens) {
        LEGALTOKENS = Set.of("+", "-", "*", "/");
        stack = new Stack();
        for(String token : tokens)
            eval(token);
        return stack.peek();
    }

    private void eval(String token){
        if (!LEGALTOKENS.contains(token))
            stack.push(Integer.parseInt(token));
        else{
            int oprand2 = stack.pop();
            int oprand1 = stack.pop();
            if (token.equals("+"))
                stack.push(oprand1 + oprand2);
            else if (token.equals("-"))
                stack.push(oprand1 - oprand2);
            else if (token.equals("*"))
                stack.push(oprand1 * oprand2);
            else if (token.equals("/"))
                stack.push(oprand1 / oprand2);
        }
    }

    public static void main(String[] args){
        Q150 q = new Q150();
        int r = q.evalRPN(new String[]{"3","13","5","/","+"});
        String s = "12+23-3*4/6";
        String[] re = s.split("\\+|-|\\*|/");
        System.out.println(s.replaceAll("\\d+", ""));
    }
}
