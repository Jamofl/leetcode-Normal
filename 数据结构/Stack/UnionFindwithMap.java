package 数据结构.Stack;
import java.util.*;
public class UnionFindwithMap {
    public Map<Integer, Integer> parent;
    public UnionFindwithMap(int[] nums){
        this.parent = new HashMap();
        for (int num : nums)
            parent.put(num, num);
    }

    public void union(int m, int n){
        int rootM = find(m);
        int rootN = find(n);
        if (rootM == rootN)
            return ;
        if (m < n)
            parent.put(m, rootN);
        else
            parent.put(n, rootM);
    }

    // return root of n; do the path compression at the same time
    public int find(int n){
        int root = n;
        while (parent.get(root) != root)
            root = parent.get(root);

        int cur = n;
        while (parent.get(cur) != root){
            int preParent = parent.get(cur);
            parent.put(cur, root);
            cur = preParent;
        }
        return root;
    }

    public static void main(String[] args){
        UnionFindwithMap uf = new UnionFindwithMap(new int[] {3,1,2,4});
        uf.union(1,2);
        uf.union(3,4);
        uf.union(2,3);

    }

}
