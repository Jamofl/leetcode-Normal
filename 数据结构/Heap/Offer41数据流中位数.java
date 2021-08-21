/*
剑指 Offer 41. 数据流中的中位数
如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。

例如，

[2,3,4] 的中位数是 3

[2,3] 的中位数是 (2 + 3) / 2 = 2.5

设计一个支持以下两种操作的数据结构：

void addNum(int num) - 从数据流中添加一个整数到数据结构中。
double findMedian() - 返回目前所有元素的中位数。
示例 1：

输入：
["MedianFinder","addNum","addNum","findMedian","addNum","findMedian"]
[[],[1],[2],[],[3],[]]
输出：[null,null,null,1.50000,null,2.00000]
 */
package 数据结构.Heap;
import java.util.*;

public class Offer41数据流中位数 {

    // 方法1
    // 添加元素时，如果比大顶堆的堆顶元素大，就添加到小顶堆中；否则添加到大顶堆中。
    // 添加完毕后需要动态的维护两个堆，若一个堆的元素个数是另一个堆的元素个数 + 2，需要迁移元素。
    // 始终保持两个堆的元素个数相差1或相等
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    PriorityQueue<Integer> bigHeap = new PriorityQueue<>((Integer i, Integer j) -> j - i);
    public int size;
    /** initialize your data structure here. */
    public Offer41数据流中位数() {
        size = 0;
    }

    public void addNum(int num) {
        // add num
        int size1 = bigHeap.size();
        int size2 = minHeap.size();
        if (size1 == 0 && size2 == 0)
            bigHeap.offer(num);
        else{
            if (num > bigHeap.peek())
                minHeap.offer(num);
            else
                bigHeap.offer(num);
        }

        // dynamically maintain the two heaps
        size1 = bigHeap.size();
        size2 = minHeap.size();
        if (size1 == size2 + 2){
            int temp = bigHeap.poll();
            minHeap.offer(temp);
        }
        else if (size2 == size1 + 2){
            int temp = minHeap.poll();
            bigHeap.offer(temp);
        }
        size ++;
    }

    public double findMedian() {
        if (size % 2 == 0)
            return (double)(minHeap.peek() + bigHeap.peek()) / 2.0;
        else {
            if (bigHeap.size() > minHeap.size())
                return bigHeap.peek();
            else
                return minHeap.peek();
        }
    }



    // 方法2
    // 动态的维护两个堆中的元素，始终保持大顶堆中的元素都比中位数小；
    // 小顶堆中的元素都比中位数大；若出现元素个数相等的情况，永远向大顶堆中添加，保持大顶堆中多一个元素。

    public void addNum2(int num) {
        // 如果 两个堆中的元素个数相等，直接向bigHeap中添加元素（先向小顶堆中添加元素  然后将小顶堆中的元素移到大顶堆中）
        if (bigHeap.size() == minHeap.size()){
            minHeap.offer(num);
            bigHeap.offer(minHeap.poll());
        }
        // 不相等，即bigHeap 中元素多了1个。先向bigHeap加一个元素，再将bigheap中元素弹出一个加入到minHeap
        else{
            bigHeap.offer(num);
            minHeap.offer(bigHeap.poll());
        }
        size ++;
    }

    public double findMedian2() {
        if (size % 2 == 0)
            return (double)(minHeap.peek() + bigHeap.peek()) / 2.0;
        else{
            return bigHeap.peek();
        }
    }

    public static void main(String[] args){
        Offer41数据流中位数 o = new Offer41数据流中位数();
        o.addNum(-1);
        System.out.println(o.findMedian());
        o.addNum(-2);
        System.out.println(o.findMedian());
        o.addNum(-3);
        System.out.println(o.findMedian());

    }
}
