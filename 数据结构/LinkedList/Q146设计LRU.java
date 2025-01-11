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
package 数据结构.LinkedList;
import java.util.*;

/**
 *  【思路】双向链表 + hashmap结构；
 *   双向链表 用来维护“最近使用的”这一概念 只是为了记录频率 和缓存本身无关
 *           头部尾部各有一个哑结点，用来表示最远未使用的节点 和 最近使用过的节点
 *   hashmap 即缓存本身 用来存储integer -> node的映射关系 同key则替换
 *
 *   定义一个帮助函数，moveToLast，即每次调用put get方法后，都调用moveToLast方法将该节点移至链表末尾，表明他是最新的
 */
public class Q146设计LRU {
    private int capacity; // 容量
    private Node first; // 虚拟头结点
    private Node last; // 虚拟尾节点
    private Map<Integer, Node> map; // 缓存本身 key=Integer代表缓存key  value=Node代表缓存值 同key则覆盖

    // node的双向链表只是为了用来实现Lest Recently Use的 是为了记录最近使用的数据和最久使用的数据 和缓存本身没有关系
    private class Node{
        private int key;
        private int value;
        private Node next;
        private Node pre;

        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }

        public Node(int key, int value, Node next, Node pre){
            this.key = key;
            this.value = value;
            this.next = next;
            this.pre = pre;
        }
    }

    public Q146设计LRU(int capacity) {
        this.capacity = capacity;
        this.first = new Node(-1, -1);
        this.last = new Node(-2, -2);
        this.first.next = last;
        this.last.pre = first;
        this.map = new HashMap<>();
    }

    public int get(int key) {
        Node target = this.getNode(key);
        return target == null ? -1 : target.value;
    }

    // 定义了一个私有函数 用来获取缓存中的某个节点 并实现了LRU更新
    private Node getNode(int key) {
        // 如果缓存中不存在 直接返回
        if (!map.containsKey(key)){
            return null;
        }
        // 缓存中存在 获取最新值 并更新频率
        Node target = map.get(key);
        // 断开node和原相邻节点的关系 这部分需要调用方自行实现
        target.pre.next = target.next;
        target.next.pre = target.pre;
        moveToLast(target);
        return target;
    }

    public void put(int key, int value) {
        // 如果缓存中已经存在 直接调用get方法获取目标节点(LRU更新的逻辑自包含了) 然后替换value
        if (map.containsKey(key)){
            Node target = getNode(key);
            target.value = value;
        }
        // 如果缓存中不存在 先判断size剔除旧数据 然后插入新数据
        else {
            if (map.keySet().size() == capacity){
                map.remove(this.first.next.key); // 注意这里是.key 不是.value
                this.first.next = this.first.next.next;
                this.first.next.pre = this.first;
            }
            Node newNode = new Node(key, value);
            moveToLast(newNode);
            map.put(key, newNode);
        }
    }


    /**
     * 将node移动至末尾 这里站在node的视角 绑定了了node和末尾相邻节点的关系 但是没有断开node和原相邻节点的关系 这部分需要调用方自行实现
     * @param node
     */
    private void moveToLast(Node node){
        node.next = this.last;
        node.pre = this.last.pre;
        this.last.pre.next = node;
        this.last.pre = node;
    }
    public static void main(String[] args){
        Q146设计LRU lRUCache = new Q146设计LRU(2);
        int r = 0;
        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        r = lRUCache.get(1);    // 返回 1
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        r = lRUCache.get(2);    // 返回 -1 (未找到)
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        r = lRUCache.get(1);    // 返回 -1 (未找到)
        r = lRUCache.get(3);    // 返回 3
        r = lRUCache.get(4);    // 返回 4
    }
}
