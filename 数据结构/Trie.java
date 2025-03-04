package 数据结构;

import java.util.*;

public class Trie {

    public Node root;
    private class Node{
        public char ch; // 表明当前节点的字符

        public Node[] children; // 子节点 一个长度为26的Node数组
        public boolean isKey; // 是否为关键节点 从初始节点到关键节点的字符连起来可以构成一个单词

        public Node(char ch){
            this.children = new Node[26];
            this.ch = ch;
            this.isKey = false;
        }
    }

    /** Initialize your data structure here. */
    public Trie() {
        this.root = new Node('R');
    }


    /**
     * 查找某个前缀开头的单词
     * @param prefix
     * @return
     */
    private Node searchNodeWithPrefix(String prefix){
        Node cur = root;
        for (char c : prefix.toCharArray()){
            int index = c - 'a';
            if (cur.children[index] == null){
                return null;
            }
            cur = cur.children[index];
        }
        return cur;
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        Node cur = root;
        for (char c : word.toCharArray()){
            int index = c - 'a';
            if (cur.children[index] == null){
                cur.children[index] = new Node(c);
            }
            cur = cur.children[index];
        }
        cur.isKey = true; // 遍历结束后 cur所在的节点一定是word的最后一个字符
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Node node = searchNodeWithPrefix(word);
        return node != null && node.isKey;
    }



    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Node node = searchNodeWithPrefix(prefix);
        return node != null;
    }

    /**
     * 返回字典树中所有以prefix开头的单词
     * @param prefix
     * @return
     */
    public List<String> keysWithPrefix(String prefix){
        Node node = searchNodeWithPrefix(prefix);
        if (node == null){
            return new ArrayList<>();
        }
        List<String> ans = new ArrayList<>();
        // point指向的是prefix最后一个字符所在的节点
        dfs(ans, prefix, node);
        return ans;
    }

    // dfs （类似回溯，但由于这里是字符串，所以不需要回溯）
    private void dfs(List<String> ans, String path, Node cur){
        if (cur.isKey){
            ans.add(path);
        }

        for (Node node : cur.children){
            if (node != null){
                dfs(ans, path + node.ch, node);
            }
        }
    }

    public static void main(String[] args){
        Trie trie = new Trie();
        trie.insert("app");
        trie.insert("apple");
        trie.insert("applepen");
        trie.insert("application");
        System.out.println(trie.search("app")); // 返回 true
        System.out.println(trie.search("appl")); // 返回 false
        System.out.println(trie.startsWith("appl"));     // 返回 true
        System.out.println(trie.keysWithPrefix("app"));
        System.out.println(trie.keysWithPrefix("appe"));

    }
}
