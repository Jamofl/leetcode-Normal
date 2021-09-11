package 数据结构.LinkedList;

/**
 * a -> b -> c -> d -> e -> f
 * a -> f -> b -> e -> c -> d
 *
 * 1.断开
 * 2.后半部分翻转
 * 3.合并
 */
public class 拆分翻转合并链表 {

    public static class ListNode{
        public int val;
        public ListNode next;

        public ListNode(int val, ListNode next){
            this.val = val;
            this.next = next;
        }
        public ListNode(int val){
            this.val = val;
            this.next = null;
        }
    }

    public static void main(String[] args) {
        ListNode lst = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode lst2 = listFunc(lst);

        while (lst2 != null){
            System.out.println(lst2.val);
            lst2 = lst2.next;
        }
    }

    // pre翻转的部分  cur待翻转的部分
    private  static ListNode reverse(ListNode pre, ListNode cur){
        if (cur == null)
            return pre;

        ListNode temp = cur.next;
        cur.next = pre;
        return reverse(cur, temp);
    }


    public static ListNode listFunc(ListNode list){
        if (list == null || list.next == null)
            return list;

        // 快慢指针找中点  偶数情况下： 中间偏左的节点  ；奇数情况下：正中间的节点
        ListNode slow = list;
        ListNode fast = list.next;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode firstPart = list;
        ListNode secondPart = slow.next;
        slow.next = null;

        // 翻转第二部分
        ListNode secondPartReversed = reverse(null, secondPart);

//        while (firstPart != null){
//            System.out.println(firstPart.val);
//        }
//
//        while (secondPartReversed != null){
//            System.out.println(secondPartReversed.val);
//        }

        // 合并
        ListNode dummyNode = new ListNode(-1);
        ListNode point = dummyNode;
        while (secondPartReversed != null){
            point.next = new ListNode(firstPart.val);
            point.next.next = new ListNode(secondPartReversed.val);
            point = point.next.next;
            firstPart = firstPart.next;
            secondPartReversed = secondPartReversed.next;
        }
        // 奇数的情况  firstPart会多一个节点
        if (firstPart != null)
            point.next = new ListNode(firstPart.val);
        return dummyNode.next;
    }
}

