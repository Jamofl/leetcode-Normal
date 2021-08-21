package 数据结构.Stack;
import java.util.*;
public class Q224calculator {

    // Solution 1: 通过栈匹配对应的右括号，将括号看成一个数，递归调用解决括号内的计算；
    // 通过将所有元素出栈累加得到最终结果。匹配括号的过程过于耗时间。

    public int calculate(String s) {
        s = s.trim();
        s = s.replaceAll(" ", "");
        char[] arr = s.toCharArray();
        return eval(arr, 0, arr.length - 1);
    }

    private int indexOfRightBrackets(int i, int end, char[] arr){
        int j = end;
        Stack<Character> signStack = new Stack();
        for(int k = i; k < end; k ++){
            if (arr[k] == '('){
                signStack.push(arr[k]);
            }
            else if (arr[k] == ')'){ // 找到对应的匹配的右括号
                if (signStack.size() == 1 && signStack.peek() == '('){
                    j = k;
                    break;
                }
                else
                    signStack.pop();
            }
        }
        return j;

    }
    private int eval(char[] arr, int start, int end){
        Stack<Integer> stack = new Stack();
        char operation = '+';
        int num = 0;
        int i = start;
        while (i <= end){
            if (arr[i] == '('){
                int j = indexOfRightBrackets(i, end, arr);
                int temp = eval(arr, i + 1, j - 1);
                if (operation == '+')
                    stack.push(temp);
                else if (operation == '-')
                    stack.push(- temp);
                i = j + 1;
                continue;
            }
            else if (Character.isDigit(arr[i]))
                num = num * 10 + arr[i] - '0';

            if (i == end || arr[i] == '+' || arr[i] == '-' ){
                if (operation == '+')
                    stack.push(num);
                else if (operation == '-')
                    stack.push(- num);
                operation = arr[i];
                num = 0;
            }
            i ++;
        }

        int sum = 0;
        while(stack.size() != 0)
            sum += stack.pop();
        return sum;
    }



    // Solution 2: 类似的思想，但是使用全局变量i，当对子序列进行递归计算时，i的值得到了增加。
    public int i = 0; // i要设置为全局变量！！！非常重要
    public int calculate2(String s) {
        //s = s.replaceAll(" ", ""); //去掉空格
        return helper(s.toCharArray());
    }

    private int helper(char[] arr){
        Stack<Integer> stack = new Stack();
        char sign = '+'; //记录num前的符号，默认为+
        char c;
        int num = 0; //初始化呢一个num为0
        for(; i < arr.length; i ++){ // for 循环的初始条件默认不填，即为当前的i值！！！
            c = arr[i];
            if (Character.isDigit(c))
                num = num * 10 + (c - '0');

            else if (c == '('){ // 对‘（’的判断一定要在下一种情况的上方
                i ++;  // i ++, 跳过当前的‘(’
                num = helper(arr);
            }


            if ((!Character.isDigit(c) && c != ' ') || i == arr.length - 1){ // 不可以用else if
                if (sign == '+')
                    stack.push(num);
                else if (sign == '-')
                    stack.push(-num);
                else if (sign == '*'){
                    int temp = stack.pop();
                    stack.push(temp * num);
                }
                else if (sign == '/'){
                    int temp = stack.pop();
                    stack.push(temp / num);
                }
                num = 0;
                sign = c;
            }
            if (c == ')') // 对‘）’的判断一定要在上面判断的下方
                break;
        }
        int sum = 0;
        while (stack.size() != 0)
            sum += stack.pop();
        return sum;
    }

    public static void main(String[] args){
        Q224calculator q = new Q224calculator();
        int r = q.calculate("1+(1-3)+8");
        System.out.println(r);
    }
}
