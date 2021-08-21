package 数据结构;
/*
155. 最小栈
设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。

push(x) —— 将元素 x 推入栈中。
pop() —— 删除栈顶的元素。
top() —— 获取栈顶元素。
getMin() —— 检索栈中的最小元素。


示例:

输入：
["MinStack","push","push","push","getMin","pop","top","getMin"]
[[],[-2],[0],[-3],[],[],[],[]]

输出：
[null,null,null,null,-3,null,0,-2]

解释：
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> 返回 -3.
minStack.pop();
minStack.top();      --> 返回 0.
minStack.getMin();   --> 返回 -2.


提示：

pop、top 和 getMin 操作总是在 非空栈 上调用。

 */
class MinStack {

    private class Node{
        public int value;
        public Node next;
        public Node pre;

        public Node(int value, Node next, Node pre){
            this.value = value;
            this.next = next;
            this.pre = pre;
        }
        public Node(int value){
            this.value = value;
        }

        public Node(){

        }
    }

    public int minValue;
    public Node top;
    public Node root;
    /** initialize your data structure here. */
    public MinStack() {
        minValue = Integer.MAX_VALUE;
        root = new Node();
        top = root;
    }

    public void push(int x) {
        Node tempNext = new Node(x);
        Node tempPre = top;
        top.next = tempNext;
        top = top.next;
        top.pre = tempPre;

        if (x < minValue){
            minValue = x;
        }
    }

    public void pop() {
        Node toPop = top;
        top = top.pre;
        top.next = null;
        if (toPop.value == minValue){
            minValue = Integer.MAX_VALUE;
            Node temp = top;
            while(temp != root){
                if (temp.value < minValue)
                    minValue = temp.value;
                temp = temp.pre;
            }
        }
    }

    public int top() {
        return top.value;
    }

    public int getMin() {
        return minValue;
    }

    public static void main(String[] args){
//        MinStack minstack = new MinStack();
//        minstack.push(-2);
//        minstack.push(0);
//        minstack.push(4);
//        System.out.println(minstack.getMin());
//        minstack.pop();
//        System.out.println(minstack.getMin());
//        System.out.println(minstack.top());
        System.out.println( (double) 3 / 2);
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
