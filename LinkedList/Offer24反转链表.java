/*
剑指 Offer 24. 反转链表
定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。

示例:

输入: 1->2->3->4->5->NULL
输出: 5->4->3->2->1->NULL

限制：

0 <= 节点个数 <= 5000
 */
package LinkedList;

public class Offer24反转链表 {
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    // 迭代法  维护一个cur 一个head   让hea.next = cur
    public ListNode reverseList(ListNode head) {
        ListNode cur = null;
        while (head != null){
            ListNode temp = head.next;
            head.next = cur;
            cur = head;
            head = temp;
        }
        return cur;
    }


    // 递归法  假设head.next已经全部翻转完毕   让head.next.next 指向head本身，并让head.next = null
    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode temp = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return temp;
    }



    // 方法3 递归法 ： 迭代法的变形  更容易理解
    public ListNode reverseList3(ListNode head) {
        return reverse(null, head);
    }
    private ListNode reverse(ListNode pre, ListNode cur){
        if (cur == null)
            return pre;

        ListNode temp = cur.next;
        cur.next = pre;
        return reverse(cur, temp);
    }

}
