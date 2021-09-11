package 数据结构.LinkedList;
import java.util.*;

public class Q23合并K个升序链表 {
    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }

        ListNode(int x, ListNode next){
            this.val = x;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        //System.out.println("Hello World!");
        ListNode node11 = new ListNode(1);
        ListNode node12 = new ListNode(4);
        ListNode node13 = new ListNode(5);
        node11.next = node12;
        node12.next = node13;
        ListNode node21 = new ListNode(1);
        ListNode node22 = new ListNode(3);
        ListNode node23 = new ListNode(4);
        node21.next = node22;
        node22.next = node23;
        ListNode node31 = new ListNode(2);
        ListNode node32 = new ListNode(6);
        node31.next = node32;
        //ListNode[] lists = new ListNode[]{node11, node21, node31};
        List<ListNode> inputList = new ArrayList<>();
        inputList.add(node11);
        inputList.add(node21);
        inputList.add(node31);

        ListNode head = mergeKLists(inputList);
        ListNode cur = head;
        while (cur != null) {
            System.out.println(cur.val);
            cur = cur.next;
        }
    }


    public static ListNode mergeKLists(List<ListNode> inputList){
        // 小顶堆pq
        PriorityQueue<ListNode> pq = new PriorityQueue<>((ListNode l1, ListNode l2) -> (l1.val - l2.val));

//        Comparator<ListNode> c = new Comparator<>() {
//            @Override
//            public int compare(ListNode l1, ListNode l2) {
//                return l1.val - l2.val;
//            }
//        };
//
//        PriorityQueue<ListNode> pq = new PriorityQueue<>(c);


        for (ListNode list : inputList){
            if (list != null)
                pq.offer(list);
        }

        ListNode ans = new ListNode(-1); // dummy node
        ListNode cur = ans;
        while (pq.size() != 0){
            ListNode poppedList = pq.poll();
            cur.next = new ListNode(poppedList.val, null);
            cur = cur.next;
            if (poppedList.next != null)
                pq.offer(poppedList.next);
        }
        return ans.next;
    }
}
