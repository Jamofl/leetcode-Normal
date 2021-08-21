import javax.management.ListenerNotFoundException;
import java.util.concurrent.atomic.AtomicReference;

/**
 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。

 进阶：

 你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 */

class Q148 {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x){
            val = x;
        }
    }

    private ListNode  mergeList(ListNode l1, ListNode l2){
        ListNode mergedList = new ListNode(-1);
        ListNode point = mergedList;
        int temp1;
        int temp2;
        while (l1 != null && l2 != null){
            temp1 = l1.val;
            temp2 = l2.val;
            if (temp1 <= temp2){
                point.next = new ListNode(temp1);
                l1 = l1.next;
            }
            else{
                point.next = new ListNode(temp2);
                l2 = l2.next;
            }
            point = point.next;

        }
        point.next = (l1 == null)? l2 : l1;
        return mergedList.next;

    }

    // return middle right node of a lst
    private ListNode findMiddleNode(ListNode head, ListNode tail){
        ListNode slow = head;
        ListNode quick = head;
        while(quick != tail){
            slow = slow.next;
            quick = quick.next;
            if(quick != tail)
                quick = quick.next;
        }
        return slow;
    }

    private ListNode sortList(ListNode head, ListNode tail){
        if (head == null)
            return head;
        else if (head.next == tail){
            head.next = null;
            return head;
        }

        ListNode middle = findMiddleNode(head, tail);
        ListNode left = sortList(head, middle);
        ListNode right = sortList(middle, tail);

        return mergeList(left, right);
    }

    public ListNode sortList(ListNode head) {
        return sortList(head, null);
    }

    public static void main(String[] args){
        Q148 q148 = new Q148();

        ListNode lst = new ListNode(4);
        lst.next = new ListNode(3);
        lst.next.next = new ListNode(1);
        lst.next.next.next = new ListNode(2);


//        ListNode lst2 = new ListNode(1);
//        lst2.next = new ListNode(2);
//        ListNode mergeList = q148.mergeList(lst, lst2);
        ListNode sorted = q148.sortList(lst);
        System.out.println(4);
    }
}
