package 数据结构;
import java.util.*;
public class RandomSetQ380 {
    private class Node{
        public int val;
        public Node(int val){
            this.val = val;
        }

        @Override
        public boolean equals(Object o){
            if (! (o instanceof Node))
                return false;
            Node that = (Node) o;
            return this.val == that.val;
        }

        @Override
        public int hashCode(){
            return this.val * 7;
        }
    }

    Set<Integer> set;
    List<Node> list;
    Random rand;
    /** Initialize your data structure here. */
    public RandomSetQ380() {
        set = new HashSet<>();
        list = new ArrayList<>();
        rand = new Random();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (set.contains(val))
            return false;
        set.add(val);
        list.add(new Node(val));
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!set.contains(val))
            return false;
        set.remove(val);
        list.remove(new Node(val));
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        Integer toReturn = null;
        int size = set.size();
        int randomNum = rand.nextInt(size);
        toReturn = list.get(randomNum).val;
        return toReturn;
    }

}
