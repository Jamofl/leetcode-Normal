/*
295. 数据流的中位数
中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。

例如，

[2,3,4] 的中位数是 3

[2,3] 的中位数是 (2 + 3) / 2 = 2.5

设计一个支持以下两种操作的数据结构：

void addNum(int num) - 从数据流中添加一个整数到数据结构中。
double findMedian() - 返回目前所有元素的中位数。
示例：

addNum(1)
addNum(2)
findMedian() -> 1.5
addNum(3)
findMedian() -> 2
进阶:

如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？
 */

package 数据结构;
import java.util.*;


    // 使用一个大顶堆，一个小顶堆
public class MedianFinder {
    private PriorityQueue<Integer> maxQueue;
    private PriorityQueue<Integer> minQueue;
    private int count;
    /** initialize your data structure here. */
    public MedianFinder() {
        minQueue = new PriorityQueue<>();
        maxQueue = new PriorityQueue<>((x, y) -> y - x);
        count = 0;
    }

    public void addNum(int num) {
        if (count % 2 == 0){
            maxQueue.add(num);
            minQueue.add(maxQueue.poll());
            maxQueue.add(minQueue.poll());
        }
        else{
            maxQueue.add(num);
            minQueue.add(maxQueue.poll());
        }
        count ++;
    }

    public double findMedian() {
        if (count % 2 == 0){
            return ((double)maxQueue.peek() + minQueue.peek()) / 2;
        }
        else{
            return (maxQueue.peek());
        }
    }

    public static void main(String[] args){
        MedianFinder m = new MedianFinder();
        m.addNum(1);
        m.addNum(2);
        System.out.println(m.findMedian());
        m.addNum(3);
        System.out.println(m.findMedian());
    }
}
