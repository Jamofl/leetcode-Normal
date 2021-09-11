package 数据结构.LinkedList;
/*
剑指 Offer 22. 链表中倒数第k个节点
输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。

例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。这个链表的倒数第 3 个节点是值为 4 的节点。



示例：

给定一个链表: 1->2->3->4->5, 和 k = 2.

返回链表 4->5.
 */
import java.util.*;
public class OFFER22链表中倒数第k个节点 {
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }


    // Solution 1:  O(N + K) 使用一个栈保存已经遍历过所有节点，  从栈中依次向外弹出k个元素即可。
    public ListNode getKthFromEnd(ListNode head, int k) {
        Stack<ListNode> stack = new Stack<>();
        while (head != null){
            stack.push(head);
            head = head.next;
        }

        ListNode re = stack.peek();
        while (k > 0){
            re = stack.pop();
            k --;
        }
        return re;
    }

    // Solution 2: O(N) 双指针l, r 。  l指向头节点，r指向l向右的k个节点，即 l 和 r 相距k个节点的距离。
    // 不断向右移动l和r, 当 r 指向null的时候，l指向的就是倒数第 k 个节点。
    public ListNode getKthFromEnd2(ListNode head, int k) {
        ListNode l = head;
        ListNode r = head;
        int count = 0;
        while (count < k){
            r = r.next;
            count ++;
        }

        while (r != null){
            l = l.next;
            r = r.next;
        }
        return l;
    }

}
