package 数据结构;
import java.util.*;
class MyHashMap {
    private class Node{
        public int key;
        public int value;
        public Node pre;
        public Node next;

        public Node(int key, int value, Node pre, Node next){
            this.key = key;
            this.value = value;
            this.next = next;
            this.pre = pre;
        }

        public Node find(int key){
            if (this.key == key)
                return this;
            else if (this.next != null)
                return this.next.find(key);
            return null;
        }
    }


    public int n; // number of elements
    public int m; // number of buckets
    public static double loadfact = 1.5; // 负载因子，即 n / m
    public ArrayList<Node> buckets;

    /** Initialize your data structure here. */
    public MyHashMap() {
        this.n = 0;
        this.m = 50000;
        this.buckets = new ArrayList<>(m);
        for (int i = 0; i < m; i ++)
            buckets.add(null);
    }

    public MyHashMap(int m) {
        this.n = 0;
        this.m = m;
        this.buckets = new ArrayList<>(m);
        for (int i = 0; i < m; i ++)
            buckets.add(null);
    }

    public int getIndex(int key){
        return key % this.m;
    }

    private void resize(){
        MyHashMap newMyHashMap = new MyHashMap(this.m * 2);
        for (Node front : this.buckets){ // 外层循环遍历桶
            while (front != null){       // 内层循环遍历桶内的链表
                newMyHashMap.put(front.key, front.value);
                front = front.next;
            }
        }
        this.m = newMyHashMap.m;
        this.n = newMyHashMap.n;
        this.buckets = newMyHashMap.buckets;
        this.loadfact = newMyHashMap.loadfact;
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        int index = getIndex(key);
        Node front = buckets.get(index);
        if (front == null){ // if no such hash list exist
            buckets.set(index, new Node(key, value, null, null));
            this.n ++;
        }
        else{
            Node targetNode = front.find(key);
            if (targetNode != null) // if already exist
                targetNode.value = value;
            else{
                Node temp = new Node(key, value, null, front);
                buckets.set(index, temp);
                this.n ++;
            }
        }
        if ((double) n / m > loadfact)
            resize();
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int index = getIndex(key);
        Node front = buckets.get(index);
        if (front == null)
            return -1;
        else {
            Node target = front.find(key);
            if (target == null)
                return -1;
            else
                return target.value;
        }
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int index = getIndex(key);
        Node front = buckets.get(index);
        if (front == null)
            return;
        Node temp = front.find(key);
        if (temp == null)
            return;

        Node tempPre = temp.pre;
        Node tempNext = temp.next;
        if (tempPre == null)
            buckets.set(index, tempNext);
        else
            tempPre.next = tempNext;
        this.n --;
    }

    /**
     * Your MyHashMap object will be instantiated and called as such:
     * MyHashMap obj = new MyHashMap();
     * obj.put(key,value);
     * int param_2 = obj.get(key);
     * obj.remove(key);
     */

    public static void main(String[] args){
        MyHashMap hashmap = new MyHashMap();
        hashmap.put(65877, 63853);
        int r = hashmap.get(65877);
        System.out.println(r);

    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */
