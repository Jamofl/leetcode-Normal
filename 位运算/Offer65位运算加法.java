package 位运算;
/*
https://leetcode-cn.com/problems/bu-yong-jia-jian-cheng-chu-zuo-jia-fa-lcof/
剑指 Offer 65. 不用加减乘除做加法
写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。



示例:

输入: a = 1, b = 1
输出: 2


提示：

a, b 均可能是负数或 0
结果不会溢出 32 位整数
 */
public class Offer65位运算加法 {

    public static void main(String[] args){
        int r = add(3,4);
        System.out.println(r);
    }
    public static  int add(int a, int b) {
        if (b == 0)
            return a;
        else{
            int temp = a;
            a = a ^ b;
            b = (temp & b) << 1;
            return add(a, b);
        }
    }
}
