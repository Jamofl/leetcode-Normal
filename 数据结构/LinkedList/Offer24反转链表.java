/*
剑指 Offer 24. 反转链表
定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。

示例:

输入: 1->2->3->4->5->NULL
输出: 5->4->3->2->1->NULL

限制：

0 <= 节点个数 <= 5000
 */
package 数据结构.LinkedList;

public class Offer24反转链表 {
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
        ListNode(int x, ListNode next) {
            val = x;
            next = next;
        }
    }

    // 方法1 迭代法
    // 维护一个cur 一个head   让head.next = cur
    public ListNode reverseList(ListNode head) {
        ListNode reverseHead = null;
        while (head != null){
            ListNode temp = head.next;
            head.next = reverseHead;
            reverseHead = head;
            head = temp;
        }
        return reverseHead;
    }

    // 方法2 迭代构造法
    // 反向构造 for循环遍历链表，通过new ListNode(val, next)来指定当前的val和下一个节点的值
    public ListNode reverseList4(ListNode head) {
        ListNode ans = null;
        for (; head != null; head = head.next){
            ans = new ListNode(head.val, ans);
        }
        return ans;
    }



    // 方法3 递归法 (不太好理解)
    // 假设head.next已经全部翻转完毕
    // 让head.next.next 指向head本身，并让head.next = null
    public ListNode reverseListRec(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode temp = reverseListRec(head.next);
        head.next.next = head;
        head.next = null;
        return temp;
    }



    // 方法4 递归法模拟法（好理解）
    // 迭代法的变形  更容易理解
    public ListNode reverseList3(ListNode head) {
        return reverse(null, head);
    }

    /**
     * reverseHead 已经翻转的部分
     * head        尚未翻转的部分
     * @param reverseHead
     * @param head
     * @return
     */
    private ListNode reverse(ListNode reverseHead, ListNode head){
        // 尚未翻转的部分==null 则翻转完毕 返回已经翻转的部分
        if (head == null)
            return reverseHead;

        ListNode temp = head.next;
        head.next = reverseHead;
        return reverse(head, temp);
    }


}
