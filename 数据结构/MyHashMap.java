package 数据结构;
import BackTrack回溯.Q22括号生成;

import java.util.*;
class MyHashMap {
    private class Node {
        public int key;
        public int value;
        public Node pre;
        public Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.next = null;
            this.pre = null;
        }

        public Node(int key, int value, Node pre, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
            this.pre = pre;
        }

        public Node find(int key) {
            if (this.key == key)
                return this;
            if (this.next != null)
                return this.next.find(key);
            return null;
        }
    }

    public int n; // number of elements 元素的数量
    public int m; // number of buckets  桶的数量
    public static double loadfact = 1.1; // 负载因子，即 n / m
    public ArrayList<Node> buckets;

    /**
     * Initialize your data structure here.
     */
    public MyHashMap() {
        this.n = 0;
        this.m = 769;
        this.buckets = new ArrayList<>(m);
        for (int i = 0; i < m; i++) {
            Node dummyHead = new Node(-1, -1);
            Node dummyTail = new Node(-2, -2);
            dummyHead.next = dummyTail;
            dummyTail.pre = dummyHead;
            buckets.add(dummyHead); // 桶中添加虚拟头尾节点 防止头尾节点空指针特判
        }
    }

    public MyHashMap(int m) {
        this.n = 0;
        this.m = m;
        this.buckets = new ArrayList<>(m);
        for (int i = 0; i < m; i++) {
            Node dummyHead = new Node(-1, -1);
            Node dummyTail = new Node(-2, -2);
            dummyHead.next = dummyTail;
            dummyTail.pre = dummyHead;
            buckets.add(dummyHead); // 桶中添加虚拟头尾节点 防止头尾节点空指针特判
        }
    }

    public int getIndex(int key) {
        return key % this.m;
    }

    private void resize() {
        MyHashMap newMyHashMap = new MyHashMap(this.m * 2);
        for (Node front : this.buckets) {// 外层循环遍历桶
            front = front.next; // 跳过dummy虚拟节点
            while (front != null && front.value != -2) {       // 内层循环遍历桶内的链表
                newMyHashMap.put(front.key, front.value);
                front = front.next;
            }
        }
        this.m = newMyHashMap.m;
        this.n = newMyHashMap.n;
        this.buckets = newMyHashMap.buckets;
        this.loadfact = newMyHashMap.loadfact;
    }

    /**
     * value will always be non-negative.
     */
    public void put(int key, int value) {
        int index = getIndex(key);
        Node front = buckets.get(index);

        Node targetNode = front.find(key);
        if (targetNode != null) // if already exist, update it's value
            targetNode.value = value;
        else {
            Node target = new Node(key, value);
            target.next = front.next;
            target.pre = front;
            front.next = target;
            target.next.pre = target;
            this.n++;
        }
        if (((double) n / m) > loadfact) {
            resize();
        }

    }

    /**
     * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
     */
    public int get(int key) {
        int index = getIndex(key);
        Node front = buckets.get(index);
        Node target = front.find(key);
        if (target == null) {
            return -1;
        } else {
            return target.value;
        }

    }

    /**
     * Removes the mapping of the specified value key if this map contains a mapping for the key
     */
    public void remove(int key) {
        int index = getIndex(key);
        Node front = buckets.get(index);
        Node target = front.find(key);
        if (target == null){
            return;
        }
        target.pre.next = target.next;
        target.next.pre = target.pre;
        this.n--;
    }

    /**
     * Your MyHashMap object will be instantiated and called as such:
     * MyHashMap obj = new MyHashMap();
     * obj.put(key,value);
     * int param_2 = obj.get(key);
     * obj.remove(key);
     */

    public static void main(String[] args){
        MyHashMap hashmap = new MyHashMap(1);
        hashmap.put(1, 1);
        hashmap.put(2, 2);
        hashmap.put(3, 3);
        hashmap.remove(2);

    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */
