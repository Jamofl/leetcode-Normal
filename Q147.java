/**
 * 插入排序的动画演示如上。从第一个元素开始，该链表可以被认为已经部分排序（用黑色表示）。
 * 每次迭代时，从输入数据中移除一个元素（用红色表示），并原地将其插入到已排好序的链表中。
 *
 * 插入排序算法：
 *
 * 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
 * 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
 * 重复直到所有输入数据插入完为止。
 */


import java.lang.ref.SoftReference;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

public class Q147 {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x){
            val = x;
        }
    }

    public ListNode insertionSortList(ListNode head) {
        if (head == null)
            return  head;
        ListNode sorted = new ListNode(head.val);
        return insertionSort(sorted, head.next, sorted);
    }

    private ListNode insertionSort(ListNode sorted, ListNode unsorted, ListNode last){
        if(unsorted == null)
            return sorted;

        int pivot = unsorted.val;
        unsorted = unsorted.next;
        ListNode point = sorted;
        while (true){
            if(pivot > last.val){
                last.next = new ListNode(pivot);
                last = last.next;
                break;
            }
            if(pivot > point.val){
                point = point.next;
            }
            else{
                ListNode tempNode = new ListNode(point.val);
                tempNode.next = point.next;
                point.val = pivot;
                point.next = tempNode;
                if(last.equals(point))
                    last = last.next;
                break;
            }
        }
        return insertionSort(sorted, unsorted, last);
    }

    public static void main(String[] args){
        ListNode lst = new ListNode(4);
        lst.next = new ListNode(3);
        lst.next.next = new ListNode(1);
        lst.next.next.next = new ListNode(2);

        Q147 q147 = new Q147();
        ListNode sorted = q147.insertionSortList(lst);
        System.out.println(4);
    }
}
