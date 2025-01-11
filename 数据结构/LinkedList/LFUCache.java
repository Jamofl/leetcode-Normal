package 数据结构.LinkedList;

import java.util.*;


/**
    思路: 使用两个hashmap
            hashmap1<Integer, Node> 用作缓存
            hashmap2<freq, Deque> 用于存放当前频率对应的node节点的双向链表

            get(key): 返回value + 频率从freq => freq + 1
            put(key, value): 调用get方法获取目标节点, 若存在则修改value, 若干不存在则在freq=1处插入
 */
public class LFUCache {
    /**
     * 实现1 使用java自带的双向链表
     */
    private class Node{
        private Integer key;
        private Integer value;
        private Integer freq;

        public Node(Integer key, Integer value, Integer freq){
            this.key = key;
            this.value = value;
            this.freq = freq;
        }
    }


    private Map<Integer, Deque<Node>> freqMap = new HashMap<>();
    private Map<Integer, Node> nodeMap = new HashMap<>();
    private Integer capacity;
    private Integer minFreq;


    public LFUCache(int capacity) {
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
        Deque<Node> freqDeque = freqMap.getOrDefault(freq, new LinkedList<>());
        Deque<Node> nextFreqDeque = freqMap.getOrDefault(freq + 1, new LinkedList<>());
        freqDeque.remove(target);
        nextFreqDeque.addFirst(target);
        freqMap.put(freq + 1, nextFreqDeque);
        if (freqDeque.size() == 0 && target.freq == minFreq){
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
            Deque<Node> freqDeque = freqMap.getOrDefault(1, new LinkedList<>());
            freqDeque.addFirst(newNode);
            minFreq = 1;
            freqMap.put(1, freqDeque);
        }
        else {
            target.value = value;
        }
    }

    private void removeLeastFreqNode(){
        Deque<Node> minFreqDeque = freqMap.getOrDefault(minFreq, new LinkedList<>());
        nodeMap.remove(minFreqDeque.removeLast().key);
        if (minFreqDeque.size() == 0){
            minFreq ++;
        }
    }

    public static void main(String[] args) {
        //[[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]
        LFUCache lfuCache = new LFUCache(2);
        lfuCache.put(3,1);
        lfuCache.put(2,1);
        lfuCache.put(2,2);
        lfuCache.put(4,4);
        System.out.println(lfuCache.get(2));

    }


}
