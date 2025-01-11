package 数据结构.LinkedList;

import java.util.HashMap;
import java.util.Map;

public class LFUCache2 {
    /**
     * 实现2 自己实现双向链表
     */
    private class Node{
        private Integer key;
        private Integer value;
        private Integer freq;
        private Node next;
        private Node pre;

        public Node(Integer key, Integer value, Integer freq){
            this.key = key;
            this.value = value;
            this.freq = freq;
        }

        public Node(Integer key, Integer value){
            this.key = key;
            this.value = value;
            this.freq = 1;
        }
    }

    private class DoubleLinkedList {
        private Node first = new Node(-1, -1);
        private Node last = new Node(-2, -2);
        private Integer size;

        public DoubleLinkedList(){
            this.first.next = last;
            this.last.pre = first;
            this.size = 0;
        }
        public void remove(Node target){
            target.next.pre = target.pre;
            target.pre.next = target.next;
            this.size --;
        }


        public void addFirst(Node target){
            target.pre = this.first;
            target.next = this.first.next;
            this.first.next.pre = target;
            this.first.next = target;
            this.size ++;
        }

        public Node removeLast(){
            Node target = this.last.pre;
            remove(target);
            return target;
        }
    }


    private Map<Integer, DoubleLinkedList> freqMap = new HashMap<>();
    private Map<Integer, Node> nodeMap = new HashMap<>();
    private Integer capacity;
    private Integer minFreq;


    public LFUCache2(int capacity) {
        this.capacity = capacity;
        this.minFreq = 0;
    }

    public int get(int key) {
        Node target = getNode(key);
        return target == null ? -1 : target.value;
    }

    public Node getNode(int key) {
        Node target = nodeMap.get(key);
        if (target != null){
            addFreq(target);
        }
        return target;
    }

    private void addFreq(Node target){
        int freq = target.freq;
        DoubleLinkedList freqDeque = freqMap.getOrDefault(freq, new DoubleLinkedList());
        DoubleLinkedList nextFreqDeque = freqMap.getOrDefault(freq + 1, new DoubleLinkedList());
        freqDeque.remove(target);
        nextFreqDeque.addFirst(target);
        freqMap.put(freq + 1, nextFreqDeque);
        if (freqDeque.size == 0 && target.freq == minFreq){
            minFreq ++;
        }
        target.freq ++;
    }

    public void put(int key, int value) {
        Node target = getNode(key);
        if (target == null){
            if (nodeMap.keySet().size() == capacity){
                removeLeastFreqNode();
            }
            // add new node
            Node newNode = new Node(key, value, 1);
            nodeMap.put(key, newNode);
            DoubleLinkedList freqDeque = freqMap.getOrDefault(1, new DoubleLinkedList());
            freqDeque.addFirst(newNode);
            minFreq = 1;
            freqMap.put(1, freqDeque);
        }
        else {
            target.value = value;
        }
    }

    private void removeLeastFreqNode(){
        DoubleLinkedList minFreqDeque = freqMap.getOrDefault(minFreq, new DoubleLinkedList());
        nodeMap.remove(minFreqDeque.removeLast().key);
        if (minFreqDeque.size == 0){
            minFreq ++;
        }
    }

    public static void main(String[] args) {
        //[[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]
        LFUCache2 lfuCache = new LFUCache2(2);
        lfuCache.put(3,1);
        lfuCache.put(2,1);
        lfuCache.put(2,2);
        lfuCache.put(4,4);
        System.out.println(lfuCache.get(2));

    }


}
