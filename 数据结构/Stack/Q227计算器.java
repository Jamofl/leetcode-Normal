/*
227. 基本计算器 II
实现一个基本的计算器来计算一个简单的字符串表达式的值。

字符串表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格  。 整数除法仅保留整数部分。

示例 1:

输入: "3+2*2"
输出: 7
示例 2:

输入: " 3/2 "
输出: 1
示例 3:

输入: " 3+5 / 2 "
输出: 5
说明：

你可以假设所给定的表达式都是有效的。
请不要使用内置的库函数 eval。
 */
package 数据结构.Stack;
import java.util.*;
public class Q227计算器 {
    /*
    Stack<String> stack = new Stack();

    public int calculate(String s) {
        String sWithoutSpace = "";
        for(char c : s.toCharArray()){
            if (c != ' ')
                sWithoutSpace += c;
        }

        stack = putExpIntoStack(sWithoutSpace);
        return (int)eval();
    }


    private Stack<String> putExpIntoStack(String s){
        Stack<String> stack = new Stack<>();
        String[] nums = s.split("\\+|-|\\*|/");
        s = s.replaceAll("\\d+", "");
        char[] signs = s.toCharArray();
        for(int k = 0; k < signs.length; k ++){
            if (signs[k] == '-'){
                signs[k] = '+';
                nums[k + 1] = "-" + nums[k + 1];
            }
            else if (signs[k] == '/'){
                signs[k] = '*';
                nums[k + 1] = String.valueOf((double)1 / Double.parseDouble(nums[k + 1]));
            }
        }


        boolean flag = true;
        int i = nums.length - 1; int j = signs.length - 1;
        while (i >= 0){
            if (flag){
                stack.push(nums[i]);
                i --;
            }
            else{
                stack.push(String.valueOf(signs[j]));
                j--;
            }
            flag = !flag;
        }
        return stack;
    }

    private int cmp(String op1, String op2){
        if ((op1.equals("+") || op1.equals("-")) && (op2.equals("+") || op2.equals("-")))
            return 0;
        else if ((op1.equals("*") || op1.equals("/")) && (op2.equals("*") || op2.equals("/")))
            return 0;
        else if ((op1.equals("+") || op1.equals("-")) && (op2.equals("*") || op2.equals("/")))
            return -1;
        else //if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
            return 1;
    }

    private double baseEval(String operator, double operand1, double operand2) {
        if (operator.equals("+"))
            return operand1 + operand2;

        else //if (operator.equals("*")){
            return operand1 * operand2;
//        else if (operator.equals("-")){
//            return operand1 - operand2;
//        }


//        }

        //else //if (operator == '/')
          //  return operand1 / operand2;
//        else
//            throw new Exception();

    }

    private double eval(){
        if (stack.size() == 1)
            return Double.parseDouble(stack.pop());

        double operand1 = Double.parseDouble(stack.pop());
        String operator1 = stack.pop();
        double operand2 = Double.parseDouble(stack.pop());
        if (stack.size() == 0)
            return baseEval(operator1, operand1, operand2);

        String operator2 = stack.pop();
        if (cmp(operator1, operator2) >= 0){
            operand1 = baseEval(operator1, operand1, operand2);
            return baseEval(operator2, operand1, eval());
        }
        else if (cmp(operator1, operator2) == -1){
            stack.push(operator2);
            stack.push(String.valueOf(operand2));
            return baseEval(operator1, operand1, eval());
        }
        return -1;
    }

    */

    /*
    Solution 2: 参考解法: 先乘除，后加减。遇 '*' '/', 将栈中元素弹出一个，计算后在放回。
    最后将栈中元素依次弹出相加。
     */
    public int calculate(String s) {
        s = s.trim();
        return core(s.toCharArray());
    }
    private int i = 0;
    private int core(char[] chs){
        Stack<Integer> stack = new Stack<>();
        int num = 0;
        char operation = '+';
        for (; i < chs.length; i++) {
            if(chs[i]==' '){
                continue;
            }
            if (Character.isDigit(chs[i])) {
                num = num * 10 + (chs[i] - '0');
            }
            if (!Character.isDigit(chs[i]) || i >= chs.length - 1) {
                if (operation == '+') {
                    stack.push(num);
                } else if (operation == '-') {
                    stack.push(-num);
                } else if (operation == '*') {
                    int a = stack.pop();
                    stack.push(a * num);
                } else if (operation == '/') {
                    int a = stack.pop();
                    stack.push(a / num);
                }
//                if(i>=chs.length-1){
//                    break;
//                }
                operation = chs[i];
                num = 0;
            }
        }
        int sum = 0;
        while (!stack.isEmpty()) {
            sum += stack.pop();
        }
        return sum;
    }

    public static void main(String[] args){
        Q227计算器 q = new Q227计算器();
        int r = q.calculate("13-7*10+1"); // 会变成1000 * 0.5 * (int)(0.333 * 0.25)
        String s = "df a bn ";
        System.out.println(s.replaceAll(" ", ""));
    }
}
