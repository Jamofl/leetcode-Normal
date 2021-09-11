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

    private V getHelper(Node root, K key){
        if (root == null)
            return null;
        if(key.compareTo(root.key) > 0)
            return getHelper(root.right, key);
        else if (key.compareTo(root.key) < 0)
            return getHelper(root.left, key);
        else
            return root.value;
    }

    public void put(K key, V value){
        this.root = putHelper(this.root, key, value);
    }

    // 不使用root的方法存在错误，最后的T指向叶子节点，而不是树本身，会失去对树本身的指针。
    private Node putHelper(Node root, K key, V value){
        if (root == null)
            return new Node(key, value);
        else if (key.compareTo(root.key) > 0)
            root.right = putHelper(root.right, key, value);
        else if (key.compareTo(root.key) < 0)
            root.left = putHelper(root.left, key, value);
        else
            root.value = value;
        return root;
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

    private Node deleteHelper(Node root, K key){
        if (root == null)
            return null;
        else if (key.compareTo(root.key) > 0){
            root.right = deleteHelper(root.right, key);
        }
        else if (key.compareTo(root.key) < 0){
            root.left = deleteHelper(root.left, key);
        }
        else{
            if(root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;
            // 左右都不为空  找到右子树上的后继节点  删除该节点 并用该节点的值代替根节点
            else{
                Node successor = findSuccessor(root.right);// the smallest node on the right sub tree 右子树一直往左找
                this.deleteHelper(root, successor.key);
                root.key = successor.key;
                root.value = successor.value;
            }
        }
        return root;
    }

    // 找后继节点  find the Node with smallest key in Node N;
    private Node findSuccessor(Node N){
        if(N.left == null)
            return N;
        else{
            return findSuccessor(N.left);
        }
    }
}
