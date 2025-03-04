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

/**
 *  动态维护一个大顶堆和一个小顶堆，前者的最大元素始终小于后者的最小元素
 *      添加元素: 大顶堆中的元素个数为m，小顶堆中的元素个数为n，始终保持m == n 或 m == n + 1;
 *                  若两边元素一样多，则向大顶堆添加；若大顶堆元素多，则向小顶堆添加
 *      寻找中位数: 若偶数个元素->大顶堆堆顶元素; 若奇数个元素->两个堆堆顶元素取平均
 */
public class Offer41数据流中位数 {

    private PriorityQueue<Integer> bigHeap;
    private PriorityQueue<Integer> smallHeap;

    public Offer41数据流中位数() {
        bigHeap = new PriorityQueue<>((x, y) -> y - x); // 大顶堆
        smallHeap = new PriorityQueue<>(); // 小顶堆
    }

    public void addNum(int num) {
        // 两边元素相等 向大顶堆中添加元素（先加到小顶堆 然后弹出堆顶元素加入到大顶堆 可以保证大顶堆中元素都小于小顶堆）
        if (bigHeap.size() == smallHeap.size()){
            smallHeap.offer(num);
            bigHeap.offer(smallHeap.poll());
        }
        // 大顶堆中元素多一个 向小顶堆中添加元素（先加到大顶堆 然后弹出堆顶元素加入到小顶堆）
        else{
            bigHeap.offer(num);
            smallHeap.offer(bigHeap.poll());
        }
    }

    public double findMedian() {
        if (bigHeap.size() == smallHeap.size()){
            return ((double)(bigHeap.peek()) + (double)(smallHeap.peek())) / 2.0;
        }
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
