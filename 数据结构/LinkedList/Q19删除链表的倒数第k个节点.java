package 数据结构.LinkedList;

/*
19. 删除链表的倒数第 N 个结点
给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。

进阶：你能尝试使用一趟扫描实现吗？
示例 1：


输入：head = [1,2,3,4,5], n = 2
输出：[1,2,3,5]
示例 2：

输入：head = [1], n = 1
输出：[]
示例 3：

输入：head = [1,2], n = 1
输出：[1]


提示：

链表中结点的数目为 sz
1 <= sz <= 30
0 <= Node.val <= 100
1 <= n <= sz
 */
public class Q19删除链表的倒数第k个节点 {
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            this.val = x;
            this.next = null;
        }

        ListNode(int x, ListNode next){
            this.val = x;
            this.next = next;
        }
    }

    // 双指针一遍扫描
    // 1.找到倒数第k + 1个节点point 2.point.next = point.next.next;
    // 建立哨兵节点指向头结点，初始时l r双指针均指向哨兵节点，让l向右移动 n + 1个距离，即此时 l  r 相距 k + 1
    // 当r节点指向null时， l节点指向倒数第k个节点的前一个节点
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode sentinel = new ListNode(-1, head);
        ListNode l = sentinel;
        ListNode r = sentinel;

        int count = 0;
        while (count < n + 1){
            r = r.next;
            count ++;
        }

        while (r != null){
            l = l.next;
            r = r.next;
        }
        l.next = l.next.next;
        return sentinel.next;
    }
}
