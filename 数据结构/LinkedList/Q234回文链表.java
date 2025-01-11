/*
234. 回文链表
请判断一个链表是否为回文链表。

示例 1:

输入: 1->2
输出: false
示例 2:

输入: 1->2->2->1
输出: true
进阶：
你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 */
package 数据结构.LinkedList;

public class Q234回文链表 {
    static class  ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            this.val = x;
            this.next = null;
        }

        ListNode(int x, ListNode next) {
            this.val = x;
            this.next = next;
        }
    }
    /*
    // 方法1 : 使用hash值  前序计算和后序计算hash值相同  说明为回文
    public boolean isPalindrome(ListNode head) {
        long hash1 = 0l; long hash2 = 0l;
        long h = 1l;
        long seed = 7;
        ListNode point = head;
        while(point != null){
            hash1 = hash1 * seed + point.val;
            hash2 = hash2 + h * point.val;
            h = h * seed;
            point = point.next;
        }
        return hash1 == hash2;
    }

     */

    // 方法2 : 递归法 妙！
    // 使用一个全局变量存储 头部节点preNode  当递归到最后一层时  返回尾部节点 和头部节点进行比较
    // 然后preNode = preNode.next，同时递归回退到上一层， head = head.pre
    ListNode preNode = null;
    public boolean isPalindrome(ListNode head) {
        preNode = head;
        return recursiveCheck(head);
    }

    public boolean recursiveCheck(ListNode head){
        if (head != null){
            if (! recursiveCheck(head.next))
                return false;

            if (preNode.val != head.val)
                return false;
            preNode = preNode.next;
        }

        return true;
    }

    // 方法3 : 快慢指针法  时间复杂度为O(1)
    // 整个流程可以分为以下五个步骤
    // 1.找到前半部分链表的尾节点。
    // 2.反转后半部分链表。
    // 3.判断是否回文。
    // 4.恢复链表。
    // 5.返回结果。
    boolean ans = true;
    public boolean isPalindrome3(ListNode head) {
        if (head == null || head.next == null)
            return true;
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode lastHalf = reverse(slow);
        ListNode firstHalf = head;
        while (firstHalf != null){
            if ( firstHalf.val != lastHalf.val) {
                ans = false;
                break;
            } else{
                firstHalf = firstHalf.next;
                lastHalf = lastHalf.next;
            }
        }
        reverse(slow);
        return ans;
    }

    public ListNode reverse(ListNode head){
        if (head == null || head.next == null)
            return head;
        ListNode reversed = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return reversed;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1, new ListNode(2, new ListNode(2, new ListNode(1))));
        Q234回文链表 q = new Q234回文链表();
        System.out.println(q.isPalindrome3(listNode));
    }



}
