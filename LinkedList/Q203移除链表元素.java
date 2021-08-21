/*
203. 移除链表元素
删除链表中等于给定值 val 的所有节点。

示例:

输入: 1->2->6->3->4->5->6, val = 6
输出: 1->2->3->4->5
 */
package LinkedList;

public class Q203移除链表元素 {
    private class ListNode{
        int val;
        ListNode next;
        public ListNode(int val){
            this.val = val;
            this.next = null;
        }
    }

    // 使用虚拟头结点，删除更方便
    // https://mp.weixin.qq.com/s/slM1CH5Ew9XzK93YOQYSjA
    public ListNode removeElements(ListNode head, int target) {
        if (head == null)
            return head;
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode point = dummyNode;
        while (point.next != null){
            if (point.next.val == target){
                point.next = point.next.next;
            }
            else
                point = point.next;
        }
        return dummyNode.next;
    }
}
