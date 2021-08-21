package 数据结构.DisjointSet;
import java.util.*;
class Test{
    public int[] disjointSet;
    public Test(int n ){
        this.disjointSet = new int[n];
        Arrays.fill(disjointSet, -1);
    }

    public void union(int i, int j){
        int small = (i > j) ? j : i;
        int big   = (i > j) ? i : j;
        int root1 = find(small);
        int root2 = find(big);
        disjointSet[root2] = disjointSet[root2] + disjointSet[root1];
        disjointSet[root1] = root2;
    }

    // return the root of m
    public int find(int i){
        if (disjointSet[i] < 0)
            return i;
        return find(disjointSet[i]);
    }

    public static void main(String[] args){
        Test ds = new Test(10);
        ds.union(3, 5);
        ds.union(2, 3);
        int r = ds.find(2);
        System.out.println(r);

    }
}

