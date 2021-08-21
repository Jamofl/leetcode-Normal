package 数据结构;
public class MyTreeMap<K extends Comparable<K>, V> {

    private class Node{
        private Node left, right;
        private V value;
        private K key;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
        }
    }

    private Node root;

    public MyTreeMap(){
        this.root = null;
    }

    public V get(K key){
        return getHelper(this.root, key);
    }

    private V getHelper(Node N, K key){
        if (N == null)
            return null;
        if(key.compareTo(N.key) > 0)
            return getHelper(N.right, key);
        else if (key.compareTo(N.key) < 0)
            return getHelper(N.left, key);
        else
            return N.value;
    }

    public void put(K key, V value){
        this.root = putHelper(this.root, key, value);
    }

    // 不使用root的方法存在错误，最后的T指向叶子节点，而不是树本身，会失去对树本身的指针。
    private Node putHelper(Node node, K key, V value){
        if (node == null)
            return new Node(key, value);
        else if (key.compareTo(node.key) > 0)
            node.right = putHelper(node.right, key, value);
        else if (key.compareTo(node.key) < 0)
            node.left = putHelper(node.left, key, value);
        else
            node.value = value;
        return node;
    }

    public void delete(K key){
        this.root = deleteHelper(this.root, key);
    }

    /*
    public Node deleteH(Node node, K key){
        if (node == null)
            return null;
        if (key.compareTo(node.key) > 0)
            node.right = deleteH(node.right, key);
        else if (key.compareTo(node.key) < 0)
            node.left = deleteH(node.left, key);
        else{
            if (node.right == null && node.left == null)
                return null;
            else if (node.left == null)
                return node.right;
            else{
                K predecessorKey = getPredecessor(node.left).key;
                V predecessorVal = getPredecessor(node.left).value;
                deleteH(node, predecessorKey);
                node.key = predecessorKey;
                node.value = predecessorVal;
            }
        }
        return node;
    }

    private Node getPredecessor(Node node){
        while (node.right != null)
            node = node.right;
        return node;
    }
     */

    private Node deleteHelper(Node node, K key){
        if (node == null)
            return null;
        else if (key.compareTo(node.key) > 0){
            node.right = deleteHelper(node.right, key);
        }
        else if (key.compareTo(node.key) < 0){
            node.left = deleteHelper(node.left, key);
        }
        else{
            if(node.left == null)
                return node.right;
            else if (node.right == null)
                return node.left;
            else{
                Node successor = findSuccessor(node.right);// the smallest node on the right sub tree
                this.deleteHelper(node, successor.key);
                node.key = successor.key;
                node.value = successor.value;
            }
        }
        return node;
    }

    // find the Node with smallest key in Node N;
    private Node findSuccessor(Node N){
        if(N.left == null)
            return N;
        else{
            return findSuccessor(N.left);
        }
    }
}
