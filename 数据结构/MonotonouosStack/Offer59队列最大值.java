package 数据结构.MonotonouosStack;
/*
剑指 Offer 59 - II. 队列的最大值
请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。

若队列为空，pop_front 和 max_value 需要返回 -1

示例 1：

输入:
["MaxQueue","push_back","push_back","max_value","pop_front","max_value"]
[[],[1],[2],[],[],[]]
输出: [null,null,null,2,1,2]
示例 2：

输入:
["MaxQueue","pop_front","max_value"]
[[],[],[]]
输出: [null,-1,-1]


限制：

1 <= push_back,pop_front,max_value的总操作数 <= 10000
1 <= value <= 10^5
 */
import java.util.*;

public class Offer59队列最大值 {
    Deque<Integer> monoQueue;
    Queue<Integer> q;
    public Offer59队列最大值() {
        monoQueue = new LinkedList<>();
        q = new LinkedList<>();
    }

    public int max_value() {
        if (q.size() == 0)
            return -1;
        return monoQueue.peekFirst();
    }

    public void push_back(int value) {
        q.offer(value);
        offer(monoQueue, value);
    }

    public int pop_front() {
        if (q.size() == 0)
            return -1;
        int toReturn = q.poll();
        if (toReturn == monoQueue.peekFirst())
            monoQueue.pollFirst();
        return toReturn;
    }

    private void offer(Deque<Integer> monoQueue, int n){
        while (monoQueue.size() != 0 && monoQueue.peekLast() < n)
            monoQueue.pollLast();
        monoQueue.offerLast(n);
    }

    public static void main(String[] args){
        Offer59队列最大值 q = new Offer59队列最大值();
        q.push_back(1);
        q.push_back(2);
        System.out.println(q.max_value());
        q.pop_front();
        System.out.println(q.max_value());
    }
}
