/*
146. LRU 缓存机制
运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制 。
实现 LRUCache 类：

LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。


进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作？


示例：

输入
["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
输出
[null, null, null, 1, null, -1, null, -1, 3, 4]

解释
LRUCache lRUCache = new LRUCache(2);
lRUCache.put(1, 1); // 缓存是 {1=1}
lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
lRUCache.get(1);    // 返回 1
lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
lRUCache.get(2);    // 返回 -1 (未找到)
lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
lRUCache.get(1);    // 返回 -1 (未找到)
lRUCache.get(3);    // 返回 3
lRUCache.get(4);    // 返回 4


提示：

1 <= capacity <= 3000
0 <= key <= 3000
0 <= value <= 104
最多调用 3 * 104 次 get 和 put
 */
package 数据结构;
import java.util.*;
public class Q164设计LRU {
    private class Node{
        public int key;
        public int val;
        public Node pre;
        public Node next;
        public Node(int key, int val){
            this.key = key;
            this.val = val;
            this.pre = null;
            this.next = null;
        }
        public Node(int key, int val, Node pre, Node next){
            this.key = key;
            this.val = val;
            this.pre = pre;
            this.next = next;
        }
    }

    public Map<Integer, Node> map;
    public int capacity;
    public int size;
    public Node first;
    public Node last;

    public Q164设计LRU(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.first = new Node(-1, -1);
        this.last = new Node(-2, -2);
        this.first.next = this.last;
        this.last.pre = this.first;
    }

    public int get(int key) {
        if (! map.containsKey(key))
            return -1;
        Node target = map.get(key);
        moveToLast(target);
        return target.val;
    }

    public void put(int key, int value) {
        if (this.size >= this.capacity){
            map.remove(this.first.next.key);
            this.first.next.next.pre = this.first;
            this.first.next = this.first.next.next;
            this.size --;
        }

        if (map.containsKey(key)){
            Node target = map.get(key);
            target.val = value;
            moveToLast(target);
            return;
        }
        else {
            Node cur = new Node(key, value, this.last.pre, this.last);
            this.last.pre.next = cur;
            this.last.pre = cur;

            this.map.put(key, cur);
        }
        this.size ++;
    }

    private void moveToLast(Node target){
        target.pre.next = target.next;
        target.next.pre = target.pre;

        target.next = this.last;
        target.pre = this.last.pre;
        this.last.pre.next = target;
        this.last.pre = target;
    }

    public static void main(String[] args){
//        Q164设计LRU lRUCache = new Q164设计LRU(2);
//        int r = 0;
//        lRUCache.put(1, 1); // 缓存是 {1=1}
//        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
//        r = lRUCache.get(1);    // 返回 1
//        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
//        r = lRUCache.get(2);    // 返回 -1 (未找到)
//        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
//        r = lRUCache.get(1);    // 返回 -1 (未找到)
//        r = lRUCache.get(3);    // 返回 3
//        r = lRUCache.get(4);    // 返回 4


    }
}
