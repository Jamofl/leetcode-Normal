package ByteDance;

public class OddIncEvenDec {

    public static class ListNode{

        int val;
        ListNode next;
        public ListNode(int val, ListNode next){
            this.val = val;
            this.next = next;
        }

        public ListNode(int val){
            this.val = val;
        }
    }

    public ListNode ordered(ListNode lst){
        ListNode oddList = new ListNode(-1);
        ListNode evenList = new ListNode(-2);
        ListNode p1 = oddList;
        ListNode p2 = evenList;
        ListNode point = lst;
        int flag = 1;
        while(point != null) {
            if (flag % 2 == 1) {
                p1.next = new ListNode(point.val);
                p1 = p1.next;
            } else {
                p2.next = new ListNode(point.val);
                p2 = p2.next;
            }
            point = point.next;
            flag++;
        }
        oddList = oddList.next;
        evenList = evenList.next;
        evenList = reverse(evenList);
        return merger(oddList, evenList);

    }

    //reverse list node lst
    private ListNode reverse(ListNode lst){
        if(lst == null)
            return null;
        if(lst.next == null)
            return lst;
        ListNode pre = null;
        ListNode cur = lst;
        ListNode temp;
        while(cur != null){
            temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }


    // merger l1 and l2 together
    private ListNode merger(ListNode l1, ListNode l2){
        ListNode point1 = l1;
        ListNode point2 = l2;
        ListNode re = new ListNode(-1);
        ListNode point = re;
        while (point1 != null && point2 != null){
            if(point1.val < point2.val){
                point.next = new ListNode(point1.val);
                point1 = point1.next;
            }
            else{
                point.next = new ListNode(point2.val);
                point2 = point2.next;
            }
            point = point.next;
        }
        if (point1 != null)
            point.next = point1;
        if (point2 != null)
            point.next = point2;
        return re.next;
    }

    public static void main(String[] args){
        OddIncEvenDec o = new OddIncEvenDec();
        ListNode lst = new ListNode(1, new ListNode(6, new ListNode(3, new ListNode(4, null))));
        ListNode lst2 = new ListNode(2, new ListNode(3, new ListNode(4, null)));

        ListNode re = o.ordered(lst);
        System.out.println();

    }
}
