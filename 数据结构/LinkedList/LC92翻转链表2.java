package 数据结构.LinkedList;

public class LC92翻转链表2 {
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

    public ListNode reverseBetween(ListNode head, int left, int right) {
        // 虚拟节点
        ListNode dummy = new ListNode(-1, head);
        // 1.找分界: pre节点 left节点 right节点 succ节点，4个节点将链表分为三个部分
        ListNode preNode = dummy, leftNode = null, rightNode = null, succNode = null;
        for (int i = 1; i < left; i ++){
            preNode = preNode.next;
        }
        leftNode = preNode.next;
        rightNode = preNode;
        for (int i = 1; i <= right - left + 1; i ++){
            rightNode = rightNode.next;
        }
        succNode = rightNode.next;

        // 2.切断1、2      2、3之间的联系
        rightNode.next = null;
        preNode.next = null;

        // 3.翻转中间部分的链表
        reverse(leftNode);

        // 4.把三部分再粘回去
        preNode.next = rightNode;
        leftNode.next = succNode;
        return dummy.next;

    }

    public ListNode reverse(ListNode node){
        ListNode reversedNode = null;
        while (node != null){
            ListNode temp = node.next;
            node.next = reversedNode;
            reversedNode = node;
            node = temp;
        }
        return reversedNode;
    }


    // 方法2 原地 穿针引线
    public ListNode reverseBetween2(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(-1, head);
        ListNode preNode = dummy, curNode = null, nextNode = null;
        for (int i = 1; i < left; i ++){
            preNode = preNode.next;
        }
        curNode = preNode.next;
        nextNode = curNode.next;
        int k = right - left + 1;
        while (k > 0){
            curNode.next = nextNode.next;
            nextNode.next = preNode.next;
            preNode.next = nextNode;
            nextNode = curNode.next;
            k--;
        }
        return dummy.next;
    }
}
