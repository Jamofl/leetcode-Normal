import java.util.ArrayList;
import java.util.List;

public class Trie {

    private class Node{
        private char ch;
        private Node[] children;
        private boolean isKey;

        public Node(char ch){
            this.ch = ch;
            this.children = new Node[26];
            this.isKey = false;
        }
    }

    private Node root;
    public Trie(){
        root = new Node('R');
    }


    public void insert(String prefix) {
        Node cur = root;
        for (char c : prefix.toCharArray()){
            int index = c - 'a';
            if (cur.children[index] == null){
                cur.children[index] = new Node(c);
            }
            cur = cur.children[index];
        }
        cur.isKey = true;
    }


    /**
     * 返回字典树中是否存在某个单词
     * @param word
     * @return
     */
    public boolean search(String word){
        Node node = searchNode(word);
        return node != null && node.isKey;
    }

    /**
     * 返回字典树中是否存在prefix开头的单词
     * @param prefix
     * @return
     */
    public boolean startWith(String prefix){
        Node node = searchNode(prefix);
        return node != null;
    }

    /**
     * helper方法 用来查找字典树中是以prefix开头的节点
     * @param prefix
     * @return
     */
    private Node searchNode(String prefix){
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


    /**
     * 返回所有以prefix开头的单词
     * @param prefix
     * @return
     */
    public List<String> wordWithPrefix(String prefix){
        Node node = searchNode(prefix);
        if (node == null){
            return new ArrayList<>();
        }
        List<String> ans = new ArrayList<>();
        dfs(node, prefix, ans);
        return ans;
    }

    private void dfs(Node node, String path, List<String> ans){
        if (node.isKey){
            ans.add(path);
        }
        for (Node next : node.children){
            if (next != null){
                dfs(next, path + next.ch, ans);
            }
        }
    }


    public static void main(String[] args) {

        Trie trie = new Trie();
        trie.insert("app");
        trie.insert("apple");
        trie.insert("applepen");
        trie.insert("application");
        System.out.println(trie.search("apple")); // 返回 true
        System.out.println(trie.search("app"));     // 返回 false
        System.out.println(trie.startWith("app")); // 返回 true
        System.out.println(trie.search("app"));     // 返回 true
        System.out.println(trie.wordWithPrefix("app"));

    }

}
