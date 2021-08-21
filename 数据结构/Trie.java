package 数据结构;

import java.util.*;

public class Trie {

    public Node root;
    public final int R = 26;

    private class Node{
        public char ch;
        public Node[] next = new Node[R];
        public boolean iskey = false;

        public Node(char ch){
            this.ch = ch;
        }
    }

    /** Initialize your data structure here. */
    public Trie() {
        this.root = new Node('R');
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        char[] charArray = word.toCharArray();
        Node point = root;

        for (int i = 0; i < word.length(); i ++){
            char c = charArray[i];
            if (point.next[c - 'a'] == null){
                point.next[c - 'a'] = new Node(c);
            }
            point = point.next[c - 'a'];
            if (i == word.length() - 1)
                point.iskey = true;
        }
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        char[] charArray = word.toCharArray();
        Node point = root;

        for (int i = 0; i < charArray.length; i ++){
            char c = charArray[i];
            if (point.next[c - 'a'] == null)
                return false;
            else {
                point = point.next[c - 'a'];
                if (i == word.length() - 1){
                    return point.iskey;
                }
            }
        }
        return false;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        char[] charArray = prefix.toCharArray();
        Node point = root;
        for(int i = 0; i < charArray.length; i ++){
            char c = charArray[i];
            if (point.next[c - 'a'] == null)
                return false;
            else{
                point = point.next[c - 'a'];
            }
        }
        return true;
    }

    public List<String> keysWithPrefix(String prefix){
        char[] charArr = prefix.toCharArray();
        List<String> ans = new LinkedList<>();
        Node point = root;
        for (int i = 0; i < prefix.length(); i ++){
            char c = charArr[i];
            if (point.next[c - 'a'] == null)
                return new LinkedList<>();
            else{
                point = point.next[c - 'a'];
            }
        }
        // point指向的是prefix最后一个字符所在的节点
        dfs(ans, prefix, point);
        return ans;
    }

    // dfs （类似回溯，但由于这里是字符串，所以不需要回溯）
    private void dfs(List<String> ans, String path, Node point){
        if (point.iskey)
            ans.add(path);

        for (Node node : point.next){
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
        System.out.println(trie.search("apple")); // 返回 true
        System.out.println(trie.search("app"));     // 返回 false
        System.out.println(trie.startsWith("app")); // 返回 true
        System.out.println(trie.search("app"));     // 返回 true
        System.out.println(trie.keysWithPrefix("app"));

    }
}
