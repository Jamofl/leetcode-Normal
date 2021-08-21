package 数据结构;

public class BST {
    public int val;
    public BST right;
    public BST left;

    public BST (){

    }
    public BST (int val){
        this.val = val;
    }

    public BST(int val, BST left, BST right){
        this.val = val;
        this.left = left;
        this.right = right;
    }


    public BST insert(int val){
        return insert(this, val);
    }

    public static BST insert(BST tree, int val){
        if (tree == null)
            return new BST(val);
        if (val > tree.val)
            tree.right = insert(tree.right, val);
        else if (val <= tree.val)
            tree.left = insert(tree.left, val);
        return tree;

    }

    public BST search(int val){
//        if (this.val == val)
//            return this;
//        else if (this.val < val)
//            if (this.right != null)
//                return this.right.search(val);
//            else
//                return null;
//        else
//            if (this.left != null)
//                return this.left.search(val);
//            else
//                return null;
        return search(this, val);
    }

    public static BST search(BST tree, int val){
        if (tree == null)
            return null;
        if (tree.val == val)
            return tree;
        else if (val > tree.val)
            return search(tree.right, val);
        else
            return search(tree.left, val);
    }


    public void delete(int val){
        delete(this, val);
    }

    public static BST delete(BST tree, int val){
        if (val > tree.val)
            tree.right = delete(tree.right, val);
        else if (val < tree.val)
            tree.left = delete(tree.left, val);
        else{
            if (tree.left == null && tree.right == null)
                return null;
            if (tree.right == null)
                return tree.left;
            if (tree.right != null){
                BST successor = findSuccessor(tree.right);
                tree = delete(tree, successor.val);
                tree.val = successor.val;
            }
        }
        return tree;
    }

    private static BST findSuccessor(BST tree){
        while (tree.left != null)
            tree = tree.left;
        return tree;
    }
}
