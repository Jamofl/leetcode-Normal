/*
160. 相交链表
编写一个程序，找到两个单链表相交的起始节点。

如下面的两个链表：

在节点 c1 开始相交。

示例 1：

输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
输出：Reference of the node with value = 8
输入解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
 */
package 数据结构.LinkedList;

public class Q160相交链表判断交点 {

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


    // 官方解法
    // 创建两个指针 pApA 和 pBpB，分别初始化为链表 A 和 B 的头结点。然后让它们向后逐结点遍历。
    // 当 pA 到达链表的尾部时，将它重定位到链表 B 的头结点 (你没看错，就是链表 B);
    // 类似的，当 pB 到达链表的尾部时，将它重定位到链表 A 的头结点。
    // 若在某一时刻 pA 和 pB 相遇，则 pA/pB 为相交结点。
    // 【因为如果最终能相遇 他们走过的距离是相等的】

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode pointA = headA;
        ListNode pointB = headB;
        while (pointB != pointA){ // 若有交点，则在交点处相遇；若无交点，则在null处相遇 返回null
            pointA = (pointA == null) ? headB : pointA.next;
            pointB = (pointB == null) ? headA : pointB.next;
        }
        return pointA;
    }

    /*
    // 哈希表法
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> setA = new HashSet<>();
        ListNode pointA = headA;
        while (pointA != null){
            setA.add(pointA);
            pointA = pointA.next;
        }

        ListNode pointB = headB;
        while (pointB != null){
            if (setA.contains(pointB))
                return pointB;
            pointB = pointB.next;
        }
        return null;
    }
     */
}
