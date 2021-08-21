import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 23. 合并K个升序链表
 * 给你一个链表数组，每个链表都已经按升序排列。
 *
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。

 * 示例 1：
 *
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 */
public class Q23 {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) {
            this.val = val;
        }
        ListNode(int val, ListNode next) {
            this.val = val; this.next = next;
        }
    }


    /**
    // solution 1: 使用小顶堆，每次都弹出最小值。假设有k个链表，每个长为n，time O(kn log k),  space O(k)
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0)
            return null;

        ListNode re = new ListNode(-1);
        ListNode point = re;
        PriorityQueue<ListNode> pq = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode t1, ListNode t2) {
                return t1.val - t2.val;
            }
        });
        for(int i = 0; i < lists.length; i++){
            if(lists[i] != null)
                pq.add(lists[i]);
        }

        ListNode temp = new ListNode();
        while(pq.peek() != null){
            temp = pq.poll();
            point.next = new ListNode(temp.val, null);
            if(temp.next != null)
                pq.add(temp.next);
            point = point.next;
        }
        return re.next;
    }
     */
    public ListNode mergeKLists(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }

    private ListNode merge(ListNode[] lists, int start, int end){
        if(start == end)
            return lists[start];
        else if(start > end)
            return null;
        int middle = start + (end - start) / 2;
        return mergeTwoList(merge(lists, start, middle), merge(lists, middle + 1, end));
    }

    private ListNode mergeTwoList(ListNode l1, ListNode l2){
        ListNode re = new ListNode(-1);
        ListNode point = re;
        while(l1 != null && l2 != null){
            if(l1.val <= l2.val){
                point.next = new ListNode(l1.val);
                l1 = l1.next;
            }
            else{
                point.next = new ListNode(l2.val);
                l2 = l2.next;
            }
            point = point.next;
        }
        if(l1 != null)
            point.next = l1;
        else if(l2 != null)
            point.next = l2;
        return re.next;
    }


    public static void main(String[] args){
    Q23 q = new Q23();
    ListNode l1 = new ListNode(1, new ListNode(3, new ListNode(4, null)));
    ListNode l2 = new ListNode(2, new ListNode(3, new ListNode(5, null)));
    ListNode l3 = new ListNode(1, new ListNode(4, new ListNode(7, null)));
    ListNode[] lst = new ListNode[] {l1, l2, l3};
    ListNode re = q.mergeKLists(lst);
    System.out.println(1);

    Integer[] integers = new Integer[]{1,2,3};
    Arrays.asList(integers).toArray();
    }

}
