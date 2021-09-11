/**
 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。

 进阶：

 你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 */

class Q148链表排序 {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x){
            val = x;
        }

        ListNode(int val, ListNode next){
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 【方法1】 递归 自顶向下 归并排序
     * 通过递归实现链表归并排序，有以下两个环节：
     *
     * 分割 cut 环节： 找到当前链表中点，并从中点将链表断开（以便在下次递归 cut 时，链表片段拥有正确边界）；
         * 我们使用 fast,slow 快慢双指针法，奇数个节点找到中点，偶数个节点找到中心左边的节点。
         * 找到中点 slow 后，执行 slow.next = None 将链表切断。
         * 递归分割时，输入当前链表左端点 head 和中心节点 slow 的下一个节点 tmp(因为链表是从 slow 切断的)。
         * cut 递归终止条件： 当head.next == None时，说明只有一个节点了，直接返回此节点。
     * 合并 merge 环节： 将两个排序链表合并，转化为一个排序链表。
         * 双指针法合并，建立辅助ListNode h 作为头部。
         * 设置两指针 left, right 分别指向两链表头部，比较两指针处节点值大小，由小到大加入合并链表头部，指针交替前进，直至添加完两个链表。
         * 时间复杂度 O(l + r)，l, r 分别代表两个链表长度。
     *
     * 作者：jyd
     * 链接：https://leetcode-cn.com/problems/sort-list/solution/sort-list-gui-bing-pai-xu-lian-biao-by-jyd/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    private ListNode mergeList(ListNode left, ListNode right){
        ListNode h = new ListNode(0);
        ListNode res = h;
        while (left != null && right != null) {
            if (left.val < right.val) {
                h.next = left;
                left = left.next;
            } else {
                h.next = right;
                right = right.next;
            }
            h = h.next;
        }
        h.next = left != null ? left : right;
        return res.next;
    }

    public ListNode sortList(ListNode head) {
        // base case就是链表为空 或者仅有一个节点
        if (head == null || head.next == null)
            return head;

        // 使用快慢指针 找到链表的中间节点即为slow节点
        // 要注意 奇数节点为中间节点 偶数节点为中间节点的左节点
        ListNode fast = head.next, slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // slow节点为中间节点  slow.next为中间节点的下一跳节点
        ListNode tmp = slow.next;
        slow.next = null;
        // 分别对左半部分和右半部分进行排序 ，然年合并
        ListNode left = sortList(head);
        ListNode right = sortList(tmp);
        ListNode mergedList = mergeList(left, right);
        return mergedList;
    }

    /**
     * 【方法2】 快排
     * 我们只需要两个指针p1和p2，p1只接受比pivot小的，p2只接受比pivot大的
     * 这两个指针均往next方向移动。
     * 移动的过程中保持p1之前的key都小于选定的key， p1和p2之间的key都大于选定的key，那么当p2走到末尾时交换p1与key值便完成了一次切分。
     * @param head
     * @return
     */
    public ListNode sortList2(ListNode head) {
        quickSort(head, null);
        return head;
    }

    public void quickSort(ListNode head, ListNode tail){
        if (head == tail)
            return ;
        // in-place 排序 不需要返回值
        ListNode pivot = sortAndFindMiddle(head, tail);
        quickSort(head, pivot);
        quickSort(pivot.next, tail);
    }

    public ListNode sortAndFindMiddle(ListNode head, ListNode tail){
        ListNode pivot = head;
        ListNode p1 = head; // p1只接受比pivot小的元素
        ListNode p2 = head.next;
        while (p2 != tail){
            if (p2.val < pivot.val){
                // 在最开始时，p1指向pivot p2指向pivot.next 如果p2遇到的元素都 < pivot，则p1 p2会一直同步向后移
                // 直到p2遇到第一个 > pivot的元素，p2直接后移，留下p1在原地。
                // 当p2再次遇到 < pivot的元素时，p1先向后移动一位 移动到的那个元素一定是比pivot大的元素 即之前p2留下的元素，再和p2交换位置
                p1 = p1.next;
                swap(p1, p2);
            }
            p2 = p2.next;
        }
        swap(p1, pivot);
        return p1;
    }


    private static void swap(ListNode l1, ListNode l2){
        if (l1.val == l2.val)
            return;
        int temp = l1.val;
        l1.val = l2.val;
        l2.val = temp;
    }

    /*
    // 融合两个有序链表
    // 初版写法 较复杂
    private ListNode  mergeList(ListNode l1, ListNode l2){
        // 链表头部的哑结点
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
    // 根据快慢指针 找到链表中间的节点
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
     */

    public static void main(String[] args){
        Q148链表排序 q148链表排序 = new Q148链表排序();

        ListNode lst = new ListNode(4, new ListNode(1, new ListNode(2)));
//        ListNode lst2 = new ListNode(1);
//        lst2.next = new ListNode(2);
//        ListNode mergeList = q148.mergeList(lst, lst2);
        ListNode sorted = q148链表排序.sortList(lst);
        System.out.println(4);
    }
}
