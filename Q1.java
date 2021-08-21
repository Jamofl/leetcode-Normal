import java.util.*;

public class Q1 {


    public Queue<Character> q = new LinkedList<>();
    public void convert(String exp){
        for (char c : exp.toCharArray()){
            if (c != ' ')
                q.offer(c);
        }
    }


    public int eval(){

        char first = q.poll();
        int ans = 0;

        if (first == '('){
            return eval();
        }

        else if (first == '+'){
            int operand1 = eval();
            int operand2 = eval();
            ans = operand1 + operand2;
        }

        else if (first == '+'){
            int operand1 = eval();
            int operand2 = eval();
            ans = operand1 - operand2;
        }

        else if (first == '*'){
            int operand1 = eval();
            int operand2 = eval();
            ans = operand1 * operand2;
        }

        else if (Character.isDigit(first)) // self evalating
            return first - '0';

        else if (first == ')')
            return ans;

        return ans;
    }



    public static void main(String[] args){
        Q1 q1 = new Q1();
        q1.convert("(+23)");
        int r = q1.eval();
        System.out.println(r);
    }
}
